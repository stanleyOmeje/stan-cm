package com.stan.stancommons.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class MdcAwareLoggingFilter implements Filter {

    public static final String DEVICE_REQUEST_ID = "R.2";

    public static final String DEVICE_REQUEST_ID_INTERNAL = "deviceId";

    public static final String USER_EMAIL = "R.9";

    public static final String USER_EMAIL_INTERNAL = "email";

    private static final Logger logger = LoggerFactory.getLogger(MdcAwareLoggingFilter.class);


    @Override
    public void destroy() {
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            String deviceId = ((HttpServletRequest) servletRequest).getHeader(DEVICE_REQUEST_ID);
            String email = ((HttpServletRequest) servletRequest).getHeader(USER_EMAIL);
            if (deviceId != null && !deviceId.isEmpty()) {
                MDC.put(DEVICE_REQUEST_ID_INTERNAL, deviceId);
            }
            if (email != null && !email.isEmpty()) {
                MDC.put(USER_EMAIL_INTERNAL, email);
            }
            logger.debug(String.format("Logging for %s ", ((HttpServletRequest) servletRequest).getRequestURI()));
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(DEVICE_REQUEST_ID_INTERNAL);
            MDC.remove(USER_EMAIL_INTERNAL);
        }
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
}
