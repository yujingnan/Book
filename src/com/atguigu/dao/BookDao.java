package com.atguigu.dao;

import com.atguigu.pojo.Book;

import java.util.List;

public interface BookDao {
    public int addBook(Book book);
    public int deleteBookById(int id);
    public int updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
    public int queryBookCount();
    public List<Book> queryPageItems(int begin,int num);
    public int queryCountByPrice(int min,int max);
    public List<Book> queryPriceItems(int min,int max,int begin,int size);
}
