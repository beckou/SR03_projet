<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<p>


Gestion des users!!
</p>


<!-- 
 <table border="1" cellpadding="5" cellspacing="5">
        <tr>
            <th>User ID</th>
            <th>User Name</th>
        </tr>
 
        <c:forEach var="user" items="${userList}">
            <tr>
                <td>${user.id}</td>
                <td>${user.lastname}</td>
              
            </tr>
        </c:forEach>
    </table>
 
    <%--For displaying Previous link except for the 1st page --%>
    <c:if test="${currentPage != 1}">
        <td><a href="gestion_users?page=${currentPage - 1}">Previous</a></td>
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
                        <td><a href="gestion_users?page=${i}">${i}</a></td>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </tr>
    </table>
     
    <%--For displaying Next link --%>
    <c:if test="${currentPage lt noOfPages}">
        <td><a href="gestion_users?page=${currentPage + 1}">Next</a></td>
    </c:if>











-->

<c:if test= "${!empty userList}">
	<p class="succes">La liste de users n'est pas nulle! </p>
</c:if>


<c:forEach var="user" items="${userList}">
            
                <p>${user.id}</p>
                <br>
                <p>${user.lastname}</p>  <br>
             
            
</c:forEach>




<c:if test="${!empty sessionScope.sessionUtilisateur}">
	<%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
	<p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.sessionUtilisateur.mail}</p>
</c:if>
</body>
</html>