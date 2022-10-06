<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="skateshop.css">
<meta charset="ISO-8859-1">
<title>All Entities</title>
</head>
<body>

<h1>Welcome to the Skateboard Shop</h1>

<nav>
<a href="deckindex.html">Decks</a>
<a href="editskatedeck">Add a Deck</a>
<a href="index.html">Boards</a>
<a href="editskate">Add a Board</a>
</nav>
<a href="entitylist">List all entities</a>
<table>
	<tr>
		<th>ID</th>
		<th>Brand</th>
		<th>Width</th>
		<th>Detail</th>
		<th>Edit</th>
		<th>Delete</th>
		<th>Skateboards</th>
	</tr>
<c:forEach var="var" items="${skateDecks}">
<tr>
	<td><c:out value="${var.id}"/></td>
	<td><c:out value="${var.brand}"/></td>
	<td><c:out value="${var.width}"/></td>
	<td><a href="detaildeck?id=${var.id}">Detail</a>
	<td><a href="editskatedeck?id=${var.id}">Edit</a></td>
	<td><a href="deleteskatedeck?id=${var.id}">Delete</a></td>
	<td>
	<c:forEach var="s" items="${var.skates}">
		<c:out value="${s.id }"/> 
	</c:forEach>
	</td>
</tr>
</c:forEach>
</table>
<c:if test="${skateDecks.size() == 0}"><p>There are no matching skateboard decks.</p></c:if>

<table>
	<tr>
		<th>ID</th>
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
	<td><c:out value="${var.id}"/></td>
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