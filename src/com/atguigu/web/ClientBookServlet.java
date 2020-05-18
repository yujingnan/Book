package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Page;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class ClientBookServlet extends HttpServlet {
    BookService bookService=new BookServiceImpl();

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
//        if ("login".equals(action)){
//            login(req,resp);
//        }
//        if ("regist".equals(action)){
//            regist(req,resp);
//        }
        //通过反射调用方法
        try {
            //获取action业务鉴别字符串，获取相应的业务方法反射对象
            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
            //调用目标业务 方法
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        page.setUrl("client/clientBookServlet?action=page");
        //3、保存page对象到request中
        req.setAttribute("page",page);
        //4、请求转发到pages/manager/book_manager.jsp
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
    private void pageByPrice(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
        //1、获取请求参数pageNo,pageSize,min,max;
        int pageNo=WebUtils.parseInt(req.getParameter("pageNo"),1);
        int pageSize=WebUtils.parseInt(req.getParameter("pageSize"),Page.PAGE_SIZE);
        int min =WebUtils.parseInt(req.getParameter("min"),0);
        int max =WebUtils.parseInt(req.getParameter("max"),100000);
        Page<Book> page = bookService.pageByPrice(pageNo,pageSize,min,max);
        StringBuilder sb = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        if (req.getParameter("min")!=null){
            sb.append("&min=").append(req.getParameter("min"));
        }
        if (req.getParameter("max")!=null){
            sb.append("&max=").append(req.getParameter("max"));
        }
        page.setUrl(sb.toString());

        req.setAttribute("page",page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req,resp);
    }
}
