package com.example.quinoidtestapplication.view.ui.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.quinoidtestapplication.base.BaseActivity;
import com.example.quinoidtestapplication.data.models.UserDetailsModel;
import com.example.quinoidtestapplication.data.remote.bean.HomeBean;
import com.example.quinoidtestapplication.databinding.ActivityMainBinding;
import com.example.quinoidtestapplication.utils.DialogUtils;
import com.example.quinoidtestapplication.view.adapter.RecyclerViewAdapter;
import com.example.quinoidtestapplication.view.listener.OnAdapterSelectedListener;
import com.example.quinoidtestapplication.view.ui.detail.DetailActivity;
import com.example.quinoidtestapplication.view.ui.profile.ProfileActivity;
import com.example.quinoidtestapplication.viewmodel.HomeViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private HomeViewModel homeViewModel;
    private RecyclerViewAdapter adapter;
    ProgressDialog mProgressDialog;
    private List<HomeBean> homePageList = new ArrayList<>();
    OnAdapterSelectedListener<HomeBean> adapterSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        homeViewModel = createViewModel(HomeViewModel.class);
        setContentView(binding.getRoot());
        initUi();
        subscribeHomePage();
        homeViewModel.fetchHomePage();
        showLoading();
        setImageListRecyclerView();

    }


    private void initUi() {

        adapterSelectedListener = new OnAdapterSelectedListener<HomeBean>() {
            @Override
            public void onAdapterSelected(HomeBean pos) {

                Intent i = new Intent(getBaseContext(), DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("imageModel", pos.getUrls());
                bundle.putString("description", pos.getAltDescription());
                i.putExtra("bundle", bundle);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//              overridePendingTransition(0, 0);
                startActivity(i);
            }

            @Override
            public void onKeyValue(Object value) {

                Intent i = new Intent(getBaseContext(), ProfileActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("userModel", (UserDetailsModel) value);
                bundle.putString("image", ((UserDetailsModel) value).getProfileImage().getLarge());
                i.putExtra("bundle", bundle);
                i.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);

            }
        };
    }

    private void setImageListRecyclerView() {

        binding.recyclerviewPhotos.setHasFixedSize(true);
        binding.recyclerviewPhotos.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewAdapter(homePageList, adapterSelectedListener);
        binding.recyclerviewPhotos.setAdapter(adapter);
    }

    private void subscribeHomePage() {

        homeViewModel.getResponseLiveData().observe(this, homeBeans -> {

            if (homeBeans != null && homeBeans.size() > 0) {
                hideLoading();
                homePageList.clear();
                homePageList.addAll(homeBeans);
                adapter.notifyDataSetChanged();
            } else {
                hideLoading();
            }

        });
    }

    public void hideLoading() {

        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public void showLoading() {

        hideLoading();
        mProgressDialog = DialogUtils.showLoadingDialog(this);

    }
}