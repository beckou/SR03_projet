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
Bienvenue dans votre page, Stagiare ${userID} </br>

Vos questionnaires possibles :

<fieldset>

 <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Quizz Name</th>
            <th>Action</th>

        </tr>
 
        <c:forEach var="quizz" items="${quizzList}">
            <tr>
                <td>${quizz.intitule}</td>
            	<td>        <form method="get" action="view_quizz_form">
            	                 				 <input type="hidden" name="userID" value="${userID}">
            	<button type="submit" name="quizzID" value="${quizz.id}">R�aliser le Quizz</button></form>
            	</td>
            </tr>
        </c:forEach>
    </table>
    
</fieldset>
</body>
</html>