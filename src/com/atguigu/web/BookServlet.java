package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public class BookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1. 获取请求的参数==封装成为Book对象

        req.setCharacterEncoding("UTF-8");
//        System.out.println(req.getParameter("name"));
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //2. 调用BookService.addBook()保存图书
        bookService.addBook(book);
        //3. 调到图书列表页面
        // /manager/bookServlet?action=list
        //req.getRequestDispatcher("/manager/bookServlet?action=list").forward(req, resp);
        /**
         * 请求转发的“/”表示：http://主机:端口号/工程名
         * 重定向的“/”表示：http://主机:端口号
         */
        System.out.println(req.getContextPath());
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");

    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        1、获取请求的参数id，图书编程
       int id = WebUtils.parseInt(req.getParameter("id"),0);
        //        2、调用bookService.deleteBookById();删除图书
        bookService.deleteBookById(id);
//        3、重定向回图书列表管理页面
//                /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=list");

//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));

    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //        1、获取请求的参数==封装成为Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//        2、调用BookService.updateBook( book );修改图书
        bookService.updateBook(book);
//        3、重定向回图书列表管理页面
//        地址：/工程名/manager/bookServlet?action=list
//        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求的参数图书编号
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        //2 调用bookService.queryBookById查询图书
        Book book = bookService.queryBookById(id);
        //3 保存到图书到Request域中
        req.setAttribute("book",book);
        //4 请求转发到。pages/manager/book_edit.jsp页面
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 通过BookService查询全部图书
        List<Book> books = bookService.queryBooks();
        //2 把全部图书保存到Request域中
        req.setAttribute("books", books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
