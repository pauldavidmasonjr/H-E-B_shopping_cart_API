package com.example.HEBAPI.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class coupon {
    private String couponName;
    private int appliedSku;
    private float discountPrice;

    //CONSTRUCTOR
    coupon(JSONObject object) throws JSONException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        this.couponName = object.getString("couponName");
        this.appliedSku = object.getInt("appliedSku");
        this.discountPrice = Float.parseFloat(df.format(object.get("discountPrice")));
    }

    //GETTERS and SETTERS

    public String getCouponName() {
        return couponName;
    }

    public int getAppliedSku() {
        return appliedSku;
    }

    public float getDiscountPrice() {
        return discountPrice;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public void setAppliedSku(int appliedSku) {
        this.appliedSku = appliedSku;
    }

    public void setDiscountPrice(float discountPrice) {
        this.discountPrice = discountPrice;
    }
}
