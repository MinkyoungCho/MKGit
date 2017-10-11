//모듈 추출
var express = require('express');
var morgan = require('morgan');

//서버 생성
var app = express();

//미들웨어 설정: use()의 parameter로 입력하는 함수 -> 요청의 응답을 완료하기 전까지 여러가지 일 처리하므로 <미들웨어>라 부름
app.use(morgan('combined'));
app.use(function (request, response) {
  response.send('<h1>express Basic</h1>');
});

//서버 실행
app.listen(52273, function () {
  console.log('Server Running at http://127.0.0.1:52273');
});
