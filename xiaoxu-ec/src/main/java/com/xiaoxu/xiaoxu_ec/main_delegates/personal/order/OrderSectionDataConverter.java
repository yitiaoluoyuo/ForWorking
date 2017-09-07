package com.xiaoxu.xiaoxu_ec.main_delegates.personal.order;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

/**
 * Created by xiaoxu on 2017/9/7.
 */

public class OrderSectionDataConverter {


    public ArrayList<OrderSectionEntity> convertToEntityList(String json) {

        final JSONArray jsonSectionList = JSON.parseObject(json).getJSONObject("data").getJSONArray("list");

        ArrayList<OrderSectionEntity> sectionList = new ArrayList<>();

        final int size = jsonSectionList.size();
        //循环订单
        for (int i = 0; i < size; i++) {
            //取出订单head
            final JSONObject orderData = jsonSectionList.getJSONObject(i);
            //final String paymentTypeDesc = orderData.getString("paymentTypeDesc");//在线支付
            ///final int postage = orderData.getInteger("postage");
            final String receiverName = orderData.getString("receiverName");
            final String statusDesc = orderData.getString("statusDesc");//未支付
            final double payment = orderData.getDouble("payment");
            final long orderNo = orderData.getLong("orderNo");
            final String createTime = orderData.getString("createTime");
            final int shippingId = orderData.getInteger("shippingId");//为订单详情备用数据
            //为订单head添加信息
            OrderSectionEntity orderSectionEntity = new OrderSectionEntity(true, "header");
            orderSectionEntity.setId(i);
            orderSectionEntity.setIsMore(true);
            orderSectionEntity.setReceiverName(receiverName);
            orderSectionEntity.setStatusDesc(statusDesc);
            orderSectionEntity.setPayment(payment);
            orderSectionEntity.setOrderNo(orderNo);
            orderSectionEntity.setCreateTime(createTime);
            orderSectionEntity.setShippingId(shippingId);
            sectionList.add(orderSectionEntity);

            //循环订单商品
            final JSONArray goodsItemData = orderData.getJSONArray("orderItemVoList");
            final int orderItemSize = goodsItemData.size();
            for (int j = 0; j < orderItemSize; j++) {
                OrderSectionItemEntity orderSectionItemEntity = new OrderSectionItemEntity();
                final JSONObject goodsData = goodsItemData.getJSONObject(j);
                final int productId = goodsData.getInteger("productId");
                final String productName = goodsData.getString("productName");
                final String productImage = goodsData.getString("productImage");
                final double currentUnitPrice = goodsData.getDouble("currentUnitPrice");
                final int quantity = goodsData.getInteger("quantity");
                final double totalPrice = goodsData.getDouble("totalPrice");
                //为订单item添加信息
                orderSectionItemEntity.setProductId(productId);
                orderSectionItemEntity.setProductImage(productImage);
                orderSectionItemEntity.setProductName(productName);
                orderSectionItemEntity.setCurrentUnitPrice(currentUnitPrice);
                orderSectionItemEntity.setQuantity(quantity);
                orderSectionItemEntity.setTotalPrice(totalPrice);

                sectionList.add(new OrderSectionEntity(orderSectionItemEntity));
            }
            //商品内容循环结束
        }
        //Section循环结束
        return sectionList;
    }


}
