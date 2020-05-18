package com.atguigu.test;

import com.atguigu.pojo.Car;
import com.atguigu.pojo.CarItem;
import com.atguigu.service.OrderService;
import com.atguigu.service.impl.OrdeServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Car car = new Car();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrdeServiceImpl();

        System.out.println("订单号是："+orderService.createOrder(car,1));
    }
}