const express = require("express");
const router = express.Router();
const verifyToken = require("../middleware/verifyToken");
const { getDetectionHistoryDetail } = require("../models/detection");

router.get("/:detectId", verifyToken, async (req, res) => {
  const detectId = req.params.detectId;

  try {
    const history = await getDetectionHistoryDetail(detectId);

    return res.status(200).json({
      status: "success",
      message: "History detail fetched successfully",
      history: history,
    });
  } catch (error) {
    console.error(error);
    return res.status(500).json({ error: "Internal server error" });
  }
});

module.exports = router;