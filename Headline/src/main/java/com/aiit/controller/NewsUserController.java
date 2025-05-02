package com.aiit.controller;

import com.aiit.common.Result;
import com.aiit.common.ResultCodeEnum;
import com.aiit.pojo.NewsUser;
import com.aiit.service.Impl.NewsUserServiceImpl;
import com.aiit.service.NewsUserService;
import com.aiit.utils.JwtHelper;
import com.aiit.utils.MD5Util;
import com.aiit.utils.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hmxia
 * @date 2024/12/9 16:19
 */
@WebServlet("/user/*")
public class NewsUserController extends BaseController{

    NewsUserService userService =  new NewsUserServiceImpl();

    private NewsUserService newsUserService =new NewsUserServiceImpl();


    //检查登录是否过期
    protected void checkLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求数据：token=>请求头
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if(token!=null){
            if(!JwtHelper.isExpiration(token)){
                result = Result.ok(null);
            }
        }

        //2、设置响应数据
        WebUtil.writeJson(resp,result);
    }

    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求中的数据，获取json字符串，将json数据转换为对象
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        //2、通过service层的方法注册用户
        //2.1 判断该用户是否已经注册了
        Result result = null;
        NewsUser usedUser = userService.findByUsername(newsUser.getUsername());
        if(usedUser == null){
            //没有被注册，可以正常注册
            userService.registUser(newsUser);
            result = Result.ok( null);
        }
        else{
            //注册过了
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        //3、响应数据
        WebUtil.writeJson(resp,result);
    }

    protected void checkUserName(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求中的数据（username）
        String username = req.getParameter("username");
        System.out.println("checkUserName:"+username);
        //2、通过service层调用其方法查询用户名是否重复了
        NewsUser newsUser = userService.findByUsername(username);
        //3、响应数据
        Result result = null;
        if(newsUser == null){
            result = Result.ok(null);
        }else{
            result = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }

        WebUtil.writeJson(resp,result);
    }

    protected void  getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1、获取请求中的token
        String token = req.getHeader("token");

        Result result = Result.build(null,ResultCodeEnum.NOTLOGIN);

        if(token != null && (!"".equals(token))){
            //说明有token值，进行解密，先来判断token值是否有效，传入的token是否正确，或者是否在有效期内
            if (!JwtHelper.isExpiration(token)) {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser newsUser = userService.findByUid(userId);
                System.out.println(newsUser);
                if(newsUser != null){
                    //通过校验 将查询进行放入Result中进行返回
                    Map data = new HashMap();
                    newsUser.setUserPwd("");
                    data.put("loginUser",newsUser);
                    result = Result.ok(data);
                }
            }
        }
        //3、将结果进行响应
        WebUtil.writeJson(resp,result);

    }

    //登录成功的实现
    /*
    req：请求参数
    resp：响应的参数
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、接收用户名和密码（明文）
        /*
        {
        "username":"zhangsan", //用户名
        "userPwd":"123456"     //明文密码
         }
         */
        NewsUser newsUser = WebUtil.readJson(req, NewsUser.class);

        //2、调用服务层（service）方法，实现登录
         NewsUser loginUser =userService.findByUsername(newsUser.getUsername());
        Result result = null;
         if(loginUser != null){
             if(MD5Util.encrypt(newsUser.getUserPwd()).equalsIgnoreCase(loginUser.getUserPwd())){
                 //成功
                 //将结果响应给用户
                 //1)将用户数据加密成token口令
                 String token = JwtHelper.createToken(loginUser.getUid().longValue());
                 //将token放入data中
                 Map data = new HashMap();
                 data.put("token",token);
                 //2）结果返回
                 result = Result.ok(data);
             }else{
                 //密码失败
                 result = Result.build(null, ResultCodeEnum.PASSWORD_ERROR);
             }
         }else{
             //没有查询到用户
             result = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
         }

        //3、向客户端响应登录验证信息
        WebUtil.writeJson(resp,result);
    }
}
