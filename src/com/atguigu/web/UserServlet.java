package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;
import com.atguigu.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
////        super.doPost(req, resp);
////        if ("login".equals(action)) {
////           login(req,resp);
////        } else if ("regist".equals(action)) {
////           regist(req, resp);
////        }
//        String action = req.getParameter("action");
//        try {
//            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            System.out.println(method);
//            method.invoke(this,req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        doPost(req, resp);
//    }


    /**
     * 登录的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //  1、获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        // 调用 userService.login()登录处理业务
        User user = userService.login(new User(null, username, password, null));
        // 如果等于null,说明登录 失败!
        if (user == null) {
            // 把错误信息，和回显的表单项信息，保存到Request域中
            req.setAttribute("msg","用户名或密码错误");
            req.setAttribute("username",username);
            // 跳回登录页面
            System.out.println("用户名或密码错误！");
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        } else {
            // 登录 成功
            //跳到成功页面login_success.html
            System.out.println("用户名或密码正确！");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);
        }
    }

    /**
     * 注册的功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("registServlet");
        //1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");

//        User user = new User();
//        WebUtils.copyParamToBean(req.getParameterMap(),user);
        User user = WebUtils.copyParamToBean(req.getParameterMap(),new User());


        //2. 检查验证码是否正确-----写死，要求验证码为abcde
        if ("abcde".equalsIgnoreCase(code)) {
//                if (UserService.class.newInstance().existUsername(username)){
//
//                }
            //3. 检查用户名是否可用
            if (userService.existUsername(username)) {
//        不正确
                System.out.println("用户名[" + username + "]已存在");

                //把回显信息保存到request域中
                req.setAttribute("msg","用户名已存在");
                req.setAttribute("username",username);
                req.setAttribute("email",email);

                //返回注册页面
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
//                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            } else {
                //          可用
//          调用service保存到数据库
//          跳转到注册成功的页面regist_success.html
                userService.registUser(new User(null, username, password, email));
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //把回显信息保存到request域中
            req.setAttribute("msg","验证码错误");
            req.setAttribute("username",username);
            req.setAttribute("email",email);

//        不正确
//        跳回注册页面
            System.out.println("验证码[" + code + "]错误");
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }

    }

}
