<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="skateshop.css">
<title>${addOrEdit} a Skateboard</title>
</head>
<body>
<h1>${addOrEdit} a Skateboard</h1>

<nav>
<a href="index.jsp">Home</a>
<a href="skatelist">List Skateboards</a>
<a href="editskate">Add a Skateboard</a>
</nav>
<form action="editskate" method="post">
	<p class="form-title">${addOrEdit} a Skateboard:</p>
	<input type="hidden" name="id" value="${id}" />
	<label for="deck">Deck</label>
	<input type="text" name="deck" value="${deck}" placeholder="Deck Brand"/>
	<label for="wheel">Wheel</label>
	<input type="text" name="wheel" value="${wheel}" placeholder="Wheel Brand"/>
	<label for="truck">Truck</label>
	<input type="text" name="truck" value="${truck}" placeholder="Truck Brand"/>
	<input type="submit" value="${addOrEdit} Skateboard"/>
	<input type="reset">
</form>
</body>
</html>