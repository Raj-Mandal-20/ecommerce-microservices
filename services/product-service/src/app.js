const express = require("express");
const http = require('http');
const bodyParser = require('body-parser');
require('dotenv').config();
const fs = require('fs');
const path = require('path');
const { default: mongoose } = require("mongoose");
const productRoute = require('./routes/Product');
const app = express();

const uploadDir = 'images';
if (!fs.existsSync(uploadDir)) {
  fs.mkdirSync(uploadDir);
}


app.use(bodyParser.json());
app.use(express.urlencoded({ extended: true }));


// serve static images
app.use('/images', express.static(path.join(__dirname, 'images')));


// setting up CORS policies
app.use((req, res, next) => {
  res.setHeader("Access-Control-Allow-Origin", "*");
  res.setHeader(
    "Access-Control-Allow-Methods",
    "GET, POST, PUT, PATCH, DELETE, OPTIONS"
  );
  res.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
  
  if (req.method === 'OPTIONS') {
    return res.sendStatus(200);
  }

  next();
});


app.use(productRoute);



// global error handler
app.use((error, req, res, next) => {
  console.error(error); // Correct logging

  const statusCode = error.statusCode || 500;
  const status = error.status || 'error';

  res.status(statusCode).json({
    status,
    message: error.message
  });
});


mongoose.connect(process.env.MONGO_URI).then(() => {
  const server = http.createServer(app);
  const PORT = process.env.PORT || 6565;
  console.log('DataBase Connected!');

  server.listen(PORT, () => {
    console.log("Server is Running on PORT = "+PORT);
  });

});