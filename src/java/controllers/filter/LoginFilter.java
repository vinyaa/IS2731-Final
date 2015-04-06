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
public class LoginFilter implements Filter{
    
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

        //Pages that don't need to be filtered, currently just Login page, css and js files
        if(clientURI.contains("login") || clientURI.contains("register") || clientURI.endsWith(".js") || clientURI.endsWith(".css")) {
            chain.doFilter(request, response);
        }
        else if(session == null || session.getAttribute("sesssionCode") == null) {
            //Jump to login page if session code is invalid
            servletResponse.sendRedirect("./login.jsp");
        } 
        else {
            //forward the original request
            chain.doFilter(request, response);
        }
    }
}
