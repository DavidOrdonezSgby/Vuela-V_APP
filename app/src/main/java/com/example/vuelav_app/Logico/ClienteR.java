package com.example.vuelav_app.Logico;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteR {

    public static Retrofit getCliente(String url){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }


}
