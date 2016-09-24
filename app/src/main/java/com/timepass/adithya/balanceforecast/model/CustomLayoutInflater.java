package com.timepass.adithya.balanceforecast.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.support.v7.app.ActionBar;
import android.widget.Toast;

import com.timepass.adithya.balanceforecast.R;

/**
 * Created by Adithya Rao on 9/24/16.
 */
public class CustomLayoutInflater {
    public void inflate(Activity activity
            , int LayoutResource
            , ActionBar getSupportActionBar
            , Intent getIntent){
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) activity.findViewById(R.id.app_bar_main_coordinator_layout);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(LayoutResource,null,false);
        int i = 1;
        coordinatorLayout.removeViewAt(i);
        coordinatorLayout.addView(contentView,i);
        contentView = coordinatorLayout.getChildAt(i);
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) contentView.getLayoutParams();
        params.setBehavior(new AppBarLayout.ScrollingViewBehavior());
        getSupportActionBar.setTitle("Accounts");
    }
}
