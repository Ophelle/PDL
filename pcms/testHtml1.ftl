<!DOCTYPE html>
<html>
<head>
	<title>${name} Version 0.00000001</title>
	<meta charset="utf8">
	   <style>
         fieldset{
            border: 1px solid #ccc;
            padding: 10px;
            margin-bottom: 20px;
        }
        legend{
            font-size: 14px;
            margin-bottom: 0px;
            width: auto;
            border-width: 0px;
            color: #ff6600;
            font-weight: bold;
            margin-left: 20px;
        }
        label{ font-weight : bold;}
       {text-align: right;top:20px;}
    </style>
</head>
<body>
	<fieldset>
		<table>
   			<legend style="color:red" >Formulaire</legend>
				<#list bestType as key, value>
					<#if value == "radio">
						<tr>
 		 					<td> <label>${key} :</td><td> <input type="${value}" name="Feature" value="oui"> oui <input type="${value}" name="Feature" value = "non"> non</br></br></label></td>
						</tr>
					<#else>
						<tr>
							<td> <label>${key} :</td><td> <input type="${value}" name="Feature"></br></br></label></td>
						</tr>
					</#if>
				</#list>
		</table>
		<button type="submit">Ajouter un produit</button>
	</fieldset>
		
</body>
</html>