<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="skateshop.css">
<title>Skateboard Detail</title>
</head>
<body>

<h1>Skateboard Detail</h1>

<nav>
<a href="index.html">Home</a>
<a href="deckindex.html">Decks</a>
<a href="skatelist">List Skateboards</a>
<a href="editskate">Add a Skateboard</a>
</nav>
<a href="entitylist">List all entities</a>

<table>
	<tr>
		<th>ID:</th>
		<th>Deck Brand:</th>
		<th>Width:</th>
		<th>Wheels:</th>
		<th>Trucks:</th>
	</tr>
	<tr>
		<td>${skate.id}</td>
		<td>${skate.deck.brand}</td>
		<td>${skate.deck.width}</td>
		<td>${skate.wheelBrand}</td>
		<td>${skate.truckBrand}</td>
	</tr>
</table>
</body>
</html>