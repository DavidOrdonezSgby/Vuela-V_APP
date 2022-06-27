package com.example.vuelav_app.Logico.Token;

import android.content.Context;
import android.content.SharedPreferences;

public class TokenController {

    private final static String SHARED_PREF_NAME = "net.rouk1.SHARED_PREF_NAME";
    private final static String TOKEN_KEY = "net.rouk1.TOKEN_KEY";
    private final static String ID_CLIENT="idBase";
    private final static String NOMSP="AR";
    private final static String ID_ASIENTO="idasientoBase";
    private final static String NOMAS="AS";

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getString(TOKEN_KEY, "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(TOKEN_KEY, token);
        editor.apply();
    }
    public static void setId(Context context,int id){
        SharedPreferences prefs = context.getSharedPreferences(NOMSP, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ID_CLIENT, id);
        editor.apply();
    }

    public static int getId(Context context){
        SharedPreferences id = context.getSharedPreferences(NOMSP, Context.MODE_PRIVATE);
        return id.getInt(ID_CLIENT, 0);
    }
    public static void setIdAsiento(Context context,int id){
        SharedPreferences prefs = context.getSharedPreferences(NOMAS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ID_ASIENTO, id);
        editor.apply();
    }

    public static int getIdAsiento(Context context){
        SharedPreferences id = context.getSharedPreferences(NOMAS, Context.MODE_PRIVATE);
        return id.getInt(ID_ASIENTO, 0);
    }

}
