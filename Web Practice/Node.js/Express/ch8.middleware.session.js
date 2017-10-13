//모듈 추출
var express = require('express');
var session = require('express-session');

//서버 생성
var app = express();

//미들웨어 설정
app.use(session({
  secret: 'secret key',
  resave: false,
  saveUninitialized: true
}));

app.use(function (request, response) {
  request.session.now = (new Date()).toString();

  response.send(request.session);
});

//서버 실행
app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
