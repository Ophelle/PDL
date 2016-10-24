<!DOCTYPE html>
<html>
<head>
	<title>${name} Version 0.00000001</title>
	<meta charset="utf-8">
</head>
<body>
	<p>Coucou ${name}</p>
	<#list features as feat>
    	${feat_index + 1}. <label>${feat} : <input type="text" name="Feature"></br></br></label>
	</#list>
	<button type="submit">Ajouter le produit</button>
</body>
</html>