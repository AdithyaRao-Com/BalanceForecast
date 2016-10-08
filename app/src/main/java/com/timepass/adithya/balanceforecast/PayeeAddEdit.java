package com.timepass.adithya.balanceforecast;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.model.Payee;

public class PayeeAddEdit extends MainActivity {
    protected String appBarString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        Payee addEditPayee = getIntent().getExtras().getParcelable("Payee");
        if(addEditPayee.getId() >= 0){
            appBarString = "Edit Payee";
        }
        else{
            appBarString = "Add Payee";
        }
        new CustomLayoutInflater().inflate(this
                ,R.layout.payee_activity
                ,getSupportActionBar()
                ,getIntent()
                ,appBarString
        );
        if(addEditPayee.getId() >= 0){
            EditText payeeNameEditText = (EditText) findViewById(R.id.et_payee_payee_name);
            payeeNameEditText.setText(String.valueOf(addEditPayee.getPayeeName()));
            Button addEditButton = (Button) findViewById(R.id.btn_payee_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_payee_activity_delete);
            addEditButton.setText("EDIT");
            deleteButton.setEnabled(true);
        } else{
            Button addEditButton = (Button) findViewById(R.id.btn_payee_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_payee_activity_delete);
            addEditButton.setText("ADD");
            deleteButton.setEnabled(false);
        }
    }
}
