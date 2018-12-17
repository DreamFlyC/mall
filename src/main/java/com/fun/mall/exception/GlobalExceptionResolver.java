package com.fun.mall.exception;

import com.fun.mall.common.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ author     ：CZP.
 * @ Date       ：Created in 14:13 2018/12/17
 * @ Description：全局异常处理
 * @ Modified By：
 * @ Version    : 1.0$
 */

@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {

    private static final Logger log= LoggerFactory.getLogger(GlobalExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        log.error("{},Exception",httpServletRequest.getRequestURI(),e);
        ModelAndView modelAndView=new ModelAndView(new MappingJackson2JsonView());
        modelAndView.addObject("status", ResponseCode.ERROR.getCode());
        modelAndView.addObject("msg","系统出现异常！！！");
        modelAndView.addObject("data",e.toString());
        return modelAndView;
    }
}
