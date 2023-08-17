<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- Using Directive include: Will not show the changes made in Header in this File unless changes we change something in this File --%>
<%@include file="layout/header.jsp" %>

		<h1>Iniciar Sesion</h1>
		<form action="/jakarta-ee9-webapp-shopping-cart-filters-database-crud-jstl-layout/login" method="post">
			<div>
				<label for="username">Username</label>
				<div>
					<input type="text" name="username" id="username" />
				</div>
			</div>
			<div>
				<label for="password">Password</label>
				<div>
					<input type="password" name="password" id="password" />
				</div>
			</div>
			<div>
				<input type="submit" value="Login" />
			</div>
			<a href="<%=request.getContextPath()%>/index.html">Volver</a>
		</form>

<%@include file="layout/footer.jsp" %>
