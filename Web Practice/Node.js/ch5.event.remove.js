var onUncaughtException = function(error) {
  console.log('예외가 발생했군. 이번에만 봐주겠다!');

  process.removeListener('uncaughtException', onUncaughtException);
}

process.on('uncaughtException', onUncaughtException);

var test = function() {
  setTimeout(test, 2000);
  error.error.error();
}

setTimeout(test, 2000);
