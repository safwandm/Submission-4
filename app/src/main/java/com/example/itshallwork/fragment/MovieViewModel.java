package com.example.itshallwork.fragment;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.itshallwork.model.movie.MovieItem;
import com.example.itshallwork.model.movie.ResponseMovie;
import com.example.itshallwork.network.ApiClient;
import com.example.itshallwork.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {
    private static final String API_KEY = "5616d8192ad5a4eda23fc61bc5324daf";
    private MutableLiveData<List<MovieItem>> listMovies = new MutableLiveData<>();

    MutableLiveData<List<MovieItem>> getListMovies() {
        return listMovies;
    }

    void setListMovies() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseMovie> movieCall = apiInterface.getMovies(API_KEY);
        movieCall.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(@NonNull Call<ResponseMovie> call, @NonNull Response<ResponseMovie> response) {
                if (response.body() != null) {
                    listMovies.postValue(response.body().getResults());
                    Log.d("onResponseMovie ", response.body().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseMovie> call, @NonNull Throwable t) {
                Log.d("onFailureMovie ", t.getMessage());
            }
        });
    }
}
