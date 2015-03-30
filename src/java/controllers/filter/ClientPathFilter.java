package controllers.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hab81
 */
public class ClientPathFilter implements Filter{
    private FilterConfig filterConfig;
    private ServletContext servletContext;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.servletContext = filterConfig.getServletContext();
    }
    
    @Override
    public void destroy() {
        this.filterConfig = null;
    }
 
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
        FilterChain chain) throws IOException, ServletException {
        
        if (this.filterConfig == null)
            return;
        
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession(false);
 
        //Acquire client's URI
        String clientURI = servletRequest.getRequestURI();
        
        if(clientURI.startsWith("/is2731_final/client")) {
            //filter out requests which directly type client/xxx.jsp in address bar
            servletResponse.sendRedirect("../error404.jsp");
        }
        else {
            //forward the original request
            chain.doFilter(request, response);
        }
    }
}
