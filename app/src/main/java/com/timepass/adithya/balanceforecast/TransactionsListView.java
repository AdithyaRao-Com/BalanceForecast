package com.timepass.adithya.balanceforecast;

import android.os.Bundle;

import com.timepass.adithya.balanceforecast.model.CustomLayoutInflater;

public class TransactionsListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
                ,R.layout.listview_transaction
                ,getSupportActionBar()
                ,getIntent()
        );
    }
}
