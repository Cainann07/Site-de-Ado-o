/**
 * 
 */

function validar(){
	let email = formulario.email.value
	let senha = formulario.senha.value
	if(email === ""){
		alert("Preencha o campo email")
		formulario.nome.focus();
		return false;
	} else if (senha === ""){
		alert("Preencha o campo senha.")
		formulario.senha.focus();
		return false;
	} else {
		document.forms["formulario"].submit();
	}
}