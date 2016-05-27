<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page Stagiaire</title>
</head>
<body>
Bienvenue dans votre page, Stagiare

Vos questionnaires possibles :

 <fieldset>
        <form method="get" action="view_quizz">

                <br />

                <input type="submit" value="Voir vos Questionnaires" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        </form>
        <form method="get" action="view_parcours">
             
                <br />

                <input type="submit" value="Voir Parcours réalisés" class="sansLabel" />
                <br />
                
                <p class="${empty form.erreurs ? 'succes' : 'erreur'}">${form.resultat}</p>
                
        </form>
        </fieldset>
</body>
</html>