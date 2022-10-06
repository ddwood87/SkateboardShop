<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="skateshop.css">
<title>${addOrEdit} a Skateboard Deck</title>
</head>
<body>
<h1>${addOrEdit} a Skateboard Deck</h1>

<nav>
<a href="deckindex.html">Home</a>
<a href="index.html">Boards</a>
<a href="skatedecklist">List Decks</a>
<a href="editskatedeck">Add a Deck</a>
</nav>
<a href="entitylist">List all entities</a>

<form action="editskatedeck" method="post">
	<p class="form-title">${addOrEdit} a Skateboard Deck:</p>
	<input type="hidden" name="id" value="${id}" />
	<label for="brand">Brand</label>
	<input type="text" name="brand" value="${brand}" placeholder="Deck Brand"/>
	<label for="wheel">Wheel</label>
	<input type="number" name="width" value="${width}" min="7" max="10" step=".05" placeholder="Deck Width"/>
	<input type="submit" value="${addOrEdit} Skateboard Deck"/>
	<input type="reset">
	<label><c:out value="${message}"/></label>
</form>
</body>
</html>