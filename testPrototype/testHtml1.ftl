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
   			<legend style="color:red" >Formulaire</legend>
   			<#assign i=0>
				<#list bestType as key, value>
					<#assign i++>
					<#if value == "radio">
 		 					<label>${key} :</label><br/>
 		 					 <input type="${value}" name="Feature${key}" value="oui">oui <input type="${value}" name="Feature${key}" value = "non">non </br></br>
								<#list getAllTypesValue as keys, valueAll>
									
						    			<#list valueAll as valueAll>
 		 									<#if valueAll=="string" >
 		 								 		<label>Autre</label><br/><br/><input type="text"/><#break><br/><br/>
 		 									</#if>		
					       				</#list>
					       				<#break>
								</#list>
					<#elseif value == "conditional">
 		 					<label>${key} :</label> <br/><br/><input type="${value}" name="Feature">  Condition : <input type="text" name="Feature"></br></br>
					<#elseif value == "checkbox">
							<label>${key} :</label><br/><br/>
 		 					 <#list ListMultiple as keys, valueMultiple>
 		 								<#list valueMultiple as valuemiltipe>
 		 									<input type="${value}" name="Feature" >${valuemiltipe}		
					       				</#list>
						   			</#list>
								<#list getAllTypesValue as keys, valueAll>
						    			<#list valueAll as valueAll>
 		 									<#if valueAll=="string" >
 		 										<label>Autre</label><br/><input type="text"/> <#break>
 		 									</#if>		
					       	            </#list>
					       	           <#break>
								</#list>
					<#else>
					<br/><br/>
							<label>${key} : </label><br/><br/> <input id="${i}" type="${value}" name="Feature"></br>
					</#if>
				</#list>
		<script>
		<#assign j=0>
		<#list allContentsCell as key, value>
          $("#<#assign j++>${j}").typeahead({
            name:"list${j}",
            local : [<#list value as  values>'${values}',</#list>'']
          });
        </#list>
    </script>
    <input type="submit" class="btn btn-info" value="Ajouter un produit" />
		</form></fieldset>
		</div>
		<div class="col-md-2">&nbsp;</div>
		</div>
		</div>
</body>
</html>