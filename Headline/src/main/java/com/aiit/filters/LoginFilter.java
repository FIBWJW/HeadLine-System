package com.aiit.filters;


import com.aiit.common.Result;
import com.aiit.common.ResultCodeEnum;
import com.aiit.utils.JwtHelper;
import com.aiit.utils.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * @author hmxia
 * @date 2024/12/18 9:50
 * 登录校验过滤器：过滤器不放行，无法进入发布、修改、删除的页面
 */
//@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        //1、获取token
        String token = request.getHeader("token");
        boolean flag = false;
        //token不为空并且没有过期，并且合法的，放行
        if(token!=null){
            boolean expiration = JwtHelper.isExpiration(token);
            if(!expiration){
                flag = true;
            }
        }
        if(flag){
            filterChain.doFilter(servletRequest,servletResponse);  //放行
        }else{
            WebUtil.writeJson((HttpServletResponse) servletResponse, Result.build(null, ResultCodeEnum.NOTLOGIN));
        }
    }
}
