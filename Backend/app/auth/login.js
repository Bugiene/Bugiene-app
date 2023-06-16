const express = require("express");
const bcrypt = require("bcryptjs");
const jwt = require("jsonwebtoken");
const connection = require("../config/database");

const router = express.Router();
router.use(express.urlencoded({ extended: true }));
router.use(express.json());

// Fungsi untuk menghasilkan accessToken
function generateAccessToken(userId) {
    const accessToken = jwt.sign({ userId }, "secret_key", { expiresIn: "1d" });
    return accessToken;
}

router.post("/", (req, res) => {
  const { email, password } = req.body;

  connection.query(
    "SELECT * FROM users WHERE email = ?",
    [email],
    (err, results) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: "Internal server error" });
      }

      if (results.length === 0) {
        return res.status(401).json({ error: "Invalid email or password" });
      }

      const { userId, username, email, password: hashedPassword } = results[0];

      bcrypt.compare(password, hashedPassword, (err, isMatch) => {
        if (err) {
          console.error(err);
          return res.status(500).json({ error: "Internal server error" });
        }

        if (!isMatch) {
          return res.status(401).json({ error: "Invalid email or password" });
        }

        const accessToken = generateAccessToken(userId);

        return res.status(200).json({
          status: "success",
          message: "Login successful",
          data: {
            userId,
            username,
            email,
            accessToken,
          },
        });
      });
    }
  );
});

module.exports = router;