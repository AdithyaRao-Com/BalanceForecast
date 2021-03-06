package com.timepass.adithya.balanceforecast.listview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.adapter.CategoryListAdapter;
import com.timepass.adithya.balanceforecast.addeditdelete.CategoryAddEdit;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.model.Category;

public class CategoryListView extends MainActivity {
    private ListView categoryListView;
    private CategoryListAdapter categoryListAdapter;
    private DatabaseHelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new CustomLayoutInflater().inflate(this
                , R.layout.listview_category
                ,getSupportActionBar()
                ,getIntent()
                ,"Category"
        );
        categoryListView = (ListView) findViewById(R.id.listview_category_1);
        categoryListAdapter = new CategoryListAdapter(this,this.getData());
        categoryListView.setAdapter(categoryListAdapter);
        categoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Cursor cursor = (Cursor) parent.getAdapter().getItem(position);
                Cursor cursor = ((CategoryListAdapter) parent.getAdapter()).getCursor();
                cursor.moveToPosition(position);
                Category editCategory = new Category();
                editCategory.setId(cursor.getInt(cursor.getColumnIndex("_id")));
                editCategory.setCategoryName(cursor.getString(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_category_category_name)));
                editCategory.setParentCategoryId(cursor.getInt(cursor.getColumnIndex(
                        DatabaseHelper.FIELD_category_parent_category_id)));
                Intent intent = new Intent(CategoryListView.this, CategoryAddEdit.class);
                intent.putExtra("Category", editCategory);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onFABPressed(View view){
        Category addCategory = new Category();
        addCategory.setId(-1);
        Intent intent = new Intent(CategoryListView.this, CategoryAddEdit.class);
        intent.putExtra("Category", addCategory);
        startActivity(intent);
    }
    @Override
    protected void onPause() {
        super.onPause();
        categoryListAdapter.notifyDataSetInvalidated();
        categoryListAdapter.changeCursor(null);
    }

    @Override
    protected void onResume(){
        super.onResume();
        Cursor cur = this.getData();
        categoryListAdapter.changeCursor(cur);
    }

    protected Cursor getData(){
        DatabaseHelper db = new DatabaseHelper(CategoryListView.this);
        dbhelper = db;
        Cursor cur = dbhelper.getCurCategory();
        return cur;
    }
}
