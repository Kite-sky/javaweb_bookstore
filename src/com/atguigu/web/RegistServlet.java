package com.atguigu.web;

import com.atguigu.pojo.User;
import com.atguigu.service.UserService;
import com.atguigu.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class RegistServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("registServlet");
        //1. 获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
