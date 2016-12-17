<!DOCTYPE html>
<html>
<head>
	<link href="js/bootstrap.min.css" rel="stylesheet"type="text/css">
	<link href="css/moncss.css" rel="stylesheet"type="text/css">
	<script type="text/javascript" src="js/jquery-3.1.1.min.js"> </script>
	<script type="text/javascript" src="js/typeahead.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/formulaire.js"></script>
	<title>${name} Version 0.5</title>
	<meta charset="utf-8">
</head>

<body>
	<div class="container">
  	
		<div class="row">
			<div class="col-md-2">&nbsp;</div>
			<div class="col-md-8">
				<h1>${titre}</h1>
			</div>
			<div class="col-md-2">&nbsp;</div>
		</div>
		
		<div class="row">
			<fieldset>
				<div class="col-md-2">&nbsp;</div>
				<div class="col-md-8">
					<form action="" method="post">
						<legend style="color:red;font-weight: bold;font-style:italic;text-align: center;" >Formulaire</legend>
						<#assign i=0>
						<#list bestType as key, value>
							<#if value == "radio">
								</br>	
								<label>${key} :</label></br>
								<input type="${value}" name="Feature${key}" value="oui">Yes <input type="${value}" name="Feature${key}" value = "non">No <input type="${value}" name="Feature${key}" value = "non">Partial </br>
								<#list getAllTypesValue as keys, valueAll>
									<#list valueAll as valueAll>
										<#if valueAll=="string" >
											<input type="text"/></br><#break>
										</#if>		
									</#list>
									<#break>
								</#list>
								
							<#elseif value == "conditional">
								</br>	
								<label>${key} :</label> </br><input type="${value}" name="Feature">  Condition : <input type="text" name="Feature"></br>
								
							<#elseif value == "checkbox">
								</br>	
								<label>${key} :</label></br>
								 <#list ListMultiple as keys, valueMultiple>
									<#if key == keys>
										<#list valueMultiple as valuemiltipe>
											<input type="${value}" name="Feature" >${valuemiltipe}</br>	
										</#list>
									</#if>
								</#list>
								<#list getAllTypesValue as keys, valueAll>
									<#list valueAll as valueAll>
										<#if valueAll=="string" >
											<input type="checkbox" name="Feature" ><label> Autre : </label></br><input type="text"/></br> <#break>
										</#if>		
									</#list>
									<#break>
								</#list>
							
							<#elseif value == "text">
								</br>	
								<label>${key} : </label></br> <input id="${i}" type="${value}" name="Feature"></br>
								<#assign i++>				
							<#else>
								</br>	
								<label>${key} : </label></br> <input type="${value}" name="Feature"></br>
								<#list getAllTypesValue as keys, valueAll>
									<#list valueAll as valueAll>
										<#if value != "text">
											<#if valueAll=="string">
												<input type="text"/></br><#break>
											</#if>		
										</#if>		
									</#list>
									<#break>
								</#list>
								
							</#if>
						</#list>
						<script>
							<#assign j=0>
							<#list allContentsCell as key2, value>
								$("#${j}").typeahead({
									name:"${key2}",
									local : [<#list value as  values>'${values}',</#list>'']
								});
								<#assign j++>
							</#list>
						</script>
						<input type="submit" class="btn btn-info" value="Ajouter un produit" />
					</form>
				</div>
			</fieldset>
		<div class="col-md-2">&nbsp;</div>
		</div>
	</div>
</body>
</html>