<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>AddAnswer</title>
</head>
<body>
        <form method="post" action="add_answer">
            <fieldset>
                <legend>Nouvelle question</legend>
                <p>Veuillez remplir les champs de la nouvelle questionr pour la question ${QuestionID}</p>

                <label for="intitule">Intitule</label>
                <input type="textarea" id="intitule" name="intitule" value="" />
                <br />
                <input type="checkbox" id="BonneReponse" name="BonneReponse" value="BonneReponse" /> Reponse juste
                <br />
 				<input type="hidden" name="idQuestion" value="${questionID}">
                <input type="submit" value="Créer" class="sansLabel" />
                <br />
            </fieldset>
        </form>
</body>
</html>