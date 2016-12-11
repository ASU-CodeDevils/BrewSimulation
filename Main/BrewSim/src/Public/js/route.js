

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
        console.log(call);
	    callback(call);
  } };//call this to build the table.
  request.open("POST", "/meth", true);
  request.send(topost);
}
