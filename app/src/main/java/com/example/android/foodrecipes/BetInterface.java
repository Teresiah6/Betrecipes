package com.example.android.foodrecipes;

import android.provider.ContactsContract;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BetInterface {
    @GET ("v3/sports")
    Call<Data> getData(@Query("apiKey") String api);

}
