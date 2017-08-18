
function finalizarCompra(){
	if (document.getElementById("idTrasacao").value != 0){
		$.ajax({
	        url: "rest/carrinho/finalizarCompra/"+document.getElementById("idTrasacao").value,
	        type: "GET",
	        dataType: "json",
	        success: function (data, textStatus, XMLHttpRequest) {
	        	alert("Sua compra está sendo processada. Ao finalizar enviaremos um Email. Obrigado pela Compra.");
	        	document.getElementById('idTrasacao').value = 0;
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        		alert(XMLHttpRequest.responseText + textStatus + errorThrown);
	        },
	    });
	} else {
		alert("Adicione outros produtos para finalizar uma compra!");
	}
}

function addItem(i, id, qtd){
	if (qtd > 0){
		var valor = "#idProduto" + i;
		$.ajax({
	        url: "rest/carrinho/addCarrinho/"+ id +"/"+document.getElementById("idTrasacao").value,
	        type: "GET",
	        dataType: "json",
	        success: function (data, textStatus, XMLHttpRequest) {
	        	document.getElementById('idTrasacao').value = data;
	        	alert("Produto adicionado ao carrinho.");
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	        		alert(XMLHttpRequest.responseText + textStatus + errorThrown);
	        },
	    });
	} else {
		alert("Produto esgotado!");
	}
}

function removeItem(id){
	if (document.getElementById("idTrasacao").value > 0){
		$.ajax({
	        url: "rest/carrinho/removeProdCarrinho/"+ id + "/" + document.getElementById("idTrasacao").value,
	        type: "GET",
	        dataType: "json",
	        success: function (data, textStatus, XMLHttpRequest) {
	        	document.getElementById('idTrasacao').value = data;
	        	alert("Removido Produto do Carrinho.");
	        	listProdCarrinho();
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	            alert(XMLHttpRequest.responseText);
	        },
	    });
	} else  {
		alert("Não produto na lista, ou sessão Inspirada. Atulize a Página.")
	}
}

function atulizarQtdProdVenda(idProduto, i){
	try{
		var qtdProduto = "qtdProduto" + i;
		var valida = document.getElementById(qtdProduto).value + "0";
		if (valida != "0" && valida != "00"){
			var qtdProduto = "qtdProduto" + i;
			$.ajax({
		        url: "rest/carrinho/atulizarQtdProdVenda/"+idProduto +"/"+ document.getElementById("idTrasacao").value +"/"+document.getElementById(qtdProduto).value,
		        type: "GET",
		        dataType: "json",
		        success: function (data, textStatus, XMLHttpRequest) {
		        	if (data.quantidade < document.getElementById(qtdProduto).value) {
		        		
		        		alert("Quantidade informada: (" +document.getElementById(qtdProduto).value+ 
		        			  "). Deve ser menor ou igual há quantidade em estoque: (" + data.quantidade+").");
		        		
		        	} else {
		        		alert("Atulizado quantidade, com sucesso!");
		        		listProdCarrinho();
		        	}
		           
		        },
		        error: function (XMLHttpRequest, textStatus, errorThrown) {
		        		alert(XMLHttpRequest.responseText + textStatus + errorThrown);
		        },
		    });
		}
		
		if (valida == "00"){
			alert("Quantidade informada, tem que ser maior que 0, Verifique!");
		}
	}catch (e){
		return;
	}
}

function listProdCarrinho(){
    // Realiza a consulta no banco
    $.ajax({
        url: "rest/carrinho/listProdCarrinho/" + document.getElementById("idTrasacao").value,
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, XMLHttpRequest) {
        	var list = data == null ? [] : (data instanceof Array ? data : [data]);
        	var text = "";
        	var i = 1;
            // Atualizar o Html na View
            $(list).each(function (index, produto) {
                text += "<tr>" +
						   "<td>" + produto.id + "</td>" +
						   "<td>" + produto.descricao + "</td>" +
						   "<td><input style=\"border: 0px; text-align:center;\" name=\""+ ("qtdProduto"+i) +"\" id=\""+ ("qtdProduto"+i) +"\" type=\"number\" value=\"" +produto.quantidade +"\" onkeypress=\"atulizarQtdProdVenda("+(produto.id +","+i)+")\"></td>" +
						   "<td>" + maskMoney(produto.valor) + "</td>" +
						   "<td>" + produto.obs + "</td>" + 
						   "<td><a onclick=\"removeItem("+produto.id+")\" ><img src=\"./img/delete-black.png\"></a>" +"</td>" +
						"</tr>";
                i++;
            });
            
            document.getElementById("tableCarriho").innerHTML = text;
            
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
        },
    });
}

function maskMoney(valor){
	var v = "";
	var i = 0;
	var contVir  = 0;
	var array = new String(valor);
	for (i; i < array.length; i ++){ 
		if (i == 3 && i == 6 && i == 9 && contVir == 0 && array.charAt(i) != '.'){
			v += ".";
		} else if (array.charAt(i) == '.') {
			contVir ++;
			v += v + ",";
		}
		try {
			if (contVir > 0){
				v += array.charAt(i + 1);
				v += array.charAt(i + 2);
				break;
			} else {
				v += array.charAt(i);				
			}
		}
		catch (e) {
			v += ",00";
			break;
		}
	}
	if (contVir == 0){
		v += ",00";
	}
		
	return "R$ " + v;
}



