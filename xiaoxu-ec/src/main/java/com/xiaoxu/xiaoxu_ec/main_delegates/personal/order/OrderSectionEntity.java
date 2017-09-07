package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class OrderSectionEntity extends SectionEntity<OrderSectionItemEntity> {



    private String receiverName ;
    private String statusDesc ;//未支付
    private double payment ;
    private long orderNo ;
    private String createTime;
    private int shippingId;


    private boolean mIsMore = false;
    private int mId = -1;





    public OrderSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public OrderSectionEntity(OrderSectionItemEntity orderSectionItemEntity) {
        super(orderSectionItemEntity);
    }

    public boolean isIsMore() {
        return mIsMore;
    }

    public void setIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getStatusDesc() {
        return statusDesc;
    }

    public void setStatusDesc(String statusDesc) {
        this.statusDesc = statusDesc;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public long getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(long orderNo) {
        this.orderNo = orderNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getShippingId() {
        return shippingId;
    }

    public void setShippingId(int shippingId) {
        this.shippingId = shippingId;
    }

    public boolean ismIsMore() {
        return mIsMore;
    }

    public void setmIsMore(boolean mIsMore) {
        this.mIsMore = mIsMore;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
