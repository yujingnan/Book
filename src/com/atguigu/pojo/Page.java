package com.atguigu.pojo;

import java.util.List;

/**
 * Page是分页的模型对象
 * @param <T>是具体的模块的javaBean类
 */
public class Page <T>{
    public static final Integer PAGE_SIZE=4;
    //当前页
    private Integer pageNo;
    //总页码
    private Integer pageTotal;
    //总记录数
    private Integer PageTotalCount;
    //每页显示记录数
    private Integer pageSize=PAGE_SIZE;
    //当前页数据
    private List<T> items;
    private String url;

    public Page() {
    }

    public Page(Integer pageNo, Integer pageTotal, Integer pageTotalCount, Integer pageSize, List<T> items) {
        this.pageNo = pageNo;
        this.pageTotal = pageTotal;
        PageTotalCount = pageTotalCount;
        this.pageSize = pageSize;
        this.items = items;
    }

    public Page(Integer pageNo, Integer pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotalCount() {
        return PageTotalCount;
    }

    public void setPageTotalCount(Integer pageTotalCount) {
        PageTotalCount = pageTotalCount;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNo=" + pageNo +
                ", pageTotal=" + pageTotal +
                ", PageTotalCount=" + PageTotalCount +
                ", pageSize=" + pageSize +
                ", items=" + items +
                ", url='" + url + '\'' +
                '}';
    }
}
