package com.timepass.adithya.balanceforecast.addeditdelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.R;
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
                , R.layout.accounts_activity
                ,getSupportActionBar()
                ,getIntent()
                ,appBarString
        );
        if(addEditAccount.getId() >= 0){
            Spinner accountTypeSpinner = (Spinner) findViewById(R.id.sp_accounts_account_type);
            Spinner currencySpinner = (Spinner) findViewById(R.id.sp_accounts_currency);
            EditText accountNameEditText = (EditText) findViewById(R.id.et_accounts_account_name);
            EditText accountBalanceEditText = (EditText) findViewById(R.id.et_accounts_account_balance);
            ArrayAdapter<CharSequence> accountAdapter = (ArrayAdapter) accountTypeSpinner.getAdapter();
            ArrayAdapter<CharSequence> currencyAdapter = (ArrayAdapter) currencySpinner.getAdapter();
            if (!addEditAccount.getCurrency().equals(null)) {
                int spinnerPosition = currencyAdapter.getPosition(addEditAccount.getCurrency());
                currencySpinner.setSelection(spinnerPosition);
            }
            if (!addEditAccount.getAccountType().equals(null)) {
                int spinnerPosition = accountAdapter.getPosition(addEditAccount.getAccountType());
                accountTypeSpinner.setSelection(spinnerPosition);
            }
            accountNameEditText.setText(addEditAccount.getAccountName());
            accountBalanceEditText.setText(String.valueOf(addEditAccount.getAccountBalance()));
            Button addEditButton = (Button) findViewById(R.id.btn_accounts_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_accounts_activity_delete);
            addEditButton.setText("EDIT");
            deleteButton.setEnabled(true);
        } else{
            Button addEditButton = (Button) findViewById(R.id.btn_accounts_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_accounts_activity_delete);
            addEditButton.setText("ADD");
            deleteButton.setEnabled(false);
        }
    }
}
