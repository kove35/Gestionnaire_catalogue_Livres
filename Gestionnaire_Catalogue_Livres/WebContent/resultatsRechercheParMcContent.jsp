<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>Resultat recherche par mot clé</title>
</head>
<body>
	<div>
	<table class="table table-striped">
		<tr>
			<th>CODE</th>
			<th>TITRE</th>
			<th>DESCRIPTION</th>
			<th>AUTEUR</th>
			<th>PRIX(euro)</th>
			<th>DATE DE PUBLICATION</th>
		</tr>
		<c:forEach var="livre" items="${livres}">
			<tr>
				<td>${livre.id}</td>
				<td>${livre.title}</td>
				<td>${livre.description}</td>
				<td>${livre.auteur}</td>
				<td>${livre.price}</td>
				<td>${livre.pubDate}</td>
				<td>
					<a href="${pageContext.request.contextPath}/Book" >Home</a>
				</td>
				
			</tr>
		</c:forEach>
	</table>
</div>	
</body>
</html>