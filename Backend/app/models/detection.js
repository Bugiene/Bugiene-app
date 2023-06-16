const connection = require("../config/database");

function saveDetection(userId, detectionData) {
  return new Promise((resolve, reject) => {
    const query =
      "INSERT INTO detection (detectId, userId, imageUrl, freshness, accuracy, createdAt) VALUES (?, ?, ?, ?, ?, ?)";
    const values = [
      detectionData.detectId,
      userId,
      detectionData.imageUrl,
      detectionData.freshness,
      detectionData.accuracy,
      detectionData.createdAt,
    ];

    connection.query(query, values, (error, results) => {
      if (error) {
        console.error(error);
        reject(error);
      } else {
        resolve(results);
      }
    });
  });
}

function getDetectionHistory(userId) {
    const query =
    "SELECT detectId, userId, imageUrl, freshness, accuracy, createdAt FROM detection WHERE userId = ? ORDER BY createdAt DESC";
  const values = [userId];

  return new Promise((resolve, reject) => {
    connection.query(query, values, (error, results) => {
      if (error) {
        console.error(error);
        return reject("Internal server error");
      }

      const history = results.map((row) => ({
        detectId: row.detectId,
        userId: row.userId,
        imageUrl: row.imageUrl,
        freshness: row.freshness,
        accuracy: row.accuracy,
        createdAt: row.createdAt,
      }));

      resolve(history);
    });
  });
}

function getDetectionHistoryDetail(detectId) {
  const query =
    "SELECT detectId, userId, imageUrl, freshness, accuracy FROM detection WHERE detectId = ?";
  const values = [detectId];

  return new Promise((resolve, reject) => {
    connection.query(query, values, (error, results) => {
      if (error) {
        console.error(error);
        return reject("Internal server error");
      }

      if (results.length === 0) {
        return reject("History not found");
      }

      const history = {
        detectId: results[0].detectId,
        userId: results[0].userId,
        imageUrl: results[0].imageUrl,
        freshness: results[0].freshness,
        accuracy: results[0].accuracy,
      };

      resolve(history);
    });
  });
}

module.exports = {
  saveDetection,
  getDetectionHistory,
  getDetectionHistoryDetail,
};