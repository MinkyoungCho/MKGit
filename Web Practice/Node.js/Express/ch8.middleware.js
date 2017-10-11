var express = require('express');
var app = express();

//use(): request 이벤트리스너 연결함
app.use(function (request, response, next) {
  console.log('First Middleware');
  next();
});

app.use(function (request, response, next) {
  console.log('Second Middleware');
  next();
});

app.use(function (request, response, next) {
  console.log('Third Middleware');

  response.writeHead(200, { 'Content-Type' : 'text/html' });
  response.end('<h1>express Basic</h1>');
});

app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
