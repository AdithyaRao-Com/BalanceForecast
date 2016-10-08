package com.timepass.adithya.balanceforecast.listview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.addeditdelete.PayeeAddEdit;
import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.adapter.PayeeListAdapter;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.model.Payee;

public class PayeeListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
                , R.layout.listview_payee
                ,getSupportActionBar()
                ,getIntent()
                ,"Payee"
        );
        DatabaseHelper dbhelper = new DatabaseHelper(PayeeListView.this);
        Cursor cur = dbhelper.getCurPayee();
        ListView payeeListView = (ListView) findViewById(R.id.listview_payee1_1);
        PayeeListAdapter payeeListAdapter = new PayeeListAdapter(this,cur);
        payeeListView.setAdapter(payeeListAdapter);
        payeeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                Cursor cursor = ((PayeeListAdapter)parent.getAdapter()).getCursor();
                cursor.moveToPosition(position);
                Payee editPayee = new Payee();
                editPayee.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                editPayee.setPayeeName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_payee_payee_name)));
                Intent intent = new Intent(PayeeListView.this, PayeeAddEdit.class);
                intent.putExtra("Payee", editPayee);
                startActivity(intent);
            }
        });
    }
}
