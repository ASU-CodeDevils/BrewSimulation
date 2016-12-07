/**
 * Simple web server based on 
 * http://expressjs.com/en/starter/hello-world.html
 *
 * Prerequisites:
 *  - Node
 *  - Express (npm install express)
 * 
 * To use, save as a file (e.g. SimpleServer.js) and run with:
 *  node SimpleServer.js /PATH/TO/WWW/
 */
 
// Parameters
var url = require('url');
var cookieParser = require('cookie-parser');
var responsefinal;
var sitePath = process.argv[2] || ".";
var port = 80;
var PORT = 80;
var net = require("net");
// Libraries
var express = require('express');
var app = express();
app.set('port',(process.env.PORT ||port));
app.use(express.static(__dirname + '/public'));
app.get('/meth/*',function(req,res){
    var url_parts = url.parse(req.url,true);
    var path = url_parts.path;
    var meth = path.substr(6,path.length);
    const client = net.createConnection(PORT,'jtestcd.herokuapp.com');
    meth = meth.replace(/%20/gi, " ");
    meth += '\r\n';
    console.log(meth);
    
    console.log(path);
    client.write(meth, 'UTF-8', function(){console.log('done');});
    client.on('data',function(data){
    var unwrap = data.toString();
    console.log(unwrap);
    responsefinal = unwrap;
    res.send(responsefinal);
    console.log("yeah buddy"); 
    client.end();
    });
    
    
});
// Request logging
app.use(function(req, res, next) {
    console.log(req.url);
    next();
});
 
// Start server
console.log(sitePath);
console.log("Starting server in: " + __dirname + '/' + sitePath);
app.use(express.static(__dirname + '/' + sitePath));
app.listen(app.get('port'), function() { 
    console.log("Server running at: http://localhost:" + port)
});


console.log('socket created!');

