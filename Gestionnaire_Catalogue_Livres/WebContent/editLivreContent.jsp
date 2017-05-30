<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
<title>Insert title here</title>
</head>
<body>
	<div class="container col-md-10 col-md-offset-1">
	<div class="panel panel-primary ">
		<div class="panel-heading "> Edition du livre : ${livre.title} </div>
		<div class="panel-body ">
			<form action="${pageContext.request.contextPath}/updateBook" method="post">
				<div class="form-group">
					<label class="control-label">Code</label>
					<input type="text" name="id" value="${livre.id }" class="form-control" >
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label">TITRE</label>
					<input type="text" name="titre" value="${livre.title}" class="form-control" >
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label">DESCRIPTION</label>
					<input type="text" name="description" value="${livre.description}" class="form-control" >
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label">AUTEUR</label>
					<input type="text" name="auteur" value="${livre.auteur}" class="form-control" >
					<span></span>
				</div>
				<div class="form-group">
					<label class="control-label">PRIX(euro)</label>
					<input type="text" name="price" value="${livre.price}" class="form-control" >
					<span></span>
				</div>
				
				<div class="form-group">
					<label class="control-label">DATE DE PUBLICATION</label>
					<input type="text" name="pubDate" value="${livre.pubDate}" class="form-control" >
					<span></span>
				</div>
				<div >
				<button type="submit" class="btn btn-primary">Sauvegarder</button>
				</div>
			</form>
			
		</div>
	</div>
</div>

	
</body>
</html>