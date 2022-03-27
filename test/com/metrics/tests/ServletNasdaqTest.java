package com.metrics.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.metrics.myservlets.ServletNasdaq;

public class ServletNasdaqTest extends Mockito{

	@Test
	public void testNasdaqServlet() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(response.getWriter()).thenReturn(writer);
        
		new ServletNasdaq().doGet(request, response);
		assertTrue(stringWriter.toString().contains("Nasdaq"));
		assertTrue(stringWriter.toString().contains("TSLA"));
		assertTrue(stringWriter.toString().contains("AMZN"));
	}

}
