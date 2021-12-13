$(document).ready(function(e){
		executeHooks();

});


function executeHooks(){
var trueHooks = true;
if( typeof hooks === 'undefined'){
	trueHooks = false;
}
		
if(trueHooks){
	for(i=0;i<hooks.length;i++){
			hooks[i]();
		}
}

}