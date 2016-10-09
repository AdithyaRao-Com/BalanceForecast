package com.timepass.adithya.balanceforecast.addeditdelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
    protected Button addEditButton;
    protected Button deleteButton;
    protected Spinner accountTypeSpinner;
    protected Spinner currencySpinner;
    protected EditText accountBalanceEditText;
    protected EditText accountNameEditText;
    protected Accounts addEditAccount;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        addEditAccount = getIntent().getExtras().getParcelable("Accounts");
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
            accountTypeSpinner = (Spinner) findViewById(R.id.sp_accounts_account_type);
            currencySpinner = (Spinner) findViewById(R.id.sp_accounts_currency);
            accountNameEditText = (EditText) findViewById(R.id.et_accounts_account_name);
            accountBalanceEditText = (EditText) findViewById(R.id.et_accounts_account_balance);
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
            addEditButton = (Button)findViewById(R.id.btn_accounts_activity_edit);
            deleteButton = (Button)findViewById(R.id.btn_accounts_activity_delete);
            addEditButton.setText("EDIT");
            deleteButton.setEnabled(true);
        } else{
            addEditButton = (Button) findViewById(R.id.btn_accounts_activity_edit);
            deleteButton = (Button) findViewById(R.id.btn_accounts_activity_delete);
            addEditButton.setText("ADD");
            deleteButton.setEnabled(false);
        }
        addEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditAccount.setAccountName(accountNameEditText.getText().toString());
                addEditAccount.setAccountBalance(Double.valueOf(accountBalanceEditText.getText().toString()));
                addEditAccount.setAccountType(accountTypeSpinner.getSelectedItem().toString());
                addEditAccount.setCurrency(currencySpinner.getSelectedItem().toString());
                boolean tf = addEditAccount.insertUpdateAccountToDB(AccountsAddEdit.this);
                if(tf){
                    AccountsAddEdit.this.finish();
                }
            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tf = addEditAccount.deleteAccountFromDB(AccountsAddEdit.this);
                if(tf){
                    AccountsAddEdit.this.finish();
                }
            }
        });
    }
}
