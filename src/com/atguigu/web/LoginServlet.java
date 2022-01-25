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
public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

}
