<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- Using Tag include: Will show the changes made in Header File in this file without changes in this file --%>
<%-- Also the request.setAttribute in his Servlet won't work here after the redirect tp his jsp, better use then the Directive Include --%>
<jsp:include page="layout/header.jsp" />

		<h3>User Form</h3>
		<h6>
			<h5>Session HTTP</h5>
			<ul class="list-group">
			<li class="list-group-item active">Option Menu</li>
			<li class="list-group-item"><a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/products.html">Products</a></li>
			<li class="list-group-item"><a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/login.jsp">Login</a></li>
			<li class="list-group-item"><a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/logout">Logout</a></li>
			<li class="list-group-item"><a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/cart/view">View Cart</a></li>
			</ul> 
		</h6>
	</body>
</html>

<jsp:include page="layout/footer.jsp" />
