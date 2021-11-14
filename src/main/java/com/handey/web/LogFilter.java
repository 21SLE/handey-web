package com.handey.web;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

public class LogFilter implements Filter {

    private Logger logger = (Logger) LoggerFactory.getLogger(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;

        String remoteAddr = req.getRemoteAddr();
        String uri = req.getRequestURI();
        String url = req.getRequestURL().toString();
        String queryString = req.getQueryString();

        String referer = req.getHeader("referer");
        String agent = req.getHeader("User-Agent");

        StringBuffer sb = new StringBuffer();
        sb.append("\n* remoteAddr : " + remoteAddr) //ip주소
                .append("\n* uri : " + uri)
                .append("\n* url : " + url)
                .append("\n* queryString : " + queryString)
                //경로 뒤에 있는 요처 쿼리 문자열(key=value 형태)
                .append("\n* referer : " + referer)
                .append("\n* agent : " + agent)
                .append("\n");

        HttpServletResponse resp = (HttpServletResponse) response;
        // req.getHeader("Origin") -> http://localhost:3000

        resp.setHeader("Access-Controll-Allow-Origin", req.getHeader("Origin"));
        resp.setHeader("Access-Controll-Allow-Credentials","true");
        resp.setHeader("Access-Controll-Allow-Methods","GET, POST");
        resp.setHeader("Access-Controll-Max-Age","10");
        resp.setHeader("Access-Controll-Allow-Headers","Content-Type, Accept, X-Requested-With, remember-me");

        logger.info("\nLOG Filter" + sb);

        chain.doFilter(req, response);
        // 다음에 존재하는 필터가 있다면, 그 필터가 실행될 수 있도록 chaining
        // request에 들어있는 정보를 읽어서 Log.info한 것

    }

    @Override
    public void destroy() {

    }

}

