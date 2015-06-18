package com.bsf.actions;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

/**
 * Servlet to forwarding in navigation menu.
 * 
 * @author pkalashnikov
 *
 */
@WebServlet(description = "forward action", urlPatterns = { "/Forward" })
public class Forward extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public Forward() {}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get resource name
		final String resource = request.getParameter("resource");
		response.setContentType("text/html; charset=utf-8");
		response.setHeader("X-Frame-Options", "SAMEORIGIN");
		response.setHeader("x-content-type-options", "nosniff");
		response.setHeader("x-xss-protection", "1; mode=block");
		response.setHeader("Connection", "keep-alive");
		PrintWriter writer = response.getWriter();
		
		switch(resource) {
			
			case "login":

				//response.sendRedirect("app/Views/Templates/login.html");
//				final String path = new File(".").getAbsolutePath();
				//System.out.println(new File(getClass().getClassLoader().getResource(".").getFile()).getAbsolutePath());
				final FileReader fr = new FileReader("C:/SandBox/bsf/WebContent/app/Views/Templates/login.html");
				
				//response.setHeader("Content-Length", String.valueOf(IOUtils.toString(fr).getBytes().length));
				
				writer.println(IOUtils.toString(fr));
				writer.flush();
				response.flushBuffer();
				fr.close();
				break;
				
			case "company":
				
				response.sendRedirect("app/Views/Templates/company.html");
				break;
				
			default:
				
				response.sendRedirect("app/Views/Templates/notFound.html");
				break;
		}
		
		writer.close();
		
	}
	
//	private void forward(final String resource, final HttpServletRequest request, final HttpServletResponse response) 
//			throws ServletException, IOException {
//		
//		final RequestDispatcher rd = request.getRequestDispatcher(resource);
//		rd.forward(request, response);
//	}
}
