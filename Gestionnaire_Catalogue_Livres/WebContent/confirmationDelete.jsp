<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>Confirmation suppression.jsp</title>
</head>
<body>
	<div>
		<form method="post" action="deleteBook?id=${livre.id}">
			<div>
				<p>Voulez vous supprimer le livre : ${livre.id}</p>
			</div>
			<div>
				<button type="submit" class="btn btn-primary">Confirmer la suppression du livre</button>
				<a href="${pageContext.request.contextPath}/book">Annuler</a>
			</div>
		</form>
	</div>
</body>
</html>