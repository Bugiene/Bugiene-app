const express = require("express");
const { nanoid } = require("nanoid");
const multer = require("multer");
const { Storage } = require("@google-cloud/storage");
const tf = require("@tensorflow/tfjs-node");
const verifyToken = require("../middleware/verifyToken");
const { saveDetection } = require("../models/detection");

const router = express.Router();
const upload = multer({
    storage: multer.memoryStorage(),
    limits: {
        fileSize: 10 * 1024 * 1024, // Batas ukuran file 10MB
    },
});

// Konfigurasi Google Cloud Storage
const storage = new Storage({
    projectId: "capstone-387004", // ganti pake nama project id dari bugiene
    keyFilename: "./servicekey.json", // sesuaikan sama service account di IAM nya ( pakai aja yang owner )
});

// Endpoint untuk mendeteksi kesegaran buah
router.post("/", verifyToken, upload.single("image"), async (req, res) => {
    try {
        const image = req.file.buffer;

        // Simpan foto hasil deteksi ke bucket di Google Cloud Storage
        const bucketName = "capstonebucket-c23"; // ganti pake bucket dari bugiene
        const fileName = `${nanoid(10)}.jpg`;

        const file = storage.bucket(bucketName).file(fileName);
        await file.save(image);

        // Lakukan deteksi kesegaran buah menggunakan model TensorFlow JSON
        const model = await tf.loadLayersModel(
            `https://storage.googleapis.com/capstonebucket-c23/json_model1/content/json_model/model.json`
            // `https://storage.googleapis.com/capstonebucket-c23/saved_json/model.json`
        );

        // Inference
        const img = tf.node.decodeImage(image);
        const resizedImg = tf.image.resizeBilinear(img, [150, 150]);
        const normalizedImg = resizedImg.expandDims(0).div(255);

        const classes = model.predict(normalizedImg);
        
        const label = classes.squeeze().arraySync()[0] > 0.5 ? 'Rotten Fruit' : 'Fresh Fruit';
        const accuracy = label === 'Fresh Fruit' ? 1.0 - classes.arraySync()[0] : classes.arraySync()[0];

        // Simpan hasil deteksi ke database
        const detectId = nanoid(10);
        const userId = req.userId;
        const imageUrl = `https://storage.googleapis.com/${bucketName}/${fileName}`;
        const createdAt = new Date().toISOString();
        const datetimeObj = new Date(createdAt);
        const formattedDatetime = datetimeObj.toISOString().slice(0, 19).replace('T', ' ');

        const detectionData = {
            detectId,
            userId,
            imageUrl,
            freshness: label === 'Fresh Fruit',
            accuracy,
            createdAt: formattedDatetime,
        };

        await saveDetection(userId, detectionData);

        return res.status(200).json({
            status: "success",
            message: "Fruit detection successful",
            result: {
                detectId: detectionData.detectId,
                freshness: detectionData.freshness,
                accuracy: detectionData.accuracy,
            },
        });
    } catch (error) {
        console.error(error);
        return res.status(500).json({ error: error.message });
    }
});

module.exports = router;