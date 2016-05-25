<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Admin_01</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />   
 </head>
    <body>
    <fieldset>
        <form method="get" action="gestion_users">
                <legend>Bienvenue Administrateur</legend>
                <p>Voici vos actions possibles: </p>

            
                <br />

                <input type="submit" value="Gestion utilisateurs" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        </form>
        <form method="get" action="gestion_quizz">
             
                <br />

                <input type="submit" value="Gestion QCM" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        </form>
        <form method="get" action="gestion_parcours">
             
                <br />

                <input type="submit" value="Gestion parcours" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        </form>
        
        </fieldset>
    </body>
</html>