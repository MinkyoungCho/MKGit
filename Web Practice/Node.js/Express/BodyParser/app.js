//모듈 추출
var fs = require('fs');
var express = require('express');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

//서버 생성
var app = express();

//미들웨어 설정
app.use(cookieParser());
app.use(bodyParser.unlencoded({extended: false}));

//라우터 설정
app.get('/', function (request, response) {});
app.get('/login', function (request, response) {});
app.post('/login', function (request, response) {});

//서버 실행
app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
