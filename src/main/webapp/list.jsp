<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="skateshop.css">
<meta charset="ISO-8859-1">
<title>Skateboard List</title>
</head>
<body>
<h1>Skateboard List</h1>

<nav>
<a href="index.html">Home</a>
<a href="deckindex.html">Decks</a>
<a href="skatelist">List Skateboards</a>
<a href="editskate">Add a Skateboard</a>
</nav>
<a href="entitylist">List all entities</a>
<table>
	<tr>
		<th>Deck Brand</th>
		<th>Deck Width</th>
		<th>Wheels</th>
		<th>Trucks</th>
		<th>Detail</th>
		<th>Edit</th>
		<th>Delete</th>
	</tr>
<c:forEach var="var" items="${skateboards}">
<tr>
	<td><c:out value="${var.deck.brand}"/></td>
	<td><c:out value="${var.deck.width}"/></td>
	<td><c:out value="${var.wheelBrand}"/></td>
	<td><c:out value="${var.truckBrand}"/></td>
	<td><a href="detail?id=${var.id}">Detail</a>
	<td><a href="editskate?id=${var.id}">Edit</a></td>
	<td><a href="deleteskate?id=${var.id}">Delete</a></td>
</tr>
</c:forEach>
</table>
<c:if test="${skateboards.size() == 0}"><p>There are no matching skateboards.</p></c:if>
</body>
</html>