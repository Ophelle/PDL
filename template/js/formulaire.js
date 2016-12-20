$(document).ready(function(){
    // On verifie la validité des champs texte du formulaire
    $(":text").attr("required", "true");
    // On verifie Que les valeurs du champs number sont positives
    $(':input[type="number"]').attr( "min" , 0 );
    $(':input[type="number"]').attr("required", "True" );
    // On verifie la validité des champs date du formulaire
    $(':input[type="date"]').attr("required", "True","date", "true");
    
});
function switchFunction(id1, id2)
{
    if(document.getElementById(id1).style.display=="none")
    {
        document.getElementById(id1).style.display="block";
        document.getElementById(id2).style.display="none";
    }
    else
    {
    	document.getElementById(id2).style.display="block";
        document.getElementById(id1).style.display="none";
    }
    return true;
}
       

