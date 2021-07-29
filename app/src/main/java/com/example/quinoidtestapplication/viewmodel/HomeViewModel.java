package com.example.quinoidtestapplication.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.quinoidtestapplication.data.remote.bean.HomeBean;
import com.example.quinoidtestapplication.data.repository.AppRepository;
import com.example.quinoidtestapplication.utils.InternetUtils;
import com.example.quinoidtestapplication.utils.ToastUtils;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private AppRepository appRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<HomeBean>> responseLiveData = new MutableLiveData<>();

    @Inject
    InternetUtils internetUtils;

    @Inject
    ToastUtils toastUtils;

    @Inject
    public HomeViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    public MutableLiveData<List<HomeBean>> getResponseLiveData() {

        return responseLiveData;
    }

    public void fetchHomePage() {

        if (internetUtils.isNetworkAvailable()) {

            disposable.add(appRepository.getHomePage()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(this::onGetHomeSuccess, this::onGetHomeFail));
        } else {
            toastUtils.showToast("Please connect to internet");
        }

    }

    private void onGetHomeSuccess(List<HomeBean> homePageList) {

        responseLiveData.setValue(homePageList);
    }

    private void onGetHomeFail(Throwable throwable) {

        toastUtils.showToast("No response from server");
        responseLiveData.setValue(null);
    }


//    private String handleError(Throwable e) {
//
//        HomeBean errorBean = null;
//        String errorMessage = e != null ? e.getMessage() : "";
//        if (e instanceof HttpException) {
//            try {
//                ResponseBody responseBody = ((HttpException) e).response().errorBody();
//                errorBean = new Gson().fromJson(responseBody != null ? responseBody.string() : null, HomeBean.class);
//
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
//
//        return errorMessage;
//    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }
}
