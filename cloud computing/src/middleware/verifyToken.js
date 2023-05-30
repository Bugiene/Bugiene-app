const jwt = require("jsonwebtoken");

function verifyToken(req, res, next) {
  const authHeader = req.headers["authorization"];
  const token = authHeader && authHeader.split(" ")[1];

  if (!token) {
    return res.status(401).json({ error: "Access token not provided" });
  }

  jwt.verify(token, "secret_key", (err, decoded) => {
    if (err) {
      console.error(err);
      return res.status(403).json({ error: "Invalid access token" });
    }
    req.userId = decoded.userId;
    next();
  });
}

module.exports = verifyToken;