package com.inn.cafe.util;

import com.google.common.base.Strings;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CafeUtils {
    private CafeUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus httpStatus){
        return new ResponseEntity<String>("{\"message\":\"" + responseMessage + "\"}",httpStatus);
    }

    public static String getUUID() {
        Date date = new Date();
        long time = date.getTime();
        return "BILL-" + time;
    }

    public static JSONArray getJSONArrayFromString(String data) throws JSONException{
        JSONArray jsonArray = new JSONArray(data);
        return jsonArray;
    }

    public static Map<String , Object> getMApFromJson(String data){
        if (!Strings.isNullOrEmpty(data)){
            return new Gson().fromJson(data,new TypeToken<Map<String , Object>>(){}.getType());
        }
        return new HashMap<>();
    }

    public static Boolean isFileExists(String path){
        try {
            File file = new File(path);
            return (file != null && file.exists()) ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
