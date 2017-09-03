package com.xiaoxu.xiaoxu_core.ui.recycler;

/**
 * Created by xiaoxu on 2017/8/27.
 */

public enum MultipleFields {

    ID,ITEM_TYPE,
    TITLE,
    TEXT,
    IMAGE_URL,
    BANNERS,
    SPAN_SIZE,
    TAG,

    /**
     * users
     */

    USER_ID,

    /**
     * products
     */
     CATEGORY_ID,PRODUCT_ID, NAME,
    SUBTITLE, MAIN_IMAGE, PRICE, STATUS, IMAGE_HOST,PRODUCT_STATUS,PRODUCT_STOCK,

    /**
     * shop cart
     */
    QUANTITY,IS_SELECTED,POSITION

    /**
     * order
     */

    ,TIME

    /**
     * order
     */

    ,ADDRESS,PHONE
}
