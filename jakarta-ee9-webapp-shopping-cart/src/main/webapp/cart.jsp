<%@page contentType="text/html" pageEncoding="UTF-8" import="com.kevinpina.model.*" %> 
<%
	ShoppingCart shoppingCart =  (ShoppingCart) session.getAttribute("shoppingCart");

%>
<html>
	<head>
		<title>View Cart</title>
	</head>
	<body>
		<h1>Shopping Cart</h1>
		<%
			if (shoppingCart == null || shoppingCart.getItemsCart().isEmpty()) {
				out.println("<p>We are sorry! there is no products in the shopping cart</p>");
			} else {
		%>
			<table border="1">
				<tr>
					<th>Id</th>
					<th>Name</th>
					<th>Price</th>
					<th>quantity</th>
					<th>Total</th>
				</tr>
				<%
					for (ItemCart itemCart : shoppingCart.getItemsCart()) { 
				%>
				<tr>
					<td><%= itemCart.getProduct().getId() %></td>
					<td><%= itemCart.getProduct().getName() %></td>
					<td><%= itemCart.getProduct().getPrice() %></td>
					<td><%= itemCart.getQuantity() %></td>
					<td><%= itemCart.getAmount() %></td>
				</tr>
				<% 
					} 
				%>
				<tr>
					<td colspan="4" style="text-align: right">Total</td>
					<td><%=shoppingCart.getTotal() %></td>
				</tr>
			</table>
		<%
			}
		%>
		<p><a href="<%=request.getContextPath()%>/products">Continue buying</a></p>
		<p><a href="<%=request.getContextPath()%>/index.html">Back</a></p>
	</body>
</html>