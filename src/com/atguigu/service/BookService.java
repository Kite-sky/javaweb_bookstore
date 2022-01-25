package com.atguigu.service;

import com.atguigu.pojo.Book;

import java.util.List;

/**
 * @description：TODO
 * @date ：2022/1/19 15:43
 */
public interface BookService {
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

//    Page<Book> page(int pageNo, int pageSize);
//
//    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
