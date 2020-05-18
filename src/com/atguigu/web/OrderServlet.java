package com.atguigu.web;

import com.atguigu.pojo.Car;
import com.atguigu.pojo.User;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrdeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {
    OrderService orderService = new OrdeServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取Car购物车对象
        Car car = (Car) req.getSession().getAttribute("car");
        //获取UserId
        User user = (User) req.getSession().getAttribute("user");
        if (user==null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        int userId = user.getId();
        //调用orderService.createOrder(car,userId)方法

        String orderId = orderService.createOrder(car,userId);
//        req.setAttribute("orderId",orderId);
//        req.getRequestDispatcher("/pages/cart/checkout.jsp").forward(req,resp);
        //解决重复提交
        req.getSession().setAttribute("orderId",orderId);
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");
    }
}
