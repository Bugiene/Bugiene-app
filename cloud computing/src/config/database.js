const mysql = require("mysql2");

// Konfigurasi koneksi database MySQL
const connection = mysql.createConnection({
  host: "127.0.0.1",
  user: "root",
  database: "coba",
  password: "",
});

module.exports = connection;