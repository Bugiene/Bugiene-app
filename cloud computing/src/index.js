const express = require('express');
const registerRoute = require("./routes/register");
const loginRoute = require("./routes/login");
const getUserRoute = require("./routes/getUser");

const app = express();
app.use(express.json());

app.get('/', (req, res) => {   
  res.status(200).json({     
      message: 'Hello world'   
  }) 
}) 

// Rute untuk registrasi
app.use("/register", registerRoute);

// Rute untuk login
app.use("/login", loginRoute);

// Rute untuk mendapatkan data pengguna
app.use("/getUser", getUserRoute);

// Jalankan server
const port = process.env.PORT || 3000;
app.listen(port, () => {
  console.log(`Listening: http://localhost:${port}`);
});







