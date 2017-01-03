/*
 * Copyright (c) 2016 ASU CodeDevils

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
documentation files (the "Software"), to deal in the Software without restriction, including without 
limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies 
of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial 
portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT 
NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, 
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

function packJson(classtype,method, param, param1, param2, param3, param4, param5, param6, param7){
	var topack = { 'Class':classtype,'Method':method, 'param':param, 'param1': param1, 'param2':param2,'param3':param3,'param4':param4, 'param5':param5,'param6':param6,'param7':param7,};
	var tosend = JSON.stringify(topack);
	console.log(tosend);
	return(tosend);
}



function getInfo(topost,callback) {
  var request = new XMLHttpRequest();
  request.onreadystatechange = function() {if (this.readyState == 4 && this.status == 200) {
	    var call = request.responseText;
        console.log(call, "return");
        
       localStorage.score = call;
	    callback(call);}
  };//call this to build the table.
  request.open("POST", "/meth", true);
  request.send(topost);
}
