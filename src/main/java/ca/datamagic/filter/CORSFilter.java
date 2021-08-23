/**
 * 
 */
package ca.datamagic.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Greg
 *
 */
public class CORSFilter implements Filter {
	private static Logger logger = LogManager.getLogger(CORSFilter.class);
	private String allowOrigins = null;
	private String allowMethods = null;
	private String allowHeaders = null;
	private String allowCredentials = null;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.allowOrigins = filterConfig.getInitParameter("allow.origins");
		this.allowMethods = filterConfig.getInitParameter("allow.methods");
		this.allowHeaders = filterConfig.getInitParameter("allow.headers");
		this.allowCredentials = filterConfig.getInitParameter("allow.credentials");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		String method = httpServletRequest.getMethod();
		logger.info("method: " + method);
		
		String origin = httpServletRequest.getHeader("origin");
		logger.info("origin: " + origin);
		
		if ((origin != null) && (origin.length() > 0)) {
			if ((this.allowOrigins != null) && (this.allowOrigins.length() > 0)) {
				if (this.allowOrigins.toLowerCase().contains(origin.toLowerCase())) {
					httpServletResponse.addHeader("Access-Control-Allow-Origin", origin);
					if ((this.allowMethods != null) && (this.allowMethods.length() > 0)) {
						httpServletResponse.addHeader("Access-Control-Allow-Methods", this.allowMethods);
					}
					if ((this.allowHeaders != null) && (this.allowHeaders.length() > 0)) {
						httpServletResponse.addHeader("Access-Control-Allow-Headers", this.allowHeaders);
					}
					if ((this.allowCredentials != null) && (this.allowCredentials.length() > 0)) {
						httpServletResponse.addHeader("Access-Control-Allow-Credentials", this.allowCredentials);
					}
				}
			}
		}
		
		// For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
		if ((method != null) && (method.length() > 0)) {
			if (method.compareToIgnoreCase("OPTIONS") == 0) {
				httpServletResponse.setStatus(HttpServletResponse.SC_ACCEPTED);
				return;
			}
		}
 
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}
}
