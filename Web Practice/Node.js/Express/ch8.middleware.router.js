//모듈 추출
var express = require('express');

//서버 실행
var app = express();

//라우터 설정
app.get('/a', function (request, response) {
    response.send('<a href="/b">Go to B</a>');
});

app.get('/b', function (request, response) {
  response.send('<a href="/a">Go to A</a>');
});

app.get('/page/:id', function (request, response) {
  var name = request.params.id;
  response.send('<h1>' + name + 'Page</h1>');
});

app.all('*', function (request, response) {
  response.status(404).send('<h1>ERROR - Page Not Found</h1>');
});

app.listen(52273, function () {
  console.log('Server Running at http://127.0.0.1:52273');
});
