<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- Using Tag include: Will show the changes made in Header File in this file without changes in this file --%>
<%-- Also the request.setAttribute in his Servlet won't work here after the redirect tp his jsp, better use then the Directive Include --%>
<jsp:include page="layout/header.jsp" />

		<h3>User Form</h3>
		<h6>
			<h5>Session HTTP</h5>
			<a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/products.html">Products</a><br/>
			<a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/login.html">Login</a><br/>
			<a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/logout">Logout</a><br/>
			<a href="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/cart/view">View Cart</a><br/> 
		</h6>
	</body>
</html>

<jsp:include page="layout/footer.jsp" />
