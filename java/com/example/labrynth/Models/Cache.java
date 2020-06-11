package com.example.labrynth.Models;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Set;
import java.util.TreeSet;

public class Cache {
    private static Cache single_instance = null;
    public String userName;
    public Set<Integer> currentTurnGameIDS = new TreeSet<Integer>();
    public Set<Integer> notCurrentTurnGameIDS = new TreeSet<Integer>();
    public Set<Integer> pendingGameIDS = new TreeSet<Integer>();

    // private constructor restricted to this class itself
    private Cache()
    {
    }

    // static method to create instance of Singleton class
    public static Cache getInstance()
    {
        if (single_instance == null) {
            single_instance = new Cache();
        }
        return single_instance;
    }
    public static Boolean loadCache(){
        try {
            single_instance.currentTurnGameIDS.clear();
            single_instance.notCurrentTurnGameIDS.clear();
            single_instance.pendingGameIDS.clear();
            loadCurrentGames();
            loadNotCurrentGames();
            loadPendingGames();
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public static void loadCurrentGames() throws JSONException{
        JSONObject jsonObject = new JSONObject();
        try{
            jsonObject.put("userName", single_instance.userName);
        } catch (JSONException e) {
        Log.e("loadCurretnGames", "Json Error:" + e.getMessage());
    }
        JSONObject result = Proxy.doPostAction("currentGames", jsonObject);
    }
    public static void loadNotCurrentGames() throws JSONException{

    }
    public static void loadPendingGames() throws JSONException{

    }


}
