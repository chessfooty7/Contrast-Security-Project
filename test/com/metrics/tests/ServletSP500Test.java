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

import com.metrics.myservlets.ServletSP500;

public class ServletSP500Test extends Mockito{

	@Test
	public void testSP500Servlet() throws ServletException, IOException{
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class);
		
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(response.getWriter()).thenReturn(writer);
        
		new ServletSP500().doGet(request, response);
		assertTrue(stringWriter.toString().contains("SP 500"));
		assertTrue(stringWriter.toString().contains("XOM"));
		assertTrue(stringWriter.toString().contains("KO"));
	}

}
