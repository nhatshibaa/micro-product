package com.example.demo_product_choreogrphy.enumEntity;

public enum ProductEnum {
    //    PENDING, OUT_OF_STOCK, DONE, RETURN, RETURNED;
    PENDING(0), DONE(1), OUT_OF_STOCK(-1), RETURN(-2), RETURNED(-3);

    public final Integer value;

    ProductEnum(Integer value) {
        this.value = value;
    }
}
