package com.timepass.adithya.balanceforecast;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.adapter.PayeeListAdapter;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;

public class PayeeListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
                ,R.layout.listview_payee
                ,getSupportActionBar()
                ,getIntent()
                ,"Payee"
        );
        DatabaseHelper dbhelper = new DatabaseHelper(PayeeListView.this);
        Cursor cur = dbhelper.getCurPayee();
        ListView payeeListView = (ListView) findViewById(R.id.listview_payee1_1);
        PayeeListAdapter payeeListAdapter = new PayeeListAdapter(this,cur);
        payeeListView.setAdapter(payeeListAdapter);
    }
}
