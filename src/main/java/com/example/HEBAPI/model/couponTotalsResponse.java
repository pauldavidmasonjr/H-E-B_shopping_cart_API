package com.example.HEBAPI.model;

import java.text.DecimalFormat;

public class couponTotalsResponse {
    private float beforeDiscountsSubtotal;
    private float discountTotal;
    private float subtotalAfterDiscounts;
    private float taxableSubtotal;
    private float taxTotal;
    private float grandTotal;

    //CONSTRUCTOR


    public couponTotalsResponse(float beforeDiscountsSubtotal,
                                float discountTotal,
                                float subtotalAfterDiscounts,
                                double taxableSubtotal,
                                double taxTotal,
                                double grandTotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.beforeDiscountsSubtotal = beforeDiscountsSubtotal;
        this.discountTotal = discountTotal;
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
        this.taxableSubtotal = Float.parseFloat(df.format((float)taxableSubtotal));;
        this.taxTotal = Float.parseFloat(df.format((float)taxTotal));;
        this.grandTotal = Float.parseFloat(df.format((float)grandTotal));
    }

    //GETTERS and SETTERS

    public float getBeforeDiscountsSubtotal() {
        return beforeDiscountsSubtotal;
    }

    public float getDiscountTotal() {
        return discountTotal;
    }

    public float getSubtotalAfterDiscounts() {
        return subtotalAfterDiscounts;
    }

    public float getTaxableSubtotal() {
        return taxableSubtotal;
    }

    public float getTaxTotal() {
        return taxTotal;
    }

    public float getGrandTotal() {
        return grandTotal;
    }

    public void setBeforeDiscountsSubtotal(float beforeDiscountsSubtotal) {
        this.beforeDiscountsSubtotal = beforeDiscountsSubtotal;
    }

    public void setDiscountTotal(float discountTotal) {
        this.discountTotal = discountTotal;
    }

    public void setSubtotalAfterDiscounts(float subtotalAfterDiscounts) {
        this.subtotalAfterDiscounts = subtotalAfterDiscounts;
    }

    public void setTaxableSubtotal(float taxableSubtotal) {
        this.taxableSubtotal = taxableSubtotal;
    }

    public void setTaxTotal(float taxTotal) {
        this.taxTotal = taxTotal;
    }

    public void setGrandTotal(float grandTotal) {
        this.grandTotal = grandTotal;
    }
}
