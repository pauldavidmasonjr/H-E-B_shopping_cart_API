package com.example.HEBAPI.model;

import java.text.DecimalFormat;

public class IsTaxableResponse {

    private float Subtotal;
    private float TaxableSubtotal;
    private float TaxTotal;
    private float GrandTotal;

    //Constructors

    public IsTaxableResponse(float subtotal,
                             double taxableSubtotal,
                             double taxTotal,
                             double grandTotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        Subtotal = subtotal;
        TaxableSubtotal = Float.parseFloat(df.format((float)taxableSubtotal));
        TaxTotal = Float.parseFloat(df.format((float)taxTotal));
        GrandTotal = Float.parseFloat(df.format((float)grandTotal));
    }

    //GETTERS and SETTERS

    public float getSubtotal() {
        return Subtotal;
    }

    public float getTaxableSubtotal() {
        return TaxableSubtotal;
    }

    public float getTaxTotal() {
        return TaxTotal;
    }

    public float getGrandTotal() {
        return GrandTotal;
    }

    public void setSubtotal(float subtotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        Subtotal = Float.parseFloat(df.format(subtotal));
    }

    public void setTaxableSubtotal(float taxableSubtotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        TaxableSubtotal = Float.parseFloat(df.format(taxableSubtotal));
    }

    public void setTaxTotal(float taxTotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        TaxTotal = Float.parseFloat(df.format(taxTotal));
    }

    public void setGrandTotal(float grandTotal) {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        GrandTotal = Float.parseFloat(df.format(grandTotal));
    }
}
