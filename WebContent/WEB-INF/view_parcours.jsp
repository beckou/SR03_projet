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
Bienvenue dans votre page, Stagiare ${userID}]

Vos parcours réalisés :

<fieldset>

 <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Parcours ID</th>
            <th>Quizz Name</th>
            <th>score</th>
            <th>time</th>
            <th>Voir Réponses</th>

        </tr>
 
       <c:forEach var="parcours" items="${parcoursList}">
            <tr>
            
            
                <td>${parcours.id}</td>
                <td>${parcours.idQuizz}</td>
            	<td>${parcours.score}</td>
            	<td>${parcours.time}</td>
            	<td></td>
            	
            	
            </tr>
        </c:forEach>
    </table>
        
    <fieldset>
    
    "Temps moyen est : ${tMoyen}"
    </br>
    "Score moyen est : ${sMoyen}"
	<p><progress id="score" value="${sMoyen}" max="20"></progress></p>
    
    
    
    </fieldset>

    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="gestion_parcours?page=${currentPage - 1}">Previous</a></td>
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
                        <td><a href="gestion_parcours?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="gestion_parcours?page=${currentPage + 1}">Next</a></td>
    </c:if>

 <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>Parcours ID</th>
            <th>AnswerId</th>


        </tr>
 
       <c:forEach var="parcours" items="${answerList}">
            <tr>
            
            
                <td>${parcours.idParcours}</td>
                <td>${parcours.idAnswer}</td>

            	
            	
            </tr>
        </c:forEach>
    </table>

    
    
</fieldset>

<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail}</p>
</c:if>
</body>
</html>