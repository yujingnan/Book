package com.atguigu.service.impl;

import com.atguigu.dao.BookDao;
import com.atguigu.dao.OrderDao;
import com.atguigu.dao.OrderItemDao;
import com.atguigu.dao.impl.BookDaoImpl;
import com.atguigu.dao.impl.OrderDapImpl;
import com.atguigu.dao.impl.OrderItemDaoImpl;
import com.atguigu.pojo.*;
import com.atguigu.service.OrderService;

import java.util.Date;
import java.util.Map;

public class OrdeServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDapImpl();
    private OrderItemDao orderItemDao=new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Car car, Integer userId) {
        //创建一个orderId
        String orderId=System.currentTimeMillis()+""+userId;
        //创建order对象
        Order order=new Order(orderId,new Date(),car.getTotalPrice(),0,userId);
        //保存order
        orderDao.saveOrder(order);
        //遍历购物车中每一个商品项转换成订单项保存到数据库
        for (Map.Entry<Integer,CarItem> entry:car.getItems().entrySet()){
            //获取每一个购物车中的商品项
            CarItem carItem=entry.getValue();
            //转化为每一个订单项
            OrderItem orderItem = new OrderItem(null,carItem.getName(),carItem.getCount(),carItem.getPrice(),carItem.getTotalPrice(),orderId);
            //保存订单想到数据库
            orderItemDao.saveOrderItem(orderItem);
            //更新库存和销量
            Book book = bookDao.queryBookById(carItem.getId());
            book.setSales(book.getSales()+carItem.getCount());
            book.setStock(book.getStock()-carItem.getCount());
            bookDao.updateBook(book);
        }
        car.clear();
        return orderId;
    }
}
