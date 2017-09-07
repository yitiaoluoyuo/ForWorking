package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class OrderSectionItemEntity {

    private int mProductId;
    private String mProductName;
    private String mProductImage;
    private double mCurrentUnitPrice;
    private int mQuantity;
    private double mTotalPrice;
    private String mCreateTime;

    public int getProductId() {
        return mProductId;
    }

    public void setProductId(int mProductId) {
        this.mProductId = mProductId;
    }

    public String getProductName() {
        return mProductName;
    }

    public void setProductName(String mProductName) {
        this.mProductName = mProductName;
    }

    public String getProductImage() {
        return mProductImage;
    }

    public void setProductImage(String mProductImage) {
        this.mProductImage = mProductImage;
    }

    public double getCurrentUnitPrice() {
        return mCurrentUnitPrice;
    }

    public void setCurrentUnitPrice(double mCurrentUnitPrice) {
        this.mCurrentUnitPrice = mCurrentUnitPrice;
    }

    public int getQuantity() {
        return mQuantity;
    }

    public void setQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    public void setTotalPrice(double mTotalPrice) {
        this.mTotalPrice = mTotalPrice;
    }

    public String getCreateTime() {
        return mCreateTime;
    }

    public void setCreateTime(String mCreateTime) {
        this.mCreateTime = mCreateTime;
    }
}
