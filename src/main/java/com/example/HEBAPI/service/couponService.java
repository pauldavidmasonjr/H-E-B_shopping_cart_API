package com.example.HEBAPI.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class couponService {
    public static Map<String, Double> loadCoupons() throws IOException, ParseException, JSONException {
        //get json of coupons from file
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("coupons.json");

        BufferedReader streamReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
        {
            responseStrBuilder.append(inputStr);
        }


        JSONObject jsonObject = new JSONObject(responseStrBuilder.toString());

        JSONArray couponArray = jsonObject.getJSONArray("coupons");

        //load coupons into Map
        Map<String, Double> couponMap = new HashMap<String, Double>();
        for(int i = 0; i < couponArray.length(); i++)
        {
            JSONObject tmpCoupon = couponArray.getJSONObject(i);
            //System.out.println("COUPON DISCOUNT: " + tmpCoupon.get("couponName"));
            String discountName = tmpCoupon.getString("couponName");
            String arr[] = discountName.split(" ", 2);

            couponMap.put(arr[0], tmpCoupon.getDouble("discountPrice"));
        }


        return couponMap;
    }
}
