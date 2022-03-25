package com.metrics.myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metrics.myfilter.testfilter;

/**
 * Servlet implementation class testservlet
 */
//@WebServlet("/testservlet")
public class MetricsServlet extends HttpServlet {
	static int id = 1;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MetricsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		id++;
//		response.setHeader("mike", "" + id);
		testfilter filter = new testfilter();
		HashMap<Integer, Properties> prop = filter.propMap;
		System.out.println("servlet");
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
//	    System.out.println(response.getHeader("mike"));
	    if(prop.size() != 0) {
		    for(int i=1; i<=prop.size(); i++) {
		    	
		    	out.println("The min request time is: " + getMin("requestTime"));
		    	out.println("request time: " + prop.get(i).get("requestTime") + getAvg("requestTime"));
		    	out.println("response size: " + prop.get(i).get("responseSize") + getMax("requestTime"));
		    }
	    }
	}

	private String getMax(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getAvg(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getMin(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
