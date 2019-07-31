package com.woowrale.rxjavaretrofit.data.repository;

public class ApiRestServices {

    public static ApiServices services(){
        return RestClient.getRetrofit().create(ApiServices.class);
    }
}
