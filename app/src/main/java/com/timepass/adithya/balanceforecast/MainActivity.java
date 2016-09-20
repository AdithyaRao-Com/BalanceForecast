package com.timepass.adithya.balanceforecast;

import android.app.ProgressDialog;
import android.content.Context;
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
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

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
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Button exportDB =  (Button) findViewById(R.id.content_main_button1);
        exportDB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view)
            {
                try{
                    new ExportDatabaseFileTask().execute("");

                }
                catch(Exception ex){
                    Log.e("Error in MainActivity",ex.toString());
                }
            }
        });
        Button importDB = (Button) findViewById(R.id.content_main_button2);
        importDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    new ImportDatabaseFileTask().execute("");

                }
                catch(Exception ex){
                    Log.e("Error in MainActivity",ex.toString());
                }
            }
        });
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
            // Handle the camera action
        } else if (id == R.id.nav_transactions) {

        } else if (id == R.id.nav_recurring) {

        } else if (id == R.id.nav_category) {

        } else if (id == R.id.nav_payee) {

        } else if (id == R.id.nav_reports) {

        } else if (id == R.id.nav_settings) {

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
            File dbFile = new File(Environment.getDataDirectory() +
                    "/data/com.timepass.adithya/databases/dbPersonalExpense.db");
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
            File dbDir = new File(Environment.getDataDirectory() +
                    "/data/com.timepass.adithya/databases/");
            File importFile = new File(Environment.getExternalStorageDirectory()+"/dbPersonalExpense.db");
            if (!dbDir.exists())
            {
                dbDir.mkdirs();
            }
            File file = new File(dbDir, "dbPersonalExpense.db");
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
