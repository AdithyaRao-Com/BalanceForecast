package com.timepass.adithya.balanceforecast;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.adapter.CategoryListAdapter;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.model.CustomLayoutInflater;

public class CategoryListView extends MainActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
                ,R.layout.listview_category
                ,getSupportActionBar()
                ,getIntent()
        );
        DatabaseHelper dbhelper = new DatabaseHelper(CategoryListView.this);
        Cursor cur = dbhelper.getCurCategory();
        ListView categoryListView = (ListView) findViewById(R.id.listview_category_1);
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(this,cur);
        categoryListView.setAdapter(categoryListAdapter);
    }
}
