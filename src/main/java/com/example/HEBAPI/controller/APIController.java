package com.example.HEBAPI.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

import com.example.HEBAPI.model.*;
import com.example.HEBAPI.service.couponService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIController {
    @Autowired
    //APIService empService;

    @GetMapping("/hello")
    public String readEmployees() {
        return "hello world HEB API is live";
    }

    @PostMapping(value = "calculateGrandTotal", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<GrandTotalResponse> calGranTotal(@RequestBody Map<String, Object> payload) throws JSONException {

        //System.out.println(payload);

        JSONObject jsonObj = new JSONObject(payload);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float GrandTotal = 0;

        JSONArray c = jsonObj.getJSONArray("items");
        for (int i = 0; i < c.length(); i++) {
            JSONObject obj = c.getJSONObject(i);

            GrandTotal += BigDecimal.valueOf(obj.getDouble("price")).floatValue();
            //System.out.println(BigDecimal.valueOf(obj.getDouble("price")).floatValue());
        }
        GrandTotalResponse result = new GrandTotalResponse(GrandTotal);


        return ResponseEntity.ok(result);
    }

    @PostMapping("/calculateSubTaxTotal")
    public ResponseEntity<TaxTotalsResponse> calSubTaxTotal(@RequestBody Map<String, Object> payload) throws Exception {

        //System.out.println(payload);

        JSONObject jsonObj = new JSONObject(payload);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float Subtotal = 0;

        JSONArray c = jsonObj.getJSONArray("items");
        for (int i = 0; i < c.length(); i++) {
            JSONObject obj = c.getJSONObject(i);

            Subtotal += BigDecimal.valueOf(obj.getDouble("price")).floatValue();
            //System.out.println(BigDecimal.valueOf(obj.getDouble("price")).floatValue());
        }
        double taxtotal = Subtotal * .0825;

        TaxTotalsResponse result = new TaxTotalsResponse(Subtotal, taxtotal, Subtotal + taxtotal);

        //System.out.println("TOTAL: " + df.format(Subtotal));

        return ResponseEntity.ok(result);

    }

    @PostMapping("/calculateIsTaxableTotal")
    public ResponseEntity<IsTaxableResponse> calIsTaxableTotal(@RequestBody Map<String, Object> payload) throws Exception {

        //System.out.println(payload);

        JSONObject jsonObj = new JSONObject(payload);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float subtotal = 0;
        float taxTotal = 0;
        float taxableSubTotal = 0;

        JSONArray c = jsonObj.getJSONArray("items");
        for (int i = 0; i < c.length(); i++) {
            JSONObject obj = c.getJSONObject(i);
            cartItem item = new cartItem(obj);
            if (item.isTaxable()) {
                // add to taxTotal
                float tmpTax = BigDecimal.valueOf(item.getPrice() * .0825).floatValue();
                taxTotal += tmpTax;
                taxableSubTotal += item.getPrice();
                subtotal += item.getPrice();

            } else {
                subtotal += BigDecimal.valueOf(item.getPrice()).floatValue();
                //System.out.println(BigDecimal.valueOf(obj.getDouble("price")).floatValue());
            }


        }


        IsTaxableResponse result = new IsTaxableResponse(subtotal, taxableSubTotal, taxTotal, subtotal + taxTotal);

        //System.out.println("TOTAL: " + df.format(Subtotal));

        return ResponseEntity.ok(result);

    }

    @PostMapping("/calculateCouponDiscount")
    public ResponseEntity<couponTotalsResponse> calCouponDiscount(@RequestBody Map<String, Object> payload) throws Exception {

        //System.out.println(payload);

        JSONObject jsonObj = new JSONObject(payload);

        //load discounts
        //System.out.println("loading coupons");
        Map<String, Double> couponMap = couponService.loadCoupons();
        //System.out.println("COUPONMap: " + couponMap);
        //System.out.println("finish loading coupons");

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);

        float subtotalBeforeDiscounts = 0;
        float taxTotal = 0;
        float taxableSubTotalAfterDiscount = 0;
        float discountTotal = 0;
        //float afterDiscountSubtotal = 0;


        JSONArray c = jsonObj.getJSONArray("items");
        for (int i = 0; i < c.length(); i++) {
            JSONObject obj = c.getJSONObject(i);
            cartItem item = new cartItem(obj);

            subtotalBeforeDiscounts += item.getPrice();
            //System.out.println("\n\nITEM: " + item.getItemName());
            //System.out.println("ITEM PRICE: " + item.getPrice());
            if (item.isTaxable()) {
                //System.out.println("TAXABLE --------------------");
                //check for discount
                Boolean itemCouponProcessed = false;
                Boolean itemProcessed = false;

                float tmpItemFinalPriceAfterCouponProcessing = 0;
                for (Map.Entry<String, Double> entry : couponMap.entrySet()) {


                    if (item.getItemName().contains(entry.getKey()) && !itemCouponProcessed) {
                        //System.out.println("Coupon Match Found");
                        tmpItemFinalPriceAfterCouponProcessing = BigDecimal.valueOf(item.getPrice()).floatValue() - BigDecimal.valueOf(entry.getValue()).floatValue();

                        // update discount total
                        discountTotal += BigDecimal.valueOf(entry.getValue()).floatValue();

                        itemCouponProcessed = true;
                    }
                    if (!itemCouponProcessed && !itemProcessed) {
                        // add to taxTotal
                        //System.out.println("NOT COUPON MATCH");

                        //this is to prvent adding price twice if the first word is not the coupon word
                        tmpItemFinalPriceAfterCouponProcessing = item.getPrice();

                        itemProcessed = true;
                    }
                }

                taxableSubTotalAfterDiscount += tmpItemFinalPriceAfterCouponProcessing;
                //System.out.println("--- taxable subtotal after discount " + taxableSubTotalAfterDiscount);
            } else {
                //System.out.println("NON TAXABLE --------------------");
                //check for discount
                Boolean itemCouponProcessed = false;
                Boolean itemProcessed = false;


                for (Map.Entry<String, Double> entry : couponMap.entrySet()) {
                    float tmpItemFinalPriceAfterCouponProcessing = 0;
                    if (item.getItemName().contains(entry.getKey()) && !itemCouponProcessed) {
                        //System.out.println("Coupon Match Found");
                        tmpItemFinalPriceAfterCouponProcessing = BigDecimal.valueOf(item.getPrice()).floatValue() - BigDecimal.valueOf(entry.getValue()).floatValue();

                        // update discount total
                        discountTotal += BigDecimal.valueOf(entry.getValue()).floatValue();
                        //System.out.println("--- discount total: " + discountTotal);

                        itemCouponProcessed = true;
                    }
                    if (!itemCouponProcessed && !itemProcessed) {
                        // add to taxTotal
                        //System.out.println("NOT COUPON MATCH");
                        tmpItemFinalPriceAfterCouponProcessing = item.getPrice();

                        //System.out.println("--- taxable subtotal after discount " + taxableSubTotalAfterDiscount);
                        itemProcessed = true;
                    }
                }

            }
        }

        taxTotal = BigDecimal.valueOf(taxableSubTotalAfterDiscount * .0825).floatValue();
        couponTotalsResponse result = new couponTotalsResponse(subtotalBeforeDiscounts, discountTotal, subtotalBeforeDiscounts - discountTotal, taxableSubTotalAfterDiscount, taxTotal, (subtotalBeforeDiscounts - discountTotal) + taxTotal);

        return ResponseEntity.ok(result);

    }
}
