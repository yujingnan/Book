package com.atguigu.test;

import com.atguigu.pojo.Car;
import com.atguigu.pojo.CarItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CarTest {

    @Test
    public void addItem() {
        Car car = new Car();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(car);
    }

    @Test
    public void deleteItem() {
        Car car = new Car();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        car.deleteItem(1);
        System.out.println(car);
    }

    @Test
    public void clear() {
        Car car = new Car();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        System.out.println(car);
        car.clear();
        System.out.println(car);
    }

    @Test
    public void updateCount() {
        Car car = new Car();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.addItem(new CarItem(2,"数据结构与算法",1,new BigDecimal(100),new BigDecimal(100)));
        car.deleteItem(1);
        car.clear();
        car.addItem(new CarItem(1,"java从入门到精通",1,new BigDecimal(99),new BigDecimal(99)));
        car.updateCount(1,10);
        System.out.println(car);
    }
}