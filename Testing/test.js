
var toflip = "";
var flipped="";
var request = "http://localhost/meth/"



function check(enter)
{
    if(enter.keyCode === 13){
           route();
        }
}

function route(){
    var tosend = "javaflip(";
    toflip = document.getElementById('msg').value;
    tosend = tosend + toflip + ')\r';
    var address = request + tosend;
    getInfo(address);
    //
   
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
  request.onreadystatechange = 
    function() { build(request); }//call this to build the table.
  request.open("GET", address, true);
  request.send(null);
}
function build(request){
    //document.getElementById('error_id').innerHTML = ' '; 
    console.log(request.status);
    if ((request.readyState == 4) &&
      (request.status == 200)) {//If everything is good. 
    var lastcall =request.responseText;
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