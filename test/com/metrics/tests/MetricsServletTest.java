package com.metrics.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.metrics.myfilter.MetricsFilterExtension;
import com.metrics.myservlets.MetricsServlet;

public class MetricsServletTest extends Mockito{

	static long requestTimeMin = 2;
	static long responseSizeMin = 3;
	static long requestTimeMax = 6;
	static long responseSizeMax = 7;
	static long requestTimeAvg = 4;
	static long responseSizeAvg = 5;
	
	static MetricsServlet servlet;
	
	@BeforeAll
	public static void setUp() {
		servlet = new MetricsServlet();
		
		Properties tmpProp1 = new Properties();
		tmpProp1.put("requestTime", requestTimeMin);
		tmpProp1.put("responseSize", responseSizeMin);
		
		Properties tmpProp2 = new Properties();
		tmpProp2.put("requestTime", requestTimeMax);
		tmpProp2.put("responseSize", responseSizeMax);
		
		MetricsFilterExtension.propMap.put(1, tmpProp1);
		MetricsFilterExtension.propMap.put(2, tmpProp2);
	}
	
	@Test
	public void testGetMax() {
		assertTrue(servlet.getMax("requestTime") == requestTimeMax);
		assertTrue(servlet.getMax("responseSize") == responseSizeMax);
	}
	
	@Test
	public void testGetAvg() {
		assertTrue(servlet.getAvg("requestTime") == requestTimeAvg);
		assertTrue(servlet.getAvg("responseSize") == responseSizeAvg);
	}
	
	@Test
	public void testGetMin() {
		assertTrue(servlet.getMin("requestTime") == requestTimeMin);
		assertTrue(servlet.getMin("responseSize") == responseSizeMin);
	}
}
