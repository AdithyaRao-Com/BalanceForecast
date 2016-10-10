package com.timepass.adithya.balanceforecast.addeditdelete;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.model.Payee;

public class PayeeAddEdit extends MainActivity {
    protected String appBarString;
    protected Button addEditButton;
    protected Button deleteButton;
    protected EditText payeeNameEditText;
    protected Payee addEditPayee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        addEditPayee = getIntent().getExtras().getParcelable("Payee");
        if(addEditPayee.getId() >= 0){
            appBarString = "Edit Payee";
        }
        else{
            appBarString = "Add Payee";
        }
        new CustomLayoutInflater().inflate(this
                , R.layout.payee_activity
                ,getSupportActionBar()
                ,getIntent()
                ,appBarString
        );
/***************************************************************************************************
 *     SET THE R.ID FIELDS
 **************************************************************************************************/
        payeeNameEditText = (EditText) findViewById(R.id.et_payee_payee_name);
        addEditButton = (Button) findViewById(R.id.btn_payee_activity_edit);
        deleteButton = (Button) findViewById(R.id.btn_payee_activity_delete);
/***************************************************************************************************
 *     SET FIELDS ON EDIT MODE AND CLEAR FIELDS ON INSERT MODE
 **************************************************************************************************/
        if(addEditPayee.getId() >= 0){
            payeeNameEditText.setText(String.valueOf(addEditPayee.getPayeeName()));
            addEditButton.setText("EDIT");
            deleteButton.setEnabled(true);
        } else{
            addEditButton.setText("ADD");
            deleteButton.setEnabled(false);
        }
/***************************************************************************************************
 *     SET THE ACTIONS ON THE CLICK OF THE ADD or EDIT BUTTON
 **************************************************************************************************/
        addEditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEditPayee.setPayeeName(payeeNameEditText.getText().toString());
                boolean tf = addEditPayee.insertUpdateAccountToDB(PayeeAddEdit.this);
                if(tf){
                    PayeeAddEdit.this.finish();
                }
            }
        });
/***************************************************************************************************
 *     SET THE ACTIONS ON THE CLICK OF THE DELETE BUTTON
 **************************************************************************************************/
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tf = addEditPayee.deleteAccountFromDB(PayeeAddEdit.this);
                if(tf){
                    PayeeAddEdit.this.finish();
                }
            }
        });
    }
}
