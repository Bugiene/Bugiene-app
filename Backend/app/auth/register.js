const express = require("express");
const bcrypt = require("bcryptjs");
const { nanoid } = require("nanoid");
const connection = require("../config/database");
const router = express.Router();

router.use(express.urlencoded({ extended: true }));
router.use(express.json());

// Fungsi untuk menghasilkan userId unik bernilai string 10 karakter
function generateUserId() {
  const userId = nanoid(10);
  return userId;
}

router.post("/", (req, res) => {
  const { username, email, password } = req.body;

  // Periksa apakah email sudah ada dalam database
  connection.query(
    "SELECT * FROM users WHERE email = ?",
    [email],
    async (err, results) => {
      if (err) {
        console.error(err);
        return res.status(500).json({ error: "Internal server error" });
      }

      if (results && results.length > 0) {
        return res.status(409).json({ error: "Email already exists" });
      }

      if (!password || password.length < 8) {
        return res
          .status(400)
          .json({ error: "Password should be at least 8 characters long" });
      }

      bcrypt.hash(password, 10, (err, hash) => {
        if (err) {
          console.error(err);
          return res
            .status(500)
            .json({ error: "Internal server error" });
        }

        const userId = generateUserId();

        connection.query(
          "INSERT INTO users (userId, username, email, password) VALUES (?, ?, ?, ?)",
          [userId, username, email, hash],
          (err, results) => {
            if (err) {
              console.error(err);
              return res
                .status(500)
                .json({ error: "Internal server error" });
            }

            return res.status(200).json({
              status: "success",
              message: "User Created",
            });
          }
        );
      });
    }
  );
});

module.exports = router;