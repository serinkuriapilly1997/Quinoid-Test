package com.example.quinoidtestapplication.view.ui.detail;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;

import com.example.quinoidtestapplication.R;
import com.example.quinoidtestapplication.base.BaseActivity;
import com.example.quinoidtestapplication.data.models.ImageUrlsModel;
import com.example.quinoidtestapplication.data.models.UserDetailsModel;
import com.example.quinoidtestapplication.databinding.ActivityDetailBinding;
import com.example.quinoidtestapplication.databinding.ActivityMainBinding;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailActivity extends BaseActivity implements View.OnClickListener {

    ActivityDetailBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(dataBinding.getRoot());
        dataBinding.setListener(this);
        initUi();
    }

    private void initUi() {

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Intent i = getIntent();
        Bundle bundle = i.getParcelableExtra("bundle");
        ImageUrlsModel model = bundle.getParcelable("imageModel");
        String description = bundle.getString("description");
        String imageUrl = "";

        if (model != null) {
            if (model.getFull() != null && !model.getFull().isEmpty()) {
                imageUrl = model.getFull();
            } else if (model.getRegular() != null && !model.getRegular().isEmpty()) {
                imageUrl = model.getRegular();
            } else if (model.getRaw() != null && !model.getRaw().isEmpty()) {
                imageUrl = model.getRaw();
            }

            if (!imageUrl.isEmpty()) {

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_new)
                        .resize(1000, 1000) // resizes the image to these dimensions (in pixel)
                        .into(dataBinding.imageViewDetail);
            }
        }

        if (!description.isEmpty())
            dataBinding.textViewDescription.setText(description);


    }

    @Override
    public void onClick(View view) {
        if (view == dataBinding.imageViewBack) {
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(0, 0);
    }
}