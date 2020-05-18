package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Car {
//    private Integer totalCount;
//    private BigDecimal totalPrice;
    private Map<Integer,CarItem> items=new LinkedHashMap<Integer, CarItem>();

    /**
     * 添加商品项
     */
    public void addItem(CarItem carItem){
        //先查看购物车中是否已经添加过该商品，如果已添加，则数量累加，总金额更新，如果没有添加过，直接放到集合中即可
        CarItem item = items.get(carItem.getId());
        if (item==null){
            items.put(carItem.getId(),carItem);
        }else {
            item.setCount(item.getCount()+1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
    /**
     * 删除商品项
     */
    public void deleteItem(Integer id){
        items.remove(id);
    }
    /**
     * 清空购物车
     */
    public void clear(){
        items.clear();
    }
    /**
     * 修改商品数量
     */
    public void updateCount(Integer id,Integer count){
        CarItem carItem = items.get(id);
        if (carItem!=null){
            carItem.setCount(count);
            carItem.setTotalPrice(carItem.getPrice().multiply(new BigDecimal(carItem.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer,CarItem> entry : items.entrySet()){
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }

//    public void setTotalCount(Integer totalCount) {
//        this.totalCount = totalCount;
//    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer,CarItem> entry: items.entrySet()){
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }

//    public void setTotalPrice(BigDecimal totalPrice) {
//        this.totalPrice = totalPrice;
//    }

    public Map<Integer, CarItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CarItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Car{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
