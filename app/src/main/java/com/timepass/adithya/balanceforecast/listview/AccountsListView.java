package com.timepass.adithya.balanceforecast.listview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.addeditdelete.AccountsAddEdit;
import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.adapter.AccountsListAdapter;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.model.Accounts;

public class AccountsListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
            , R.layout.listview_accounts
            ,getSupportActionBar()
            ,getIntent()
            ,"Accounts"
        );
        DatabaseHelper dbhelper = new DatabaseHelper(AccountsListView.this);
        Cursor cur = dbhelper.getCurAccounts();
        ListView accountListView = (ListView) findViewById(R.id.listview_accounts_1);
        AccountsListAdapter accountsListAdapter = new AccountsListAdapter(this,cur);
        accountListView.setAdapter(accountsListAdapter);
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = ((AccountsListAdapter)parent.getAdapter()).getCursor();
                cursor.moveToPosition(position);
                Accounts editAccount = new Accounts();
                editAccount.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                editAccount.setAccountName(cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_accounts_account_name)));
                editAccount.setAccountType(cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_accounts_account_type)));
                editAccount.setCurrency(cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_accounts_currency)));
                editAccount.setAccountBalance(cursor.getDouble(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_accounts_account_balance)));
                Intent intent = new Intent(AccountsListView.this, AccountsAddEdit.class);
                intent.putExtra("Accounts", editAccount);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onFABPressed(View view){
        Accounts addAccount = new Accounts();
        addAccount.setId(-1);
        Intent intent = new Intent(AccountsListView.this, AccountsAddEdit.class);
        intent.putExtra("Accounts", addAccount);
        startActivity(intent);
    }
}
