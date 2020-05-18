package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.pojo.User;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

public class BookServlet extends HttpServlet {
    private BookService bookService=new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        try {
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
    protected void addBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        Book book = new Book(null,req.getParameter("book_name"),new BigDecimal(req.getParameter("book_price")),req.getParameter("book_author"),Integer.getInteger(req.getParameter("book_sales")),Integer.getInteger(req.getParameter("book_stock")),null);
//        bookService.addBook(book);
//        req.setCharacterEncoding("UTF-8");
//        String username= req.getParameter("name");
////        username = new String(username.getBytes("iso8859-1"),"utf-8");
//        System.out.println(username);
//                1、获取请求参数==封装为Book对象
        int pageNo =WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo +=1;

        Book book = WebUtils.copyParamToBean(req.getParameterMap(),new Book());
//                2、调用bookService.addBook()保存图书
        bookService.addBook(book);
//                3、跳转图书列表页面

//                /manager/bookService?action=queryBooks
//      转发重复发送请求
//      req.getRequestDispatcher("/manager/bookServlet?action=queryBooks").forward(req,resp);
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+pageNo);
    }
    protected void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
//        1、获取请求参数ID，图书编程
        int id = WebUtils.parseInt(req.getParameter("id"),0);
//        2、调用bookService.deleteBookById
        bookService.deleteBookById(id);
//        3、重定向回图书列表管理页面 /book/manager/bookServlet?action=list
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }
    protected void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //获取请求参数 封装成为BOOK对象
        Book book =WebUtils.copyParamToBean(req.getParameterMap(),new Book());
        //调用BookService修改内容
        bookService.updateBook(book);
        //重订向至list页面
        resp.sendRedirect(req.getContextPath()+"/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    /**
     * 处理分页功能
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1、获取请求参数pageNo和pagesize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);

        //2、调用BookService.page(pageNo,pageSize)信息;page对象
        Page<Book> page =bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        //3、保存page对象到request中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    protected void queryBooks(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        //1、通过BookService获取所有图书信息
        List<Book> books = bookService.queryBooks();
        //2、把全部图书保存到Request域中
        req.setAttribute("books",books);
        //3、请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        Book book = bookService.queryBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);
    }
}
