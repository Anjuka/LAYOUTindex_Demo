package com.anjukakoralage.layoutindexdemo.data;

import com.anjukakoralage.layoutindexdemo.model.UserDetails;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by anjukakoralage on 27,September,2019
 */
public interface UserServices {

    @GET("users?page=2")
    Observable<List<UserDetails>> getAllDetail();
}
