package com.example.quinoidtestapplication.view.ui.profile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.quinoidtestapplication.R;
import com.example.quinoidtestapplication.base.BaseActivity;
import com.example.quinoidtestapplication.data.models.UserDetailsModel;
import com.example.quinoidtestapplication.databinding.ActivityProfileBinding;
import com.squareup.picasso.Picasso;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfileActivity extends BaseActivity implements View.OnClickListener {

    ActivityProfileBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(dataBinding.getRoot());
        dataBinding.setListener(this);
        initUi();
    }

    private void initUi() {

        Intent i = getIntent();
        Bundle bundle = i.getParcelableExtra("bundle");
        UserDetailsModel model = bundle.getParcelable("userModel");
        String imageUrl = bundle.getString("image");

        if (model != null) {

            if (!model.getName().isEmpty())
                dataBinding.textViewName.setText(model.getName());

            if (!imageUrl.isEmpty()) {

                Picasso.get()
                        .load(imageUrl)
                        .placeholder(R.drawable.placeholder_new)
                        .error(R.drawable.placeholder_new)
                        .into(dataBinding.imageViewProfile);
            }
            if (model.getLocation() != null && !model.getLocation().isEmpty())
                dataBinding.textViewLocation.setText(model.getLocation());

            if (model.getBio() != null && !model.getBio().isEmpty())
                dataBinding.textViewBio.setText(model.getBio());
        }
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