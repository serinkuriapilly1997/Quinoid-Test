package com.example.quinoidtestapplication.data.remote;

import com.example.quinoidtestapplication.data.remote.bean.HomeBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
public interface ApiService {

    @GET(ServiceNames.GET_HOME)
    Observable<List<HomeBean>> loadHomePage(@Query("client_id") String clintId);

}
