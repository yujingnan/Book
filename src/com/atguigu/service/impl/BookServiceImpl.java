package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(int id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page page(int pageNo, int pageSize) {
        int totalCount;
        int pageTotal;
        Page<Book> page=new Page<Book>();

        page.setPageSize(pageSize);
        totalCount=bookDao.queryBookCount();
        page.setPageTotalCount(totalCount);
        pageTotal=totalCount%pageSize>0?1+totalCount/pageSize:totalCount/pageSize;
        page.setPageTotal(pageTotal);

        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryPageItems((pageNo-1)*pageSize,pageSize));


        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        int totalCount;
        int pageTotal;
        Page<Book> page=new Page<Book>();

        page.setPageSize(pageSize);
        totalCount=bookDao.queryCountByPrice(min,max);
        page.setPageTotalCount(totalCount);
        pageTotal=totalCount%pageSize>0?1+totalCount/pageSize:totalCount/pageSize;
        page.setPageTotal(pageTotal);
        if (pageNo<1){
            pageNo=1;
        }
        if (pageNo>pageTotal){
            pageNo=pageTotal;
        }
        page.setPageNo(pageNo);
        page.setItems(bookDao.queryPriceItems(min,max,(pageNo-1)*pageSize,pageSize));

        return page;
    }
}
