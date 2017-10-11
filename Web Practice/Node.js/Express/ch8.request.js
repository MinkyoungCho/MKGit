//모듈 추출
var express = require('express');

//서버 생성
var app = express();

//미들웨어 설정
app.use(function (request, response) {
  //'User-Agent 속성 추출'
  var agent = request.header('User-Agent');
  if (agent.toLowerCase().match(/chrome/)) {
    response.send('<h1>Hello Chrome!</h1>')
  } else {
    response.send('<h1>Hello</h1>');
  }

  //응답
  response.sendStatus(200);
});

//서버 실행
app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
