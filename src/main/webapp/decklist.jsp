<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="skateshop.css">
<meta charset="ISO-8859-1">
<title>Skateboard Deck List</title>
</head>
<body>
<h1>Skateboard Deck List</h1>

<nav>
<a href="deckindex.html">Home</a>
<a href="index.html">Boards</a>
<a href="skatedecklist">List Decks</a>
<a href="editskatedeck">Add a Deck</a>
</nav>
<a href="entitylist">List all entities</a>
<table>
	<tr>
		<th>Brand</th>
		<th>Width</th>
		<th>Detail</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
<c:forEach var="var" items="${skateDecks}">
<tr>
	<td><c:out value="${var.brand}"/></td>
	<td><c:out value="${var.width}"/></td>
	<td><a href="detaildeck?id=${var.getId()}">Detail</a>
	<td><a href="editskatedeck?id=${var.getId()}">Edit</a></td>
	<td><a href="deleteskatedeck?id=${var.getId()}">Delete</a></td>
	<td><a href="skatelist?id=${var.getId()}">Skateboards</a>
	</td>
</tr>
</c:forEach>
</table>
<c:if test="${skateDecks.size() == 0}"><p>There are no matching skateboard decks.</p></c:if>
</body>
</html>