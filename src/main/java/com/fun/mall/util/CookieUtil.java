package com.fun.mall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ Author     ：CZP.
 * @ Date       ：Created in 11:36 2018/12/10
 * @ Description：Cookie工具类
 * @ Modified By：
 * @Version: 1.0$
 */
public class CookieUtil {
    private static Logger log= LoggerFactory.getLogger(CookieUtil.class);

    private final static String COOKIE_DOMAIN="happymmall.com";
    private final static String COOKIE_NAME="mmall_login_token";

    public static String readLoginToken(HttpServletRequest request){
        Cookie[] cks=request.getCookies();
        if (cks!=null){
            for(Cookie ck:cks){
                log.info("read cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                if(StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    log.info("return cookieName:{},cookieValue:{}",ck.getName(),ck.getValue());
                    return ck.getValue();
                }
            }
        }
        return null;
    }

    public static void writeLoginToken(HttpServletResponse response,String token){
        Cookie ck=new Cookie(COOKIE_NAME,token);
        ck.setDomain(COOKIE_DOMAIN);
        // 设置在根目录
        ck.setPath("/");
        ck.setHttpOnly(true);
        ck.setMaxAge(60*30);
        log.info("write cookieNmae:{},cookieValue:{}",ck.getName(),ck.getValue());
        response.addCookie(ck);

    }

    public static void delLoginToken(HttpServletRequest request,HttpServletResponse response){
        Cookie[] cks=request.getCookies();
        if(cks!=null){
            for(Cookie ck:cks){
                if(StringUtils.equals(ck.getName(),COOKIE_NAME)){
                    ck.setDomain(COOKIE_DOMAIN);
                    ck.setPath("/");
                    ck.setMaxAge(0);
                    log.info("del cookieNmae:{},cookieValue:{}",ck.getName(),ck.getValue());
                    response.addCookie(ck);
                    return;
                }
            }
        }
    }
}
