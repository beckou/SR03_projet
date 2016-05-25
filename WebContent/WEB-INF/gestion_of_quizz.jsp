<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Questions</title>
</head>
<body>

 "GESTION DU QUESTIONNAIRE ${quizzID}"
<fieldset>

 <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Question ID</th>
            <th>Question Name</th>
            <th>Action</th>

        </tr>
 
        <c:forEach var="question" items="${questionList}">
            <tr>
                <td>${question.id}</td>
                <td>${question.intitule}</td>
            	<td>        <form method="get" action="gestion_answers">
            	<button type="submit" name="questID" value="${question.id}">Modify Question</button></form>
            	</td>
            	
            </tr>
        </c:forEach>
    </table>
    
    
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="gestion_of_quizz?page=${currentPage - 1}">Previous</a></td>
    </c:if>
 
    <%--For displaying Page numbers. 
    The when condition does not display a link for the current page--%>
    <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        <td>${i}</td>
                    </c:when>
                    <c:otherwise>
                        <td><a href="gestion_of_quizz?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="gestion_of_quizz?page=${currentPage + 1}">Next</a></td>
    </c:if>


    
    
    
</fieldset>

<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail}</p>
</c:if>
    
</body>
</html>