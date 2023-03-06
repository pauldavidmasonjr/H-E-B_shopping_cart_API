package com.example.HEBAPI.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class cartItem {
//    "itemName": "Central Market Organics Mushroom Pasta Sauce",
    private String itemName;
//            "sku": 64474859,
    private int sku;
//            "isTaxable": true,
    private boolean isTaxable;
//            "ownBrand": true,
    private boolean ownBrand;
//            "price": 7.12
    private float price;

    //CONSTRUCTOR

    public cartItem(String itemName, int sku, boolean isTaxable, boolean ownBrand, float price) {
        this.itemName = itemName;
        this.sku = sku;
        this.isTaxable = isTaxable;
        this.ownBrand = ownBrand;
        this.price = price;
    }

    public cartItem(JSONObject object) throws JSONException {
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        this.itemName = object.get("itemName").toString();
        this.sku = Integer.parseInt(object.get("sku").toString());
        this.isTaxable = ((Boolean) object.get("isTaxable")).booleanValue();
        this.ownBrand = ((Boolean) object.get("ownBrand")).booleanValue();
        this.price = Float.parseFloat(df.format(object.get("price")));
    }

    //GETTERS and SETTERS

    public String getItemName() {
        return itemName;
    }

    public int getSku() {
        return sku;
    }

    public boolean isTaxable() {
        return isTaxable;
    }

    public boolean isOwnBrand() {
        return ownBrand;
    }

    public float getPrice() {
        return price;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setSku(int sku) {
        this.sku = sku;
    }

    public void setTaxable(boolean taxable) {
        isTaxable = taxable;
    }

    public void setOwnBrand(boolean ownBrand) {
        this.ownBrand = ownBrand;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
