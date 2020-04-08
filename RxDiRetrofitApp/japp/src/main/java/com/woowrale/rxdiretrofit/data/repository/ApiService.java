package com.woowrale.rxdiretrofit.data.repository;

public class ApiService {

    public static ApiServices services(){
        return RestClient.getRetrofit().create(ApiServices.class);
    }
}
