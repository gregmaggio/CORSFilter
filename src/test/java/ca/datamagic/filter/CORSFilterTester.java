/**
 * 
 */
package ca.datamagic.filter;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * @author Greg
 *
 */
public class CORSFilterTester {

	@Test
	public void testFilterFromAngular() throws Exception {		
		HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
	    HttpServletResponse httpServletResponse = mock(HttpServletResponse.class);
	    FilterChain filterChain = mock(FilterChain.class);
	    FilterConfig filterConfig = mock(FilterConfig.class);
	    
	    when(httpServletRequest.getHeader("origin")).thenReturn("http://localhost:4200");
	    when(httpServletRequest.getMethod()).thenReturn("OPTIONS");
	    when(filterConfig.getInitParameter("allow.origins")).thenReturn("http://localhost:4200,http://datamagic.ca,https://datamagic.ca");
	    when(filterConfig.getInitParameter("allow.methods")).thenReturn("GET, POST, OPTIONS");
	    when(filterConfig.getInitParameter("allow.headers")).thenReturn("Origin, Content-Type, Accept, Authorization, X-Request-With, location");
	    when(filterConfig.getInitParameter("allow.credentials")).thenReturn("true");
	    
	    CORSFilter corsFilter = new CORSFilter();
	    corsFilter.init(filterConfig);
	    corsFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
	    
	    verify(httpServletResponse).addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
	}
}
