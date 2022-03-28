package com.metrics.myfilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Properties;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet Filter implementation class TestFilter
 */
@WebFilter("/MetricsFilterExtension")
public class MetricsFilterExtension implements Filter {
	private static int id = 1;
    /**
     * Default constructor. 
     */
    public MetricsFilterExtension() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}
	
	public static HashMap<Integer, Properties> propMap = new HashMap<Integer, Properties>();
	
	
	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		long startTime = System.nanoTime();
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		ContentCaptureResponse contentCaptureResponse = new ContentCaptureResponse(httpResponse);
		contentCaptureResponse.setHeader("uniqueId", "" + id++);
		
		// pass the request along the filter chain
		chain.doFilter(request, contentCaptureResponse);
		String content = contentCaptureResponse.getContent();
		response.getOutputStream().write(content.getBytes(StandardCharsets.UTF_8));
		long elapsed = System.nanoTime() - startTime;
		
		writeMetrics(elapsed, Integer.parseInt(contentCaptureResponse.getHeader("uniqueId")), content.getBytes(StandardCharsets.UTF_8).length);
	}

	
	private void writeMetrics(long requestTime, Integer id, long length)
	{
		Properties tmpProp = new Properties();
		tmpProp.put("requestTime", requestTime);
		tmpProp.put("responseSize", length);
		synchronized(propMap) {
			propMap.put(id, tmpProp);
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
