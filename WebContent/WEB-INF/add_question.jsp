<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>addQuestion</title>
<link type="text/css" rel="stylesheet" href="<c:url value="/inc/form.css"/>" />
</head>
<body>
        <form method="post" action="add_question">
            <fieldset>
                <legend>Nouvelle question</legend>
                <p>Veuillez remplir les champs de la nouvelle questionr pour le questionnaire ${quizzID}</p>

                <label for="intitule">Intitule</label>
                <input type="textarea" id="intitule" name="intitule" value="" />
                <br />
 				<input type="hidden" name="idQuestionnary" value="${quizzID}">
                <input type="submit" value="Créer" class="sansLabel" />
                <br />
            </fieldset>
        </form>
</body>
</html>