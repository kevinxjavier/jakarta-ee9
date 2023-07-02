<%@page contentType="text/html" pageEncoding="uTF-8" %>
<%@page import="java.util.Map" %>

<%
	Map<String, String> errors = (Map<String, String>) request.getAttribute("errors");
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<title>Index</title>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
	</head>
	<body>
		<h3>User Form</h3>
		
		<%
			if (errors != null && !errors.isEmpty()) {
				out.println("<ol class='alert alert-danger mx-5 px-5'>");
				for (String error : errors.values()) {
					out.println("<li>" + error + "</li>");
				}
				out.println("</ol>");
			}
			%>
         
		<!--
			<div class="row mb-3"> Means Margin Button 3
			<input type"submit" class="btn btn-primary"> Means btn-primary is color blue 
			<div class="px-5"> Means padding 5
			<div class="form-check col-sm-2"> With col-sm-2 we put the checkbox horizontally
		-->

		<div class="px-5">
			<form action="<%=request.getContextPath()%>/register" method="post">
				<div class="row mb-3">
					<label for="username" class="col-form-label col-sm-2">Username</label>
					<div class="col-sm-4"><input type="text" name="username" id="username" class="form-control" value="${param.username}" /></div>
					<% if (errors != null && errors.containsKey("username")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("username") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label for="password" class="col-form-label col-sm-2">Password</label>
					<div class="col-sm-4"><input type="password" name="password" id="password" class="form-control" /></div>
					<% if (errors != null && errors.containsKey("password")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("password") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label for="email" class="col-form-label col-sm-2">Email</label>
					<div class="col-sm-4"><input type="text" name="email" id="email" class="form-control" value="${param.email}" /></div>
					<% if (errors != null && errors.containsKey("email")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("email") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label for="country" class="col-form-label col-sm-2">Country</label>
					<div class="col-sm-4">
						<select name="country" id="country" class="form-select">
							<option value="" selected>-- Select --</option>
							<option value="au" ${param.country.equals("au") ? "selected" : ""}>Austria</option>
							<option value="cl" ${param.country.equals("cl") ? "selected" : ""}>Chile</option>
							<option value="fr" ${param.country.equals("fr") ? "selected" : ""}>France</option>
							<option value="es" ${param.country.equals("es") ? "selected" : ""}>Spain</option>
							<option value="us" ${param.country.equals("us") ? "selected" : ""}>USA</option>
							<option value="ve" ${param.country.equals("ve") ? "selected" : ""}>Venezuela</option>
						</select>
					</div>
					<% if (errors != null && errors.containsKey("country")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("country") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label for="country_code" class="col-form-label col-sm-2">Country Code</label>
					<div class="col-sm-4">
						<select name="country_code" id="country_code" class="form-select" multiple>
							<option value="+43" ${paramValues.country_code.stream().anyMatch(e -> e.equals("+43")).get() ? "selected" : ""} >Austria</option>
							<option value="+56" ${paramValues.country_code.stream().anyMatch(e -> e.equals("+56")).get() ? "selected" : ""} >Chile</option>
							<option value="+995" ${paramValues.country_code.stream().anyMatch(e -> e.equals("+995")).get() ? "selected" : ""} >Georgia</option>
							<option value="+34" ${paramValues.country_code.stream().anyMatch(e -> e.equals("+34")).get() ? "selected" : ""} >Spain</option>
							<option value="+1" ${paramValues.country_code.stream().anyMatch(e -> e.equals("+1")).get() ? "selected" : ""} >USA</option>
						</select>
					</div>
					<% if (errors != null && errors.containsKey("country_code")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("country_code") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label class="col-form-label col-sm-2">Roles</label>
					<div class="form-check col-sm-2">
						<input type="checkbox" name="roles" value="ROLE_ADMIN" class="form-check-input" ${paramValues.roles.stream().anyMatch(e -> e.equals("ROLE_ADMIN")).get() ? "checked" : ""} />
						<label class="form-check-label">Administrator</label>
					</div>
					<div class="form-check col-sm-2">
						<input type="checkbox" name="roles" value="ROLE_USER" class="form-check-input" ${paramValues.roles.stream().anyMatch(e -> e.equals("ROLE_USER")).get() ? "checked" : ""} />
						<label class="form-check-label">User</label>
					</div>
					<div class="form-check col-sm-2">
						<input type="checkbox" name="roles" value="ROLE_MODERATOR" class="form-check-input" ${paramValues.roles.stream().anyMatch(e -> e.equals("ROLE_MODERATOR")).get() ? "checked" : ""} />
						<label class="form-check-label">Moderator</label>
					</div>
					<% if (errors != null && errors.containsKey("roles")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("roles") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<label class="col-form-label col-sm-2">Language</label>
					<div class="form-check col-sm-2">
						<input type="radio" name="language" value="en" class="form-check-input" ${param.language.equals("en") ? "checked" : ""} />
						<label class="form-check-label">English</label>
					</div>
					<div class="form-check col-sm-2">
						<input type="radio" name="language" value="de" class="form-check-input" ${param.language.equals("de") ? "checked" : ""} />
						<label class="form-check-label">German</label>
					</div>
					<div class="form-check col-sm-2">
						<input type="radio" name="language" value="es" class="form-check-input" ${param.language.equals("es") ? "checked" : ""} />
						<label class="form-check-label">Spanish</label>
					</div>
					<% if (errors != null && errors.containsKey("language")) { out.println("<small class='alert alert-danger col-sm-4' style='color: red;'>" + errors.get("language") + "</small>"); } %>
				</div>
				<div class="row mb-3">
					<input type="hidden" name="page" value="webapp" />
					<div><input type="submit" value="Submit" class="btn btn-primary" /></div>
				</div>
			</form>
        </div>

		<hr/>
		<h6><a href="/jakarta-ee9-webapp/info?version=v0.1.0-alpha&name=webapp-SNAPSHOT">see info.</a></h6>
	</body>
</html>
