package com.atguigu.web;

import com.atguigu.pojo.Book;
import com.atguigu.pojo.Car;
import com.atguigu.pojo.CarItem;
import com.atguigu.service.BookService;
import com.atguigu.service.impl.BookServiceImpl;
import com.atguigu.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    /**
     * 添加购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        System.out.println("加入购物车");
//        System.out.println("商品编号"+req.getParameter("id"));
        //获取商品编号
        int id= WebUtils.parseInt(req.getParameter("id"),0);
        //调用bookService.queryBookById(id):Book得到图书信息
        Book book =bookService.queryBookById(id);
        //把图书信息转化为CarItem商品项
        CarItem carItem=new CarItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //调用Car.addItem(CarItem);添加商品项
        Car car = (Car) req.getSession().getAttribute("car");
        if (car==null){
            car = new Car();
            req.getSession().setAttribute("car",car);

        }
        car.addItem(carItem);

        System.out.println(car);
//        //重定向回商品列表页面
//        resp.sendRedirect(req.getContextPath());
        req.getSession().setAttribute("lastName",carItem.getName());
        //重定向回原商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt("id",0);
        Car car = (Car) req.getSession().getAttribute("car");
        if (car!=null){
            car.deleteItem(id);
            resp.sendRedirect(req.getHeader("Referer"));
        }



    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = (Car) req.getSession().getAttribute("car");
        if (car!=null){
            car.clear();
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Car car = (Car) req.getSession().getAttribute("car");
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count=WebUtils.parseInt(req.getParameter("count"),0);
        if (car!=null){
            car.updateCount(id,count);
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
