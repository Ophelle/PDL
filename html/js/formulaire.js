$(document).ready(function(){
    // On verifie la validité des champs texte du formulaire
    $(":text").attr("required", "true");
    // On verifie Que les valeurs du champs number sont positives
    $(':input[type="number"]').attr( "min" , 0 );
    $(':input[type="number"]').attr("required", "True" );
    // On verifie la validité des champs date du formulaire
    $(':input[type="date"]').attr("required", "True","date", "true");


    
});
       

