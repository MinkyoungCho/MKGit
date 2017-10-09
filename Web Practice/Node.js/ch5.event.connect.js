process.on('exit', function(code) {
  console.log('안녕히 가거라..!');
});

process.on('uncaughtException', function(error) {
  console.log('예외 발생');
});

var count = 0;
var test = function() {
  count = count + 1;
  if (count > 3) {
    return;
  }

  setTimeout(test, 2000);
  error.error.error();
}
setTimeout(test, 2000);
