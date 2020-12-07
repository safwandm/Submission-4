package com.example.itshallwork.fragment;

import com.example.itshallwork.model.tv.TvItem;

import java.util.ArrayList;

public interface LoadTvCallback {
    void preExecute();

    void postExecute(ArrayList<TvItem> tvItems);
}
