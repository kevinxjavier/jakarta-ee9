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
    </head>
    <body>
        <h3>User Form</h3>
        
        <%
        	if (errors != null && !errors.isEmpty()) {
        		out.println("<ol>");
        		for (String error : errors.values()) {
        			out.println("<li>" + error + "</li>");
        		}
        		out.println("</ol>");
        	}
         %>
        
        <form action="/jakarta-ee9-webapp/register" method="post">
			<div>
				<label for="username">Username</label>
				<div><input type="text" name="username" id="username" /></div>
				<% if (errors != null && errors.containsKey("username")) { out.println("<small style='color: red;'>" + errors.get("username") + "</small>"); } %>
			</div>
			<div>
				<label for="password">Password</label>
				<div><input type="password" name="password" id="password" /></div>
				<% if (errors != null && errors.containsKey("password")) { out.println("<small style='color: red;'>" + errors.get("password") + "</small>"); } %>
			</div>
			<div>
				<label for="email">Email</label>
				<div><input type="text" name="email" id="email" /></div>
				<% if (errors != null && errors.containsKey("email")) { out.println("<small style='color: red;'>" + errors.get("email") + "</small>"); } %>
			</div>
			<div>
				<label for="country">Country</label>
				<div>
					<select name="country" id="country">
						<option value="" selected>-- Select --</option>
						<option value="au">Austria</option>
						<option value="cl">Chile</option>
						<option value="fr">France</option>
						<option value="es">Spain</option>
						<option value="us">USA</option>
						<option value="ve">Venezuela</option>
					</select>
				</div>
				<% if (errors != null && errors.containsKey("country")) { out.println("<small style='color: red;'>" + errors.get("country") + "</small>"); } %>
			</div>
			<div>
				<label for="country_code">Country Code</label>
				<div>
					<select name="country_code" id="country_code" multiple>
						<option value="+43" >Austria</option>
						<option value="+56" selected>Chile</option>
						<option value="+995">Georgia</option>
						<option value="+34" selected>Spain</option>
						<option value="+1">USA</option>
					</select>
				</div>
				<% if (errors != null && errors.containsKey("country_code")) { out.println("<small style='color: red;'>" + errors.get("country_code") + "</small>"); } %>
			</div>
			<div>
				<label>Roles</label>
				<div>
					<input type="checkbox" name="roles" value="ROLE_ADMIN" />
					<label>Administrator</label>
				</div>
				<div>
					<input type="checkbox" name="roles" value="ROLE_USER" checked/>
					<label>User</label>
				</div>
				<div>
					<input type="checkbox" name="roles" value="ROLE_MODERATOR" />
					<label>Moderator</label>
				</div>
				<% if (errors != null && errors.containsKey("roles")) { out.println("<small style='color: red;'>" + errors.get("roles") + "</small>"); } %>
			</div>
			<div>
				<label>Language</label>
				<div>
					<input type="radio" name="language" value="en" />
					<label>English</label>
				</div>
				<div>
					<input type="radio" name="language" value="de" />
					<label>German</label>
				</div>
				<div>
					<input type="radio" name="language" value="es"/>
					<label>Spanish</label>
				</div>
				<% if (errors != null && errors.containsKey("language")) { out.println("<small style='color: red;'>" + errors.get("language") + "</small>"); } %>
			</div>
			<div>
				<input type="hidden" name="page" value="webapp" />
				<div><input type="submit" value="Submit" /></div>
			</div>
		</form>
        
        <hr/>
        <h6><a href="/jakarta-ee9-webapp/info?version=v0.1.0-alpha&name=webapp-SNAPSHOT">see info.</a></h6>
    </body>
</html>
