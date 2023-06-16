const express = require("express");
const router = express.Router();
const verifyToken = require("../middleware/verifyToken");
const { getDetectionHistory } = require("../models/detection");

router.get("/", verifyToken, async (req, res) => {
  const userId = req.query.userId;

  try {
    const history = await getDetectionHistory(userId);

    return res.status(200).json({
      status: "success",
      message: "History detections fetched successfully",
      listhistory: history,
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal server error" });
  }
});

module.exports = router;