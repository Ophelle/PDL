<!DOCTYPE html>
<html>
<head>
<link href="css/moncss.css" rel="stylesheet"type="text/css">
<script type="text/javascript" src="js/jquery-3.1.1.min.js"> </script>
<script type="text/javascript" src="js/typeahead.min.js"></script>
<script type="text/javascript" src="js/formulaire.js"></script>
	<title>${name} Version 0.5</title>
	<meta charset="utf-8">
</head>
<body>
	<h1>${titre}</h1>
	<fieldset>
		<table>
   			<legend style="color:red" >Formulaire</legend>
   			<#assign i=0>
				<#list bestType as key, value>
					<#assign i++>
					<#if value == "radio">
						<tr>
 		 					<td> <label>${key} :</td><td> <input type="${value}" name="Feature${key}" value="oui"> oui <input type="${value}" name="Feature${key}" value = "non"> non</br></br></label></td>
						
								<#list getAllTypesValue as keys, valueAll>
									
						    			<#list valueAll as valueAll>
 		 									<#if valueAll=="string" >
 		 								 		<td>autre<input type="text"/></td><#break>
 		 									</#if>		
					       				</#list>
					       				<#break>
								</#list>
						</tr>
					<#elseif value == "conditional">
						<tr>
 		 					<td> <label>${key} :</td><td> <input type="${value}" name="Feature">  Condition : <input type="text" name="Feature"></br></br></label></td>
						</tr>
					<#elseif value == "checkbox">
						<tr>
							<td> <label>${key} :</td>
 		 					  <td>	<#list ListMultiple as keys, valueMultiple>
 		 								<#list valueMultiple as valuemiltipe>
 		 									<input type="${value}" name="Feature" >${valuemiltipe}		
					       				</#list>
						   			</#list>
						   	  </td>
						   	
								<#list getAllTypesValue as keys, valueAll>
						    			<#list valueAll as valueAll>
 		 									<#if valueAll=="string" >
 		 										<td>autre<input type="text"/> </td><#break>
 		 									</#if>		
					       	            </#list>
					       	           <#break>
								</#list>
						</tr>
					<#else>
						<tr>
							<td> <label>${key} :</td><td> <input id="${i}" type="${value}" name="Feature"></br></br></label></td>
						</tr>
					</#if>
					
					
				</#list>
		</table>
		<button type="submit">Ajouter un produit</button>
	</fieldset>
	<script>
		<#assign j=0>
		<#list allContentsCell as key, value>
          $("#<#assign j++>${j}").typeahead({
            name:"list${j}",
            local : [<#list value as  values>'${values}',</#list>'']
          });
        </#list>
        </script>
		
</body>
</html>