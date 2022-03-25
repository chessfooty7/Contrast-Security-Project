package com.metrics.myfilter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class testfilter
 */
@WebFilter("/testfilter")
public class testfilter implements Filter {

    /**
     * Default constructor. 
     */
    public testfilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public static HashMap<Integer, Properties>  propMap = new HashMap<Integer, Properties>();
	
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
//		double startTime = System.currentTimeMillis();
		long startTime = System.nanoTime();
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String names = "";
//		for(Enumeration<String> enu = httpRequest.getHeaderNames();  enu.hasMoreElements(); )
//			names = names + ",  " + enu.nextElement();
//		String url = ((HttpServletRequest)request).getRequestURL().toString();
		System.out.println("my filter is called*******************");
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ContentCaptureResponse contentCaptureResponse = new ContentCaptureResponse(httpResponse);
		// pass the request along the filter chain
		chain.doFilter(request, contentCaptureResponse);
//		contentCaptureResponse.setHeader("mike", "" + 1);
		String content = contentCaptureResponse.getContent();
		response.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
		System.out.println(content.getBytes(StandardCharsets.UTF_8).length);
		long elapsed = System.nanoTime() - startTime;
		
		System.out.println(elapsed);
//		System.out.println(httpRequest.getHeader("mike"));
		writeMetrics(elapsed, 1, content.getBytes(StandardCharsets.UTF_8).length);
	}

	
	private void writeMetrics(long requestTime, Integer id, int length)
	{
		Properties tmpProp = new Properties();
		tmpProp.put("requestTime", requestTime);
		tmpProp.put("responseSize", length);
		synchronized(propMap) {
			propMap.put(id, tmpProp);
			System.out.println(propMap.get(1).get("requestTime"));
		}
	}
	
//	private Properties readMetrics(int id)
//	{
//		synchronized(propMap) {
//			
//		}
//		return null;
//	}
//	
	
	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
