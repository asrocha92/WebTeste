function menuGrade(numGrade){
    // Realiza a consulta no banco
    $.ajax({
        url: "rest/produtos/listProdutoGrade/" + numGrade,
        type: "GET",
        dataType: "json",
        success: function (data, textStatus, XMLHttpRequest) {
        	
        	//passa o objeto serializado para uma lista
        	var list = data == null ? [] : (data instanceof Array ? data : [data]);
        	
        	var textInicial = "<div class=\"col-md-9\"><div class=\"row carousel-holder\"><div class=\"row\" id=\"itens\">";
        	var textFecha   = "</div></div></div>";
        	var text = textInicial;
        	var i = 1;
        	
        	$(list).each(function (index, produto) {
        		text += "<div class=\"col-sm-4 col-lg-4 col-md-4\"><div class=\"thumbnail\">" 
        			  + "<img style=\"height: 225px; width: 250px;\" src=\"./img/"+ produto.nFoto + ".jpg\" />" 
        			  + "<div class=\"caption\">"
        			  + "<h3>R$ "+ produto.valor +"</h3>"
        			  + "<h5><strong>Quantidade "+produto.quantidade+"<\strong><br>"+produto.obs+"</h5>" 
        			  + "<a href=\"#addItem\" onclick=\"addItem("+(i+","+produto.id+","+produto.quantidade)+")\">Add Carrinho</a>"
        			  + textFecha;
				if (i == 3){
					text += textFecha;
					text += textInicial;
				}
				i++;
            });
        	
        	text += textFecha;

        	document.getElementById("produto").innerHTML = text;     	
            return;
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            alert(XMLHttpRequest.responseText);
        },
    });
}
