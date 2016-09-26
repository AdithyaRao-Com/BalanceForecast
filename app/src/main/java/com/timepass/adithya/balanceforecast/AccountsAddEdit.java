package com.timepass.adithya.balanceforecast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.model.Accounts;

public class AccountsAddEdit extends MainActivity {
    protected String appBarString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        Accounts addEditAccount = getIntent().getExtras().getParcelable("Accounts");
        if(addEditAccount.getId() >= 0){
            appBarString = "Edit Account";
        }
        else{
            appBarString = "Add Account";
        }
        new CustomLayoutInflater().inflate(this
                ,R.layout.accounts_activity
                ,getSupportActionBar()
                ,getIntent()
                ,appBarString
        );
    }
}
