package com.gzfgeh.nbapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import com.gzfgeh.nbapp.APP;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShareUtils {
    public final static String SETTING = "Setting";

    private ShareUtils() {
    }

    public static void putValue(int resKey, int value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(context.getString(resKey), value);
        sp.commit();
    }

    public static void putValue(int resKey, boolean value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(context.getString(resKey), value);
        sp.commit();
    }

    public static void putValue(int resKey, String value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(context.getString(resKey), value);
        sp.commit();
    }

    public static void putValue(int resKey, long value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putLong(context.getString(resKey), value);
        sp.commit();
    }

    public static void putValue(String resKey, long value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putLong(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, int value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putInt(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, boolean value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putBoolean(resKey, value);
        sp.commit();
    }

    public static void putValue(String resKey, String value) {
        Context context = APP.getContext();
        Editor sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE).edit();
        sp.putString(resKey, value);
        sp.commit();
    }

    public static int getValue(int resKey, int defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(context.getString(resKey), defValue);
        return value;
    }

    public static boolean getValue(int resKey, boolean defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(context.getString(resKey), defValue);
        return value;
    }

    public static boolean getValue(String resKey, boolean defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        boolean value = sp.getBoolean(resKey, defValue);
        return value;
    }

    public static String getValue(int resKey, String defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(context.getString(resKey), defValue);
        return value;
    }

    public static int getValue(String resKey, int defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        int value = sp.getInt(resKey, defValue);
        return value;
    }

    public static String getValue(String resKey, String defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String value = sp.getString(resKey, defValue);
        return value;
    }

    public static long getValue(String resKey, long defValue) {
        Context context = APP.getContext();
        SharedPreferences sp = context.getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        long value = sp.getLong(resKey, defValue);
        return value;
    }

    public static void saveStringList(String key, List<String> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            String item = datas.get(i);
            JSONObject object = new JSONObject();
            try {
                object.put(i + "", item);
            } catch (JSONException e) {
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = APP.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    public static List<String> getStringList(String key) {
        List<String> datas = new ArrayList<>();
        SharedPreferences sp = APP.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                String item = itemObject.getString(i + "");
                datas.add(item);
            }
        } catch (JSONException e) {
        }
        return datas;
    }

    public static void saveList(String key, List<Map<String, String>> datas) {
        JSONArray mJsonArray = new JSONArray();
        for (int i = 0; i < datas.size(); i++) {
            Map<String, String> itemMap = datas.get(i);
            Iterator<Map.Entry<String, String>> iterator = itemMap.entrySet().iterator();
            JSONObject object = new JSONObject();
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                try {
                    object.put(entry.getKey(), entry.getValue());
                } catch (JSONException e) {

                }
            }
            mJsonArray.put(object);
        }

        SharedPreferences sp = APP.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(key, mJsonArray.toString());
        editor.commit();
    }

    public static List<Map<String, String>> getList(String key) {
        List<Map<String, String>> datas = new ArrayList<>();
        SharedPreferences sp = APP.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        String result = sp.getString(key, "");
        try {
            JSONArray array = new JSONArray(result);
            for (int i = 0; i < array.length(); i++) {
                JSONObject itemObject = array.getJSONObject(i);
                Map<String, String> itemMap = new HashMap<String, String>();
                JSONArray names = itemObject.names();
                if (names != null) {
                    for (int j = 0; j < names.length(); j++) {
                        String name = names.getString(j);
                        String value = itemObject.getString(name);
                        itemMap.put(name, value);
                    }
                }
                datas.add(itemMap);
            }
        } catch (JSONException e) {

        }

        return datas;
    }

    public static void delete() {
        SharedPreferences sp = APP.getContext().getSharedPreferences(SETTING, Context.MODE_PRIVATE);
        sp.edit().clear().commit();
    }
}