package com.woowrale.rxjavaretrofit.data.repository;

public class ApiService {

    public static ApiServices services(){
        return RestClient.getRetrofit().create(ApiServices.class);
    }
}
