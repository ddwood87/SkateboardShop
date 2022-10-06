<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="skateshop.css">
<title>Skateboard Deck Detail</title>
</head>
<body>

<h1>Skateboard Deck Detail</h1>

<nav>
<a href="deckindex.html">Home</a>
<a href="index.html">Boards</a>
<a href="skatedecklist">List Decks</a>
<a href="editskatedeck">Add a Deck</a>
</nav>
<a href="entitylist">List all entities</a>

<table>
	<tr>
		<th>ID: </th>
		<th>Brand: </th>
		<th>Width: </th>
	</tr>
	<tr>
		<td>${skateDeck.getId()}</td>
		<td>${skateDeck.getBrand()}</td>
		<td>${skateDeck.getWidth()}</td>
	</tr>
</table>
</body>
</html>