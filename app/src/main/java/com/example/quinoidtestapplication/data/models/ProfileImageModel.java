package com.example.quinoidtestapplication.data.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by SERIN_SEB on 27-07-2021.
 */
public class ProfileImageModel implements Parcelable {
    @SerializedName("small")
    @Expose
    private String small;
    @SerializedName("medium")
    @Expose
    private String medium;
    @SerializedName("large")
    @Expose
    private String large;

    protected ProfileImageModel(Parcel in) {
        small = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public static final Creator<ProfileImageModel> CREATOR = new Creator<ProfileImageModel>() {
        @Override
        public ProfileImageModel createFromParcel(Parcel in) {
            return new ProfileImageModel(in);
        }

        @Override
        public ProfileImageModel[] newArray(int size) {
            return new ProfileImageModel[size];
        }
    };

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(small);
        parcel.writeString(medium);
        parcel.writeString(large);
    }
}
