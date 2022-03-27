package com.metrics.myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.metrics.myfilter.MetricsFilterExtension;

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
		System.out.println("servlet");
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
//	    System.out.println(response.getHeader("mike"));
	    if(MetricsFilterExtension.propMap.size() != 0) {
		    	out.println("The min request time is: " + getMin("requestTime") + " nanoseconds" + "<br>");
		    	out.println("The average request time: " + getAvg("requestTime") + " nanoseconds" + "<br>");
		    	out.println("The max request time: " + getMax("requestTime")+ " nanoseconds" + "<br>");
		    	out.println("The min response size is: " + getMin("responseSize") + " bytes" + "<br>");
		    	out.println("The average response size is: " + getAvg("responseSize") + " bytes" + "<br>");
		    	out.println("The max response size is: " + getMax("responseSize") + " bytes");
	    }
	    else
	    	out.println("No requests yet");
	}

	private long getMax(String attribute) {
		long max = 0;
		for(int i=1; i<=MetricsFilterExtension.propMap.size(); i++) {
			long tmp = (long) MetricsFilterExtension.propMap.get(i).get(attribute);
			if(tmp > max) {
				max = tmp;
			}
		}
		return max;
	}

	private long getAvg(String attribute) {
		int argNum = MetricsFilterExtension.propMap.size();
		long sum = 0;
		for(int i=1; i<=argNum; i++) {
			sum += (long) (MetricsFilterExtension.propMap.get(i).get(attribute));
		}
		return sum/argNum;
	}

	private long getMin(String attribute) {
		long min = Long.MAX_VALUE;
		for(int i=1; i<=MetricsFilterExtension.propMap.size(); i++) {
			long tmp = (long) MetricsFilterExtension.propMap.get(i).get(attribute);
			if(tmp < min) {
				min = tmp;
			}
		}
		return min;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
