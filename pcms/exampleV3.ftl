<!DOCTYPE html>
<html>
Style{
input:color:red;
}
<head>
	<title>${name} Version 0.00000001</title>
	<meta charset="utf-8">
</head>
<body>
	<p>Coucou ${name}</p>
	<#list best as key, value>
		<#if value == "radio">
 			${key_index + 1}. <label>${key} : <input type="${value}" name="Feature" value="oui"> oui <input type="${value}" name="Feature" value = "non"> non</br></br></label>
		<#else>
			${key_index + 1}. <label>${key} : <input type="${value}" name="Feature"></br></br></label>
		</#if>
	</#list>
	<button type="submit">Ajouter le produit</button>
</body>
</html>