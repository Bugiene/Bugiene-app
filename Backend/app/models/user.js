const connection = require("../config/database");

function getUser(userId, callback) {
  connection.query(
    "SELECT * FROM users WHERE userId = ?",
    [userId],
    (err, results) => {
      if (err) {
        console.error(err);
        return callback(err, null);
      }

      if (results.length === 0) {
        return callback(null, null);
      }

      const { username, email } = results[0];

      const user = {
        userId,
        username,
        email,
      };

      return callback(null, user);
    }
  );
}

module.exports = { getUser };