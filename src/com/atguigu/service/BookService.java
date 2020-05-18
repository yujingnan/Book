package com.atguigu.service;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    public void deleteBookById(int id);
    public void updateBook(Book book);
    public Book queryBookById(int id);
    public List<Book> queryBooks();
    public Page page(int pageNo, int pageSize);

    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
