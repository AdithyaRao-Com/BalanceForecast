package com.timepass.adithya.balanceforecast;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.adapter.AccountsListAdapter;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;

public class AccountsListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
            ,R.layout.listview_accounts
            ,getSupportActionBar()
            ,getIntent()
            ,"Accounts"
        );
        DatabaseHelper dbhelper = new DatabaseHelper(AccountsListView.this);
        Cursor cur = dbhelper.getCurAccounts();
        ListView accountListView = (ListView) findViewById(R.id.listview_accounts_1);
        AccountsListAdapter accountsListAdapter = new AccountsListAdapter(this,cur);
        accountListView.setAdapter(accountsListAdapter);
    }
}
