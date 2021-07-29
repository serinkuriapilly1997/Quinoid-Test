package com.example.quinoidtestapplication.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quinoidtestapplication.R;
import com.example.quinoidtestapplication.data.remote.bean.HomeBean;
import com.example.quinoidtestapplication.databinding.PhotoListItemBinding;
import com.example.quinoidtestapplication.view.listener.OnAdapterSelectedListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by SERIN_SEB on 28-07-2021.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<HomeBean> homePageList;
    OnAdapterSelectedListener<HomeBean> adapterSelectedListener;

    public RecyclerViewAdapter(List<HomeBean> homePageList, OnAdapterSelectedListener<HomeBean> adapterSelectedListener) {
        this.homePageList = homePageList;
        this.adapterSelectedListener = adapterSelectedListener;
    }

    @NonNull
    @NotNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        PhotoListItemBinding dataBinding = DataBindingUtil
                .inflate(layoutInflater, R.layout.photo_list_item, parent, false);

        return new ViewHolder(dataBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerViewAdapter.ViewHolder holder, int position) {

        holder.binding.setListener(adapterSelectedListener);
        if (homePageList.get(position) != null) {

            if (homePageList.get(position).getUrls() != null && !homePageList.get(position).getUrls().getRegular().isEmpty()) {

                Picasso.get()
                        .load(homePageList.get(position).getUrls().getRegular())
                        .placeholder(R.drawable.placeholder_new)
                        .resize(600, 600)
                        .into(holder.binding.imageView);
            }


            if (homePageList.get(position).getAltDescription() != null && !homePageList.get(position).getAltDescription().isEmpty()) {
                holder.binding.setItem(homePageList.get(position));
            }

            if (homePageList.get(position).getUser() != null && !homePageList.get(position).getUser().getName().isEmpty()) {

                holder.binding.setUser(homePageList.get(position).getUser());
            }

            if (homePageList.get(position).getUser() != null && homePageList.get(position).getUser().getProfileImage() != null &&
                    !homePageList.get(position).getUser().getProfileImage().getSmall().isEmpty()) {

                Picasso.get()
                        .load(homePageList.get(position).getUser().getProfileImage().getMedium())
                        .placeholder(R.drawable.profile_placeholder)
                        .into(holder.binding.roundedImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return homePageList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        PhotoListItemBinding binding;

        public ViewHolder(PhotoListItemBinding dataBinding) {
            super(dataBinding.getRoot());
            binding = dataBinding;
        }
    }
}
