<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<title>View Cart</title>
	</head>
	<body>
		<h1>Shopping Cart</h1>
		<!-- shoppingCart es un elemento unico en los scopes así que podemos obviar sessionScope -->
		<c:choose>
			<c:when test ="${sessionScope.shoppingCart == null || shoppingCart.itemsCart.isEmpty()}">
				<p>We are sorry! there is no products in the shopping cart</p>
			</c:when>
			<c:otherwise>
				<table border="1">
					<tr>
						<th>Id</th>
						<th>Name</th>
						<th>Price</th>
						<th>quantity</th>
						<th>Total</th>
					</tr>
					<c:forEach items="${sessionScope.shoppingCart.itemsCart}" var="itemCart">
						<tr>
							<td>${itemCart.product.id}</td>
							<td>${itemCart.product.name}</td>
							<td>${itemCart.product.price}</td>
							<td>${itemCart.quantity}</td>
							<td>${itemCart.amount}</td>
						</tr>
					</c:forEach>
					<tr>
						<td colspan="4" style="text-align: right">Total</td>
						<td>${sessionScope.shoppingCart.total}</td>
					</tr>
				</table>
			</c:otherwise>
		</c:choose>
		<p><a href="${pageContext.request.contextPath}/products">Continue buying</a></p>
		<p><a href="<%=request.getContextPath()%>/index.html">Back</a></p>
	</body>
</html>