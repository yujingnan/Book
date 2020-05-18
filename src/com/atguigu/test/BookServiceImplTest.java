package com.atguigu.test;

import com.atguigu.pojo.Book;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceImplTest {
    private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"于静楠",new BigDecimal(9999),"于静楠",1000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22,"姚玲玉为何这么傻逼",new BigDecimal(-9999),"于静楠",1000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(22));
    }

    @Test
    public void queryBooks() {
        for (Book book:bookService.queryBooks()) {
            System.out.println(book);
        }
    }
    @Test
    public void page(){
        System.out.println(bookService.page(1,4));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1,4,50,100));
    }
}