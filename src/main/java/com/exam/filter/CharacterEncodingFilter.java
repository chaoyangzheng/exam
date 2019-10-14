package com.exam.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebFilter(initParams = {@WebInitParam(name = "encode", value = "UTF-8")}, urlPatterns = {"/*"})
public class CharacterEncodingFilter implements Filter {

    private String encoding = "UTF-8";

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=" + encoding);
        chain.doFilter(new CharacterEncodingRequest(request), resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        String encode = config.getInitParameter("encode");
        if (encode != null) {
            encoding = encode;
        }
    }

    /**
     * 对Get方式传递的请求参数进行编码
     */
    class CharacterEncodingRequest extends HttpServletRequestWrapper {
        private HttpServletRequest request;

        public CharacterEncodingRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }

        /**
         * 对参数重新编码
         */
        @Override
        public String getParameter(String name) {
            String value = super.getParameter(name);
            if (value == null) {
                return null;
            }
            String method = request.getMethod();
            if ("Get".equalsIgnoreCase(method)) {
                try {
                    value = new String(value.getBytes("ISO8859-1"), request.getCharacterEncoding());
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
            return value;
        }
    }
}