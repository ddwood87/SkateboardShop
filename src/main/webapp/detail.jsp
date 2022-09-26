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
<a href="index.jsp">Home</a>
<a href="skatelist">List Skateboards</a>
<a href="editskate">Add a Skateboard</a>
</nav>

<table>
	<tr>
		<th>ID:</th>
		<th>Deck:</th>
		<th>Wheels:</th>
		<th>Trucks:</th>
	</tr>
	<tr>
		<td>${skate.getId()}</td>
		<td>${skate.getDeckBrand()}</td>
		<td>${skate.getWheelBrand()}</td>
		<td>${skate.getTruckBrand()}</td>
	</tr>
</table>
</body>
</html>