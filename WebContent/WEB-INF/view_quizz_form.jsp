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
                	<form method="post" action="view_quizz">

        <c:forEach var="questions" items="${questionList}">
                "Question : ${questions.question.intitule} "</br>
                 				<input type="hidden" name="idQuestion" value="${questions.question.id}">
                        <c:forEach var="answer" items="${questions.listeA}">
  							<input type="radio" name="${questions.question.id}" value="${answer.status}"> ${answer.intitule}<br>
        			</c:forEach>
        </c:forEach>
        </br>
            	<button type="submit" name="questID" value="">Send Results</button>
    </form>
</fieldset>
</body>
</html>