package com.atguigu.test;

import com.atguigu.dao.OrderDao;
import com.atguigu.dao.impl.OrderDapImpl;
import com.atguigu.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDapImpl();
        orderDao.saveOrder(new Order("1234567890",new Date(),new BigDecimal(100),0,1));
    }
}