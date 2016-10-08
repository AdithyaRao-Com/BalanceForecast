package com.timepass.adithya.balanceforecast.addeditdelete;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.timepass.adithya.balanceforecast.MainActivity;
import com.timepass.adithya.balanceforecast.R;
import com.timepass.adithya.balanceforecast.helper.CustomLayoutInflater;
import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;
import com.timepass.adithya.balanceforecast.model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryAddEdit extends MainActivity {
    protected String appBarString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        Category addEditCategory = getIntent().getExtras().getParcelable("Category");
        if(addEditCategory.getId() >= 0){
            appBarString = "Edit Category";
        }
        else{
            appBarString = "Add Category";
        }
        new CustomLayoutInflater().inflate(this
                , R.layout.category_activity
                ,getSupportActionBar()
                ,getIntent()
                ,appBarString
        );
        Spinner spinnerParentCategory = (Spinner) findViewById(R.id.sp_category_parent_category);
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Category> parentCategoryArray = new ArrayList<Category>();
        parentCategoryArray.add(new Category(-1,"<No parent>",-1));
        ArrayList<Category> tmpParentCategoryArray = (ArrayList) db.getAllParentCategory();
        parentCategoryArray.addAll(tmpParentCategoryArray);
        ArrayAdapter<Category> parentCategoryAdapter = new ArrayAdapter<Category>(this
                ,android.R.layout.simple_spinner_dropdown_item
                ,parentCategoryArray);
        spinnerParentCategory.setAdapter(parentCategoryAdapter);
        if(addEditCategory.getId() >= 0){
            EditText categoryNameEditText = (EditText) findViewById(R.id.et_category_category_name);
            categoryNameEditText.setText(String.valueOf(addEditCategory.getCategoryName()));
            if (addEditCategory.getParentCategoryId()>0) {
                int spinnerPosition = parentCategoryAdapter.getPosition(addEditCategory.getParentCategory(this));
                spinnerParentCategory.setSelection(spinnerPosition);
            }
            Button addEditButton = (Button) findViewById(R.id.btn_category_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_category_activity_delete);
            addEditButton.setText("EDIT");
            deleteButton.setEnabled(true);
        } else{
            Button addEditButton = (Button) findViewById(R.id.btn_category_activity_edit);
            Button deleteButton = (Button) findViewById(R.id.btn_category_activity_delete);
            addEditButton.setText("ADD");
            deleteButton.setEnabled(false);
        }
    }
}
