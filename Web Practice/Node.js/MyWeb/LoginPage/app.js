//모듈 추출
var fs = require('fs');
var express = require('express');
var cookieParser = require('cookie-parser');
var bodyParser = require('body-parser');

//서버 생성
var app = express();

//미들웨어 설정
app.use(cookieParser());
app.use(bodyParser.urlencoded({extended: false}));

//라우터 설정
app.get('/', function (request, response) {
  if (request.cookies.auth) {
    response.send('<h1>Login Success</h1>');
  } else {
    response.redirect('/login');
  }
});

app.get('/login', function (request, response) {
  fs.readFile('login.html', function (error, data) {
    response.send(data.toString());
  });
});

app.post('/login', function (request, response) {
  //쿠키 생성
  var login = request.body.login;
  var password = request.body.password;

  //출력
  console.log(login, password);
  console.log(request.body);

  //로그인확인
  if (login == 'rint' && password == '1234') {
    response.cookie('auth', true);
    response.redirect('/');
  } else {
    response.redirect('/login');
  }
});

//서버 실행
app.listen(52273, function () {
  console.log('Server running at http://127.0.0.1:52273');
});
