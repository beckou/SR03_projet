<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
        <meta charset="utf-8" />
        <title>Inscription</title>
		<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />   
 </head>
    <body>
    <br>
    <p>${id}</p>
    <p>${user.mail}</p>
     <br>
        <form method="post" action="modify_user">
            <fieldset>
                <legend>Modifier utilisateur</legend>
                <p>Veuillez remplir les champs du nouvel utilisateur</p>

                <label for="email">Mail<span class="requis">*</span></label>
                <input type="text" id="email" name="email" value="${user.mail}" size="20" maxlength="60" />
                <br />

                <label for="motdepasse">Mot de passe <span class="requis">*</span></label>
                <input type="password" id="motdepasse" name="motdepasse" value="${user.password}" size="20" maxlength="20" />
                <br />

                <label for="nom">Nom d'utilisateur</label>
                <input type="text" id="nom" name="nom" value="${user.lastname}" size="20" maxlength="20" />
                <br />
                
                <label for="entreprise">Entreprise</label>
                <input type="text" id="entreprise" name="entreprise" value="${user.company}" size="20" maxlength="20" />
                <br />
                
                 <label for="tel">Téléphone</label>
                <input type="text" id="tel" name="tel" value="${user.phone}" size="20" maxlength="20" />
                <br />
                
                <label for="statut">Statut</label>
                <input type="text" id="statut" name="statut" value="${user.state}" size="20" maxlength="20" />
                <br />

                <input type="submit" value="Modifier" class="sansLabel" />
                <br />
            </fieldset>
        </form>
    </body>
</html>