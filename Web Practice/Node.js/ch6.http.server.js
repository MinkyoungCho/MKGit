//모듈 추출
var http = require('http');

//웹 서버 생성
var server = http.createServer();

//웹 서버 실행
server.listen(52273, function() {
  console.log('Server running at http://127.0.0.1:52273');
});

var test = function() {
  server.close();
};

setTimeout(test, 1000);
