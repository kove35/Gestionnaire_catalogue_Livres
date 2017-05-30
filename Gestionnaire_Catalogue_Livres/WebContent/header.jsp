


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Header</title>
	</head>
	<body>
		<div>
			<a href="${pageContext.request.contextPath}/book">
			<img src="${pageContext.request.contextPath}/resources/images/logo-book-shop.jpg"/>
			</a>
		</div>
	
		<div class="navbar navbar-default">
			<ul class="nav navbar-nav">
				<li>
					<a href="${pageContext.request.contextPath}/book">Home</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/ajouter">Ajouter un livre</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/book">Emprunter un livre</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/book">Rendre un livre</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/book">Recherche un livre par mot clé</a>
				</li>
				<li>
					<a href="${pageContext.request.contextPath}/book">Supprimer un livre</a>
				</li>
			</ul>
		</div>
	</body>
</html>