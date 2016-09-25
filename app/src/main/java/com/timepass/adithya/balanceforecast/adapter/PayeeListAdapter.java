package com.timepass.adithya.balanceforecast.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;

/**
 * Created by Adithya Rao on 9/24/16.
 */
public class PayeeListAdapter extends CursorAdapter{
    private Cursor mCursor;
    private final LayoutInflater mLayoutInflator;
    public PayeeListAdapter(Context context, Cursor c){
        super(context,c);
        mLayoutInflator = LayoutInflater.from(context);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor){
        TextView payeeName = (TextView) view.findViewById(R.id.listview_payee_row_payee_name);
        payeeName.setText(cursor.getString(cursor.getColumnIndex(DatabaseHelper.FIELD_payee_payee_name)));
    }
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent){
        final View view = mLayoutInflator.inflate(R.layout.listview_payee_row,parent,false);
        return view;
    }
}
