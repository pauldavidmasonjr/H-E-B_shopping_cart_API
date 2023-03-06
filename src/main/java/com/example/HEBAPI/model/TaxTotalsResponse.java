package com.example.HEBAPI.model;

import java.text.DecimalFormat;

public class TaxTotalsResponse {

    private float subtotal;
    private float taxtotal;
    private float grandtotal;

    //Constructors
    public TaxTotalsResponse()
    {
        this.subtotal = 0;
        this.taxtotal = 0;
        this.grandtotal = 0;
    }
    public TaxTotalsResponse(float subtotal, double taxtotal, double grandtotal)
    {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.subtotal = subtotal;
        this.taxtotal = Float.parseFloat(df.format((float)taxtotal));
        this.grandtotal = Float.parseFloat(df.format((float)grandtotal));
    }

    //GETTERS and SETTERS

    public float getSubtotal() {
        return subtotal;
    }

    public float getTaxtotal() {
        return taxtotal;
    }

    public float getGrandtotal() {
        return grandtotal;
    }

    public void setSubtotal(float subtotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.subtotal = Float.parseFloat(df.format(subtotal));
    }

    public void setTaxtotal(float taxtotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.taxtotal = Float.parseFloat(df.format(taxtotal));
    }

    public void setGrandtotal(float grandtotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        this.grandtotal = Float.parseFloat(df.format(grandtotal));
    }
}
