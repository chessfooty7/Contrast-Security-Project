package com.metrics.myservlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class nasdaqservlet
 */
public class ServletNasdaq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNasdaq() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("nasdaq");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	    out.println("<h1>Nasdaq</h1>");
	    out.println("<a href=\\\"https://ir.tesla.com/#tab-quarterly-disclosure\\\">TSLA</a>");
	    out.println("<a href=\\\"https://ir.aboutamazon.com/annual-reports-proxies-and-shareholder-letters/default.aspx\\\">AMZN</a>");
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
