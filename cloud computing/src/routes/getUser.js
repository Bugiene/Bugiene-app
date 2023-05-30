const express = require("express");
const router = express.Router();
const connection = require("../config/database");
const verifyToken = require("../middleware/verifyToken");

// Route to fetch user profile data
router.get("/", verifyToken, (req, res) => {
  const userId = req.userId;

  connection.query(
    "SELECT * FROM users WHERE userId = ?",
    [userId],
    (err, results) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: "Internal server error" });
      }

      if (results.length === 0) {
        return res.status(404).json({ error: "User not found" });
      }

      const { username, email } = results[0];

      return res.status(200).json({
        status: "success",
        message: "User details fetched successfully",
        data: {
          userId,
          username,
          email,
        },
      });
    }
  );
});

module.exports = router;
