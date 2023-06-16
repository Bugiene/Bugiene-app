const express = require("express");
const registerRoute = require("./auth/register");
const loginRoute = require("./auth/login");
const getUserRoute = require("./routes/getUser");
const postDetectionRoute = require("./routes/postDetection");
const getHistoryRoute = require("./routes/getHistory");
const getHistoryDetailRoute = require("./routes/getHistoryDetail");

const app = express();
app.use(express.json());

app.get("/", (req, res) => {
  res.status(200).json({
    message: "Response Successfully",
  });
});

// Rute untuk registrasi
app.use("/register", registerRoute);

// Rute untuk login
app.use("/login", loginRoute);

// Rute untuk mendapatkan data pengguna
app.use("/getUser", getUserRoute);

// Rute untuk mendeteksi kesegaran buah
app.use("/postDetection", postDetectionRoute);

// Rute untuk mendapatkan riwayat deteksi
app.use("/getHistory", getHistoryRoute);

// Rute untuk mendapatkan detail riwayat deteksi
app.use("/getHistoryDetail", getHistoryDetailRoute);

// Jalankan server
const port = 8080;
app.listen(port, () => {
  console.log(`Listening: http://localhost:${port}`);
});

console.log(process.version);
