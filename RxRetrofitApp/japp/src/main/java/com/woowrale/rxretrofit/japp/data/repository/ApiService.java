package com.woowrale.rxretrofit.japp.data.repository;

public class ApiService {

    public static ApiServices services(){
        return RestClient.getRetrofit().create(ApiServices.class);
    }
}
