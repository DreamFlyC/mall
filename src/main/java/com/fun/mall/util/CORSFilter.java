package com.fun.mall.util;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 15:20 2018/12/1
 * @ Description：跨域访问filter
 * @ Modified By：
 * @Version: 1.0$
 */
@Component
public class CORSFilter implements Filter {
    // 存放跨域的白名单
    private String[] permitUrl;

    @Override
    public void doFilter(ServletRequest req, ServletResponse res,FilterChain chain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        HttpServletRequest request = (HttpServletRequest) req;

        String myOrigin = request.getHeader("origin");
        boolean isValid = false;
        for (String ip : permitUrl) {
            if (myOrigin != null && myOrigin.equals(ip)) {
                isValid = true;
                break;
            }
        }

        response.setContentType("textml;charset=UTF-8");
        response.setHeader("Access-Control-Allow-Origin", isValid ? myOrigin : "null");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,userId,token");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("P3P", "CP=\"NON DSP COR CURa ADMa DEVa TAIa PSAa PSDa IVAa IVDa CONa HISa TELa OTPa OUR UNRa IND UNI COM NAV INT DEM CNT PRE LOC\"");
        response.setHeader("XDomainRequestAllowed", "1");

        chain.doFilter(req, res);
    }


    // 初始化方法，这里的permitUrl是在web.xml中配置的
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String urls = filterConfig.getInitParameter("permitUrl");
        if (urls != null) {
            urls = urls.replaceAll("\\n", "").replaceAll("\\r", "").replaceAll("\\t", "");
        }
        if (!"".equals(urls) && urls != null) {
            permitUrl = urls.split(",");
        }
        if (permitUrl != null) {
            for (int i = 0; i < permitUrl.length; i++) {
                permitUrl[i] = permitUrl[i].trim();
            }
        }
    }

    // 销毁方法
    @Override
    public void destroy() {
    }

}
