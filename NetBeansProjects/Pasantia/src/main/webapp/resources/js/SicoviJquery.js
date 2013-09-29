/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
//Inicio jquery
$(document).ready(function() {
   
   
   
//  console.log("...cargando...") 
//   
//   
//    $("#gestionarusuarios:editarUsuario").click(function() {
//        console.log(".....Hize clic en editar Usuario");
//        $("#gestionarusuarios:cmbTipoPersona_input").change();
//    });

   
   
   
   
   
   
   
   
   
   
});
//Fin jquery

function clickpestañaPersonales(){
    $("#datperusu").click();
}

function permitirSoloNumeros(e) {
    var tecla;
	tecla = (document.all) ? e.keyCode : e.which;        
	if (tecla == 8) {
		return true; // Tecla de retroceso (para poder borrar)
	}
	var patron = /d/;
        var te;
        if(tecla>=48 && tecla <=57){
            te = String.fromCharCode(tecla);            
            return true;
        }else{
            te="";
            return false;
        }              
	
}

function resetWizard() {
    wizusu.loadStep(wizusu.cfg.steps[0], true);
}






