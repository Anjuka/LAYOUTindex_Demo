package com.anjukakoralage.layoutindexdemo.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by anjukakoralage on 27,September,2019
 */
public class UserAllDetails {

    @SerializedName("page")
    private String page;

    @SerializedName("per_page")
    private String per_page;

    @SerializedName("total")
    private String total;

    @SerializedName("total_pages")
    private String total_pages;

    @SerializedName("data")
    private ArrayList<UserDetails> data;

    @Inject
    public UserAllDetails(String page, String per_page, String total, String total_pages, ArrayList<UserDetails> data) {

        this.page = page;
        this.per_page = per_page;
        this.total = total;
        this.total_pages = total_pages;
        this.data = data;
    }

    public String getPage() {
        return page;
    }

    public String getPer_page() {
        return per_page;
    }

    public String getTotal() {
        return total;
    }

    public String getTotal_pages() {
        return total_pages;
    }

    public ArrayList<UserDetails> getData() {
        return data;
    }
}