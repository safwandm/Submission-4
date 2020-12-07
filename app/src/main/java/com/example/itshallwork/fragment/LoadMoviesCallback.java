package com.example.itshallwork.fragment;

import com.example.itshallwork.model.movie.MovieItem;

import java.util.ArrayList;

public interface LoadMoviesCallback {
    void preExecute();

    void postExecute(ArrayList<MovieItem> movieItems);
}
