package com.example.weather;

import org.json.JSONObject;

public record weatherINFO(String city, String region, String temp, String status) {

    public static weatherINFO fromJSON(JSONObject json)
    {
        JSONObject curr = json.getJSONObject("current"), loc = json.getJSONObject("location"), cond = curr.getJSONObject("condition");
        String city = (String) loc.get("name");
        String region = (String) loc.get("region");
        String temp = (String) curr.get("temp_c").toString();
        String status = (String) cond.get("text").toString();

        return new weatherINFO(city,region,temp,status);
    }
}
