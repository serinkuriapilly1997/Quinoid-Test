package com.example.quinoidtestapplication.data.remote.bean;

import com.example.quinoidtestapplication.data.models.ImageUrlsModel;
import com.example.quinoidtestapplication.data.models.UserDetailsModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
public class HomeBean {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("alt_description")
    @Expose
    private String altDescription;
    @SerializedName("urls")
    @Expose
    private ImageUrlsModel urls;
    @SerializedName("user")
    @Expose
    private UserDetailsModel user;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getAltDescription() {
        return altDescription;
    }

    public void setAltDescription(String altDescription) {
        this.altDescription = altDescription;
    }

    public ImageUrlsModel getUrls() {
        return urls;
    }

    public void setUrls(ImageUrlsModel urls) {
        this.urls = urls;
    }

    public UserDetailsModel getUser() {
        return user;
    }

    public void setUser(UserDetailsModel user) {
        this.user = user;
    }

}
