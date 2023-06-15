const express = require("express");
const verifyToken = require("../middleware/verifyToken");
const userModel = require("../models/user");

const router = express.Router();
router.use(express.urlencoded({ extended: true }));
router.use(express.json());

// Route to fetch user profile data
router.get("/", verifyToken, (req, res) => {
  const userId = req.userId;

  userModel.getUser(userId, (err, user) => {
    if (err) {
      console.error(err);
      return res.status(500).json({ error: "Internal server error" });
    }

    if (!user) {
      return res.status(404).json({ error: "User not found" });
    }

    return res.status(200).json({
      status: "success",
      message: "User details fetched successfully",
      data: user,
    });
  });
});

module.exports = router;