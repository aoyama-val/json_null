var fs = require('fs');
var json = fs.readFileSync("sample1.json", "utf-8");
var obj = JSON.parse(json);
console.log(obj);
