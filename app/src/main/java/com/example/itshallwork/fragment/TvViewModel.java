package com.example.itshallwork.fragment;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.itshallwork.model.tv.ResponseTv;
import com.example.itshallwork.model.tv.TvItem;
import com.example.itshallwork.network.ApiClient;
import com.example.itshallwork.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvViewModel extends ViewModel {
    private static final String API_KEY = "5616d8192ad5a4eda23fc61bc5324daf";
    private MutableLiveData<List<TvItem>> listTvs = new MutableLiveData<>();

    MutableLiveData<List<TvItem>> getListTv() {
        return listTvs;
    }

    void setListTv() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseTv> tvCall = apiInterface.getTvShow(API_KEY);
        tvCall.enqueue(new Callback<ResponseTv>() {
            @Override
            public void onResponse(@NonNull Call<ResponseTv> call, @NonNull Response<ResponseTv> response) {
                if (response.body() != null) {
                    listTvs.postValue(response.body().getResults());
                    Log.d("onResponseTv ", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseTv> call, @NonNull Throwable t) {
                Log.d("onFailureTv ", t.getMessage());
            }
        });
    }
}
