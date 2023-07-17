<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Login</title>
	</head>
	<body>
		<h1>Iniciar Sesion</h1>
		<form action="/jakarta-ee9-webapp-shopping-cart-listeners/login" method="post">
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
	</body>
</html>