
var toflip = "";
var flipped="";




function check(enter)
{
    if(enter.keyCode === 13){
           route();
        }
}

function route(){
    
    
  
    var address = document.getElementById('msg').value;
    //getInfo(address);
    //
    getInfo(packJson("hello","hello","hello",1,2));
   
}
function packJson(method, param, param1, param2, param3, param4, param5, param6, param7){
	var topack = { 'Method':method, 'param':param, 'param1': param1, 'param2':param2,'param3':param3,'param4':param4, 'param5':param5,'param6':param6,'param7':param7,};
	var tosend = JSON.stringify(topack);
	console.log(tosend);
	return(tosend);
}


function done(javaflipped){
    flipped = javaflipped;
    console.log(flipped);
     var sent = document.getElementById('before_id'); 
 
     var switched = document.getElementById('after_id'); 
     sent.innerHTML = toflip; 
     switched.innerHTML =flipped; 
    
}
function getInfo(address) {
  var request = new XMLHttpRequest();
  request.onreadystatechange = function() {if (this.readyState == 4 && this.status == 200) {
	    var call = request.responseText;
	    console.log(call);
  } };//call this to build the table.
  request.open("POST", "/meth", true);
  request.send(address);
}
function build(request){
    //document.getElementById('error_id').innerHTML = ' '; 
    console.log(request.status);
    if ((request.readyState == 4) &&
      (request.status == 200)) {//If everything is good. 
    var lastcall =request.responseText;
    jsonPack(lastcall);
    var first = lastcall.indexOf('(');
    var last = lastcall.indexOf(')');
    var meth = lastcall.substr(0,first);
    console.log(last);
    last = last -1;
    console.log(last);
    console.log(meth);
    console.log(lastcall);
    var param = lastcall.substr((first+1));
    last = param.indexOf(')');
    param = param.substr(0,last);
    var fnparam = [param];
    console.log(param);
    var fn = window[meth];
    if(typeof fn === "function"){
        fn.apply(null, fnparam);
           
    
        
            
  }
}
}
function jsonPack(result){
    
   
    console.log(result);
    var unpacked = JSON.parse(result);
    unpacked.Bool = true;
    console.log(unpacked);
    console.log(unpacked.Bool);
    
}