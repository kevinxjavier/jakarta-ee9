package com.kevinpina.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request-header")
public class HttpRequestHeaderServlet extends HttpServlet {

	private static final long serialVersionUID = 4186335080690001694L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		resp.setContentType("text/html; charset=UTF-8");

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("HttpMethod", req.getMethod());
		requestHeaders.put("RequestURI", req.getRequestURI());
		requestHeaders.put("RequestURL", req.getRequestURL().toString());
		requestHeaders.put("ContextPath", req.getContextPath());
		requestHeaders.put("ServletPath", req.getServletPath());
		requestHeaders.put("LocalAddr", req.getLocalAddr());
		requestHeaders.put("RemoteAddr \"Client IP\"", req.getRemoteAddr());
		requestHeaders.put("LocalPort", String.valueOf(req.getLocalPort()));
		requestHeaders.put("Scheme", req.getScheme());
		requestHeaders.put("Host", req.getHeader("host"));

		requestHeaders.put("URL1",
				req.getScheme() + "://" + req.getHeader("host") + req.getContextPath() + req.getServletPath());
		requestHeaders.put("URL2", req.getScheme() + "://" + req.getLocalAddr() + ":" + req.getLocalPort()
				+ req.getContextPath() + req.getServletPath());

		try (PrintWriter out = resp.getWriter()) {
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("		<head>");
			out.println("			<meta charset=\"UTF-8\" />");
			out.println("			<title>Request HTTP Header</title>");
			out.println("		</head>");
			out.println("		<body>");
			out.println("			<h1>Request HTTP Header</h1>");

			for (Map.Entry<String, String> entry : requestHeaders.entrySet()) {
				out.println("		<b>" + entry.getKey() + ":</b> " + entry.getValue() + "<br/>");
			}

			out.println("			<h3>Others headers: req.getHeader(\"headerName\")</h3>");

			Enumeration<String> headerNames = req.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				out.println("		<b>" + headerName + ":</b> " + req.getHeader(headerName) + "<br/>");
			}

			out.println("		<h6>Note: The Header referer will show the last link that call this page.");
			out.println("		</body>");
			out.println("</html>");
		}
	}

}
