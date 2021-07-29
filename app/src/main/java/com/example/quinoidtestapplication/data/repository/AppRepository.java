package com.example.quinoidtestapplication.data.repository;

import com.example.quinoidtestapplication.data.remote.ApiService;
import com.example.quinoidtestapplication.data.remote.bean.HomeBean;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
@Singleton
public class AppRepository {

    private ApiService apiService;

    @Inject
    public AppRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    public Observable<List<HomeBean>> getHomePage() {
        return apiService.loadHomePage("8634366274bd23efb9b023fb9b2c6502e67f7dd5d6a7962b3b49fbee170940f8");
    }
}
