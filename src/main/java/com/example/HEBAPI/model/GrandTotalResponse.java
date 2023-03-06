package com.example.HEBAPI.model;

import jakarta.persistence.Entity;

import java.text.DecimalFormat;

@Entity
public class GrandTotalResponse {

    private float GrandTotal;

    //constructors
    public GrandTotalResponse(float total)
    {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.GrandTotal = Float.parseFloat(df.format(total));
    }
    //getters and setters

    public float getGrandTotal() {
        return GrandTotal;
    }

    public void setGrandTotal(float grandTotal) {
        GrandTotal = grandTotal;
    }
}
