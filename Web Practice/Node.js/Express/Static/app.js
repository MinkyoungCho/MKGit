//모듈 추출
var express = require('express');

//서버 생성
var app = express();

//미들웨어 설정
//  static 미들웨어: 지정한 폴더에 있는 내용을 모두 웹 서버 루트 폴더에 올림!
app.use(express.static(__dirname + '/Public')); //폴더 지정
app.use(function (request, response) {
  response.writeHead(200, {'Content-Type': 'text/html'});
  response.end('<img src = "/apple.png" width="30%" />');
});

//서버 실행
app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
