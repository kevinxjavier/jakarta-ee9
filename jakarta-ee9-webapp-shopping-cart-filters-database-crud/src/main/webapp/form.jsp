<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page
	import="
	java.time.format.DateTimeFormatter, 
	java.util.List, 
	java.util.Map, 
	java.util.Optional, 
	com.kevinpina.model.Category, 
	com.kevinpina.model.Product"%>
<%
List<Category> categories = (List<Category>) request.getAttribute("categories");

Optional<Product> product = (Optional<Product>) request.getAttribute("product");

Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");

String date = product.isPresent() ? product.get().getDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Form</title>
</head>
<body>
	<h1>Product Form</h1>

	<form action="<%=request.getContextPath()%>/product/form" method="post">

		<div>
			<label for="name">Name</label>
			<div>
				<input type="text" name="name" id="name"
					value="<%=product.isPresent() ? product.get().getName() : ""%>">
			</div>
			<%
			if (errors != null && errors.containsKey("name")) {
				out.println("<div style=\"color: red;\">" + errors.get("name") + "</div>");
			}
			%>
		</div>

		<div>
			<label for="price">Price</label>
			<div>
				<input type="number" name="price" id="price"
					value="<%=product.isPresent() ? product.get().getPrice() : ""%>">
			</div>
			<%
			if (errors != null && errors.containsKey("price")) {
				out.println("<div style=\"color: red;\">" + errors.get("price") + "</div>");
			}
			%>
		</div>

		<div>
			<label for="sku">Sku</label>
			<div>
				<input type="text" name="sku" id="sku"
					value="<%=product.isPresent() ? product.get().getSku() : ""%>">
			</div>
			<%
			if (errors != null && errors.containsKey("sku")) {
				out.println("<div style=\"color: red;\">" + errors.get("sku") + "</div>");
			}
			%>
		</div>

		<div>
			<label for="date">Date</label>
			<div>
				<input type="date" name="date" id="date" value="<%=date%>">
			</div>
			<%
			if (errors != null && errors.containsKey("date")) {
				out.println("<div style=\"color: red;\">" + errors.get("date") + "</div>");
			}
			%>
		</div>

		<div>
			<label for="category">Category</label>
			<div>
				<select name="category" id="category">
					<option value="">--- Select ---</option>
					<%
					for (Category category : categories) {
					%>
					<option value="<%=category.getId()%>"
						<%=product.isPresent() ? (category.getId().equals(product.get().getId()) ? "selected" : "") : ""%>><%=category.getName()%></option>
					<%
					}
					%>
				</select>
			</div>
			<%
			if (errors != null && errors.containsKey("category")) {
				out.println("<div style=\"color: red;\">" + errors.get("category") + "</div>");
			}
			%>
		</div>

		<input type="submit" value="Create">
		<p>
			<a href="<%=request.getContextPath()%>/index.html">Back</a>
		</p>

	</form>
</body>
</html>