function abrirTelaBase(caminho){	
	$(document).ready(function () {
		/*document.getElementById('base').hide();
		document.getElementById('base').innerHTML = "<object width=\"100%\" height=\"500px\" data=\"" + caminho +"\"></object>";*/
		$('#base').load(caminho);
	});
}