package com.example.codebase.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.bcel.classfile.ClassFormatException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.filter.GenericFilterBean;

import java.io.*;

public class LoggingFilter extends GenericFilterBean {

    private static final Logger log = LogManager.getLogger(LoggingFilter.class);

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        doFilterInternal(request, response);
        chain.doFilter(request, response);
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response) {
        if (request.getServletPath().startsWith("/monitoring")) {
            return;
        }

        if (log.isDebugEnabled()) {
            try {
                String requestLogging = generateInboundRequest(request);
                logger.debug(requestLogging);
                String responseLogging = generateOutboundRequest(response);
                logger.debug(responseLogging);
            } catch (Exception e){
                throw new ClassFormatException("Cannot generate request logging");
            }
        }
    }

    private String generateInboundRequest(HttpServletRequest request) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        var builder = new StringBuilder();
        String body = reader.readLine();
        while (body!= null) {
            builder.append(body);
            body = reader.readLine();

        }
        return builder.toString();
    }

    private String generateOutboundRequest(HttpServletResponse response) throws IOException {
        return response.getOutputStream().toString();
    }
}
