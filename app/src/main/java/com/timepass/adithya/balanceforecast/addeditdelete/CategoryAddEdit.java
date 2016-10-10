package com.timepass.adithya.balanceforecast.addeditdelete;

import android.os.Bundle;
import android.view.View;
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

public class CategoryAddEdit extends MainActivity {
    protected String appBarString;
    protected Button addEditButton;
    protected Button deleteButton;
    protected Spinner parentCategorySpinner;
    protected EditText categoryNameEditText;
    protected Category addEditCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        appBarString = new String();
        addEditCategory = getIntent().getExtras().getParcelable("Category");
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
/***************************************************************************************************
 *     SET THE R.ID FIELDS
 **************************************************************************************************/
        categoryNameEditText = (EditText) findViewById(R.id.et_category_category_name);
        parentCategorySpinner = (Spinner) findViewById(R.id.sp_category_parent_category);
        addEditButton = (Button) findViewById(R.id.btn_category_activity_edit);
        deleteButton = (Button) findViewById(R.id.btn_category_activity_delete);
/***************************************************************************************************
 *     SPINNER INITIALIZATION
 **************************************************************************************************/
        DatabaseHelper db = new DatabaseHelper(this);
        ArrayList<Category> parentCategoryArray = new ArrayList<Category>();
        parentCategoryArray.add(new Category(-1,"<No parent>",-1));
        ArrayList<Category> tmpParentCategoryArray = (ArrayList) db.getAllParentCategory();
        parentCategoryArray.addAll(tmpParentCategoryArray);
        ArrayAdapter<Category> parentCategoryAdapter = new ArrayAdapter<Category>(this
                ,android.R.layout.simple_spinner_dropdown_item
                ,parentCategoryArray);
        parentCategorySpinner.setAdapter(parentCategoryAdapter);
/***************************************************************************************************
 *     SET FIELDS ON EDIT MODE AND CLEAR FIELDS ON INSERT MODE
 **************************************************************************************************/
        if(addEditCategory.getId() >= 0){
            categoryNameEditText.setText(String.valueOf(addEditCategory.getCategoryName()));
            if (addEditCategory.getParentCategoryId()>0) {
                int spinnerPosition = parentCategoryAdapter.getPosition(addEditCategory.getParentCategory(this));
                parentCategorySpinner.setSelection(spinnerPosition);
            }
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
                addEditCategory.setCategoryName(categoryNameEditText.getText().toString());
                Category parentCategoryIdObject = (Category) parentCategorySpinner.getSelectedItem();
                addEditCategory.setParentCategoryId(Integer.valueOf(parentCategoryIdObject.getId()));
                boolean tf = addEditCategory.insertUpdateAccountToDB(CategoryAddEdit.this);
                if(tf){
                    CategoryAddEdit.this.finish();
                }
            }
        });
/***************************************************************************************************
 *     SET THE ACTIONS ON THE CLICK OF THE DELETE BUTTON
 **************************************************************************************************/
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean tf = addEditCategory.deleteAccountFromDB(CategoryAddEdit.this);
                if(tf){
                    CategoryAddEdit.this.finish();
                }
            }
        });
    }
}
