/*
 * OrderNumber.java 1.0.0 2017/11/27  14:51 
 * Copyright © 2014-2017,52mamahome.com.All rights reserved
 * history :
 *     1. 2017/11/27  14:51 created by xulihua
 */
package com.yinfeixing.utils.common;

public class OrderNumber {

    //25位订单
    private String orderId;

    //较短订单号
    private String orderNo;

    public OrderNumber() {
    }

    public OrderNumber(String orderId, String orderNo) {
        this.orderId = orderId;
        this.orderNo = orderNo;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
