package com.example.labrynth.Models;

import android.util.Log;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Proxy {
    public static final String tag = "Proxy";
    public static JSONObject doPostAction(String methodCall, JSONObject jObject){
        URL url;
        try {
            url = new URL("https://labrynth-backend.herokuapp.com/" + methodCall);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStream requestBody = connection.getOutputStream();
            requestBody.write(jObject.toString().getBytes("UTF-8"));
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream responseBody = connection.getInputStream();
                ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int length = 0;
                while ((length = responseBody.read(buffer)) != -1) {
                    byteOS.write(buffer, 0, length);
                }
                String responseBodyData = byteOS.toString();
                return new JSONObject(responseBodyData);
            }
            else
            {
                Log.e(tag, "got bad response code");
            }
        }
        catch (Exception e) { Log.e(tag, "exception in post action "+ e.getMessage());}
        return null;
    }
}
