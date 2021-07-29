package com.example.quinoidtestapplication.view.listener;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
public interface OnAdapterSelectedListener<T> {

    void onAdapterSelected(T pos);

    void onKeyValue(Object value);
}
