package com.timepass.adithya.balanceforecast;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.timepass.adithya.balanceforecast.listview.AccountsListView;
import com.timepass.adithya.balanceforecast.listview.CategoryListView;
import com.timepass.adithya.balanceforecast.listview.PayeeListView;
import com.timepass.adithya.balanceforecast.listview.RecurringListView;
import com.timepass.adithya.balanceforecast.listview.TransactionsListView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onFABPressed(view);
            }
        });
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected void onFABPressed(View view){
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_accounts) {
            Intent intent = new Intent(MainActivity.this,AccountsListView.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_transactions) {
            Intent intent = new Intent(MainActivity.this,TransactionsListView.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.nav_recurring) {
            Intent intent = new Intent(MainActivity.this,RecurringListView.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_category) {
            Intent intent = new Intent(MainActivity.this,CategoryListView.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_payee) {
            Intent intent = new Intent(MainActivity.this,PayeeListView.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.nav_reports) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_export) {
            try{
                new ExportDatabaseFileTask().execute("");

            }
            catch(Exception ex){
                Log.e("Error in MainActivity",ex.toString());
            }

        } else if (id == R.id.nav_import) {
            try{
                new ImportDatabaseFileTask().execute("");

            }
            catch(Exception ex){
                Log.e("Error in MainActivity",ex.toString());
            }
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    /*******************************************************************************
     ********************************************************************************
     Export Database into file Class
     ********************************************************************************
     *******************************************************************************/
    Context ctx = MainActivity.this;
    class ExportDatabaseFileTask extends AsyncTask<String, Void, Boolean>
    {
        private final ProgressDialog dialog = new ProgressDialog(ctx);
        private final String DATABASE_NAME = new String("dbPersonalExpense.db");
        // can use UI thread here
        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Exporting database...");
            this.dialog.show();
        }
        // automatically done on worker thread (separate from UI thread)
        protected Boolean doInBackground(final String... args)
        {
            File dbFile = new File(ctx.getApplicationInfo().dataDir+"/databases/"+DATABASE_NAME);
            File exportDir = new File(Environment.getExternalStorageDirectory(), "");
            if (!exportDir.exists())
            {
                exportDir.mkdirs();
            }
            File file = new File(exportDir, dbFile.getName());
            try
            {
                file.createNewFile();
                this.copyFile(dbFile, file);
                return true;
            }
            catch (IOException e)
            {
                Log.e("mypck", e.getMessage(), e);
                return false;
            }
        }
        // can use UI thread here
        @Override
        protected void onPostExecute(final Boolean success)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            if (success)
            {
                Toast.makeText(ctx, "Export successful!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(ctx, "Export failed", Toast.LENGTH_SHORT).show();
            }
        }
        void copyFile(File src, File dst) throws IOException
        {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            try
            {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            }
            finally
            {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            }
        }
    }
    /*******************************************************************************
     ********************************************************************************
     Import Database into file Class
     ********************************************************************************
     *******************************************************************************/
    class ImportDatabaseFileTask extends AsyncTask<String, Void, Boolean>
    {
        private final ProgressDialog dialog = new ProgressDialog(ctx);
        private final String DATABASE_NAME = new String("dbPersonalExpense.db");
        // can use UI thread here
        @Override
        protected void onPreExecute()
        {
            this.dialog.setMessage("Importing database...");
            this.dialog.show();
        }
        // automatically done on worker thread (separate from UI thread)
        protected Boolean doInBackground(final String... args)
        {
            File dbDir = new File(ctx.getApplicationInfo().dataDir+"/databases/");
            File importFile = new File(Environment.getExternalStorageDirectory()+"/"+DATABASE_NAME);
            File file = new File(dbDir, DATABASE_NAME);
            try
            {
                file.createNewFile();
                this.copyFile(importFile, file);
                return true;
            }
            catch (IOException e)
            {
                Log.e("mypck", e.getMessage(), e);
                return false;
            }
        }
        // can use UI thread here
        @Override
        protected void onPostExecute(final Boolean success)
        {
            if (this.dialog.isShowing())
            {
                this.dialog.dismiss();
            }
            if (success)
            {
                Toast.makeText(ctx, "Import successful!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(ctx, "Import failed", Toast.LENGTH_SHORT).show();
            }
        }
        void copyFile(File src, File dst) throws IOException
        {
            FileChannel inChannel = new FileInputStream(src).getChannel();
            FileChannel outChannel = new FileOutputStream(dst).getChannel();
            try
            {
                inChannel.transferTo(0, inChannel.size(), outChannel);
            }
            finally
            {
                if (inChannel != null)
                    inChannel.close();
                if (outChannel != null)
                    outChannel.close();
            }
        }
    }
}
