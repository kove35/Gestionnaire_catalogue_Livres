<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>Formulaire de recherche par mot clé</title>
</head>
<body>
<div class="container col-md-8 col-md-offset-1">
	<div class="panel-body ">
		<form action="${pageContext.request.contextPath}/confirmationRechercherParMc" method="post">
			<div class="form-group">
				<label class="control-label" for="motCle" >Entrez un mot clé :</label>
				<input type="text" name="motCle"   class="form-control" >
				<span></span>
			</div>
			<div>
				<button type="submit" class="btn btn-primary">Rechercher</button>
			</div>
		</form>
	</div>
</div>
</body>
</html>