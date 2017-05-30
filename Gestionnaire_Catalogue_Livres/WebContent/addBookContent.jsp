<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>ajouter un livre</title>
</head>
<body>
<div class="container col-md-10 col-md-offset-1">
	<div class="panel panel-primary ">
		<div class="panel-heading ">Saisie d'un livre </div>
		<div class="panel-body">
			<form action="sauvegarder" method="post">
				<%-- <div class="form-group">
					<label class="control-label" for="code">Code</label>
					<input type="text" class="form-control" name="code" value="${livre.id }">
					<span></span>
				</div> --%>
				<div class="form-group">
					<label class="control-label" for="titre">Titre</label>
					<input type="text" class="form-control" name="titre" value="${livre.title }">
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label" for="description">Description</label>
					<textarea rows="6"  class="form-control"  name="description">${livre.description }</textarea>
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label" for="auteur">Auteur</label>
					<input type="text" class="form-control" name="auteur" value="${livre.auteur }">
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label" for="prix">Prix</label>
					<input type="text" class="form-control" name="prix" value="${livre.price }">
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label" for="date">Date de publication</label>
					<input type="text" class="form-control" name="date" value="${livre.pubDate }">
					<span></span>
				</div>
				<div>
					<button type="submit" class="btn btn-primary">Soumettre</button>
				</div>
			</form>
		</div>
	</div>
</div>

</body>
</html>