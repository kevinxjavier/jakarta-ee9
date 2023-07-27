<%@page contentType="text/html; charset=UTF-8" 
	import="java.util.Optional, java.util.List, com.kevinpina.model.Product" %>

<%
	List<Product> products = (List<Product>) request.getAttribute("products");
	Optional<String> usernameOptional = (Optional<String>) request.getAttribute("usernameOptional");
	
	String messageApplication = (String) request.getAttribute("message");
	String messageRequest = (String) getServletContext().getAttribute("message");
%>
<html lang="en">
	<head>
		<title>List Products</title>
	</head>
	<body>
		<h1>List Products</h1>
		
		<% if (usernameOptional.isPresent()) { %>
			<div> Hi Welcome <%=usernameOptional.get() %></div>
			<a href="<%=request.getContextPath()%>/product/form">Create [+]</a>
		<% } %>
		
		<table border="1" cellspacing="0" cellpadding="4">
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Type</th>
				
				<% if (usernameOptional.isPresent()) { %>
					<th>Price</th>
					<th>Add</th>
					<th>Edit</th>
				<% } %>
				
			</tr>
			<% for (Product product : products) { %>
			<tr>
				<td><%= product.getId() %></td>
				<td><%= product.getName() %></td>
				<td><%= product.getCategory().getName() %></td>
				
				<% if (usernameOptional.isPresent()) { %>
					<td><%= product.getPrice() %></td>
					<td><a href="<%= request.getContextPath() %>/cart/add?id=<%= product.getId() %>">Add</a></td>
					<td><a href="<%= request.getContextPath() %>/product/form?id=<%= product.getId() %>">Edit</a></td>
				<% } %>

			</tr>
			<% } %>
		</table>
		
		<p><b>Message Application: </b><%= messageApplication %></p>
		<p><b>Message Request: </b><%= messageRequest %></p>
		<p><a href="<%=request.getContextPath()%>/index.html">Back</a></p>
	</body>
</html>
