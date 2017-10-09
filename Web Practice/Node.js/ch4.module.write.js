var fs = require('fs');

var data = 'Hello world';

fs.writeFile('TetFileOtherWrite.txt', data, 'utf8', function(error) {
  console.log('WRITE FILE ASYNC COMPLETE');
})

fs.writeFileSync('TetFileOtherWrite.txt', data, 'utf8');
console.log('WRITE FILE SYNC COMPLETE');
