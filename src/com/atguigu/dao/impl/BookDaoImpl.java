package com.atguigu.dao.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.pojo.Book;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {
    @Override
    public int addBook(Book book) {
        String sql="insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path());
    }

    @Override
    public int deleteBookById(int id) {
        String sql="delete from t_book where id=?";
        return update(sql,id);
    }

    @Override
    public int updateBook(Book book) {
        String sql="update t_book set `name`=?,`author`=?,`price`=?,`sales`=?,`stock`=?,`img_path`=? where id=?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImg_path(),book.getId());
    }

    @Override
    public Book queryBookById(int id) {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book where id=?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql="select `id`,`name`,`author`,`price`,`sales`,`stock`,`img_path` imgPath from t_book";
        return queryForList(Book.class,sql);
    }

    @Override
    public int queryBookCount() {
        String sql="select count(*) from t_book";
        return ((Number) queryForSingleValue(sql)).intValue();
    }



    @Override
    public List<Book> queryPageItems(int begin, int size) {
        String sql="select * from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,size);
    }

    public int queryCountByPrice(int min,int max){
        String sql="select count(*) from t_book where price between ? and ?";
        return ((Number)queryForSingleValue(sql,new BigDecimal(min),new BigDecimal(max))).intValue();
    }

    public List<Book> queryPriceItems(int min,int max,int begin,int size){
        String sql="select * from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,new BigDecimal(min),new BigDecimal(max),begin,size);
    }
}
