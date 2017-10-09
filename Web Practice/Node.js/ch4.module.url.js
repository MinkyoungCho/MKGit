var url = require('url');

var parsedObject = url.parse('http://www.hanbit.co.kr/stor/books/look.php?p_code=B4250257160', true);
console.log(parsedObject);
