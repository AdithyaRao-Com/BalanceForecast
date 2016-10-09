package com.timepass.adithya.balanceforecast.helper;

/**
 * Created by Adithya Rao on 9/18/16.
 */
import com.timepass.adithya.balanceforecast.model.Accounts;
import com.timepass.adithya.balanceforecast.model.Transactions;
import com.timepass.adithya.balanceforecast.model.Category;
import com.timepass.adithya.balanceforecast.model.Payee;
import com.timepass.adithya.balanceforecast.model.Transfers;
import com.timepass.adithya.balanceforecast.model.Recurring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
//import java.util.Date;
//import java.util.Locale;
//import java.sql.*;
//import java.text.SimpleDateFormat;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;


public class DatabaseHelper extends SQLiteOpenHelper {

    Context context;

    //Log file
    public static final String LOG = "dbPersonalExpense.log";

    //Database
    private static final String DATABASE_NAME = "dbPersonalExpense.db";
    private static final int DATABASE_VERSION = 1;

    //------------------------------------
    // TABLES
    //------------------------------------

    // Accounts - Table name
    public static final String TABLE_accounts = "accounts";

    // Accounts - Column names
    public static final String FIELD_accounts_id = "id";
    public static final String FIELD_accounts_account_name = "account_name";
    public static final String FIELD_accounts_account_type = "account_type";
    public static final String FIELD_accounts_currency = "currency";
    public static final String FIELD_accounts_creation_date = "creation_date";
    public static final String FIELD_accounts_last_update_date = "last_update_date";
    public static final String FIELD_accounts_account_balance = "account_balance";

    // Accounts - Table create statement
    private static final String CREATE_TABLE_accounts = "CREATE TABLE " + TABLE_accounts +
            "(" +
            FIELD_accounts_id + " INTEGER PRIMARY KEY, " +
            FIELD_accounts_account_name + " TEXT(50) NOT NULL, " +
            FIELD_accounts_account_type + " TEXT(10) NOT NULL, " +
            FIELD_accounts_currency + " TEXT(10) NOT NULL, " +
            FIELD_accounts_creation_date + " REAL(10,0) NOT NULL, " +
            FIELD_accounts_last_update_date + " REAL(10,0) NOT NULL, " +
            FIELD_accounts_account_balance + " REAL(16,2) NOT NULL " +
            ")";



    // Transactions - Table name
    public static final String TABLE_transactions = "transactions";

    // Transactions - Column names
    public static final String FIELD_transactions_id = "id";
    public static final String FIELD_transactions_account_id = "account_id";
    public static final String FIELD_transactions_transaction_type = "transaction_type";
    public static final String FIELD_transactions_category_id = "category_id";
    public static final String FIELD_transactions_payee_id = "payee_id";
    public static final String FIELD_transactions_transaction_date = "transaction_date";
    public static final String FIELD_transactions_notes = "notes";
    public static final String FIELD_transactions_creation_date = "creation_date";
    public static final String FIELD_transactions_last_update_date = "last_update_date";
    public static final String FIELD_transactions_amount = "amount";
    public static final String FIELD_transactions_recurring_id = "recurring_id";
    public static final String FIELD_transactions_to_account_id = "to_account_id";
    public static final String FIELD_transactions_is_transfer_flag = "is_transfer_flag";

    // Transactions - Table create statement
    private static final String CREATE_TABLE_transactions = "CREATE TABLE " + TABLE_transactions +
            "(" +
            FIELD_transactions_id + " INTEGER PRIMARY KEY, " +
            FIELD_transactions_account_id + " REAL(10,0) NOT NULL, " +
            FIELD_transactions_transaction_type + " TEXT(10) NOT NULL, " +
            FIELD_transactions_category_id + " REAL(10,0) NOT NULL, " +
            FIELD_transactions_payee_id + " REAL(10,0) NOT NULL, " +
            FIELD_transactions_transaction_date + " REAL(10,0) NOT NULL, " +
            FIELD_transactions_notes + " TEXT(50), " +
            FIELD_transactions_creation_date + " REAL(10,0), " +
            FIELD_transactions_last_update_date + " REAL(10,0), " +
            FIELD_transactions_amount + " REAL(16,2) NOT NULL, " +
            FIELD_transactions_recurring_id + " REAL(10,0), " +
            FIELD_transactions_to_account_id + " REAL(10,0), " +
            FIELD_transactions_is_transfer_flag + " TEXT(1) DEFAULT 'N' " +
            ")";



    // Category - Table name
    public static final String TABLE_category = "category";

    // Category - Column names
    public static final String FIELD_category_id = "id";
    public static final String FIELD_category_category_name = "category_name";
    public static final String FIELD_category_parent_category_id = "parent_category_id";

    // Category - Table create statement
    private static final String CREATE_TABLE_category = "CREATE TABLE " + TABLE_category +
            "(" +
            FIELD_category_id + " INTEGER PRIMARY KEY, " +
            FIELD_category_category_name + " TEXT(30), " +
            FIELD_category_parent_category_id + " REAL(16,0) " +
            ")";



    // Payee - Table name
    public static final String TABLE_payee = "payee";

    // Payee - Column names
    public static final String FIELD_payee_id = "id";
    public static final String FIELD_payee_payee_name = "payee_name";

    // Payee - Table create statement
    private static final String CREATE_TABLE_payee = "CREATE TABLE " + TABLE_payee +
            "(" +
            FIELD_payee_id + " INTEGER PRIMARY KEY, " +
            FIELD_payee_payee_name + " TEXT(50) " +
            ")";



    // Transfers - Table name
    public static final String TABLE_transfers = "transfers";

    // Transfers - Column names
    public static final String FIELD_transfers_id = "id";
    public static final String FIELD_transfers_from_id = "from_id";
    public static final String FIELD_transfers_to_id = "to_id";
    public static final String FIELD_transfers_amount = "amount";

    // Transfers - Table create statement
    private static final String CREATE_TABLE_transfers = "CREATE TABLE " + TABLE_transfers +
            "(" +
            FIELD_transfers_id + " INTEGER PRIMARY KEY, " +
            FIELD_transfers_from_id + " REAL(10,0), " +
            FIELD_transfers_to_id + " REAL(10,0), " +
            FIELD_transfers_amount + " REAL(16,2) " +
            ")";



    // Recurring - Table name
    public static final String TABLE_recurring = "recurring";

    // Recurring - Column names
    public static final String FIELD_recurring_id = "id";
    public static final String FIELD_recurring_category_id = "category_id";
    public static final String FIELD_recurring_payee_id = "payee_id";
    public static final String FIELD_recurring_transaction_type = "transaction_type";
    public static final String FIELD_recurring_account_id = "account_id";
    public static final String FIELD_recurring_to_account_id = "to_account_id";
    public static final String FIELD_recurring_start_date = "start_date";
    public static final String FIELD_recurring_end_date = "end_date";
    public static final String FIELD_recurring_notes = "notes";
    public static final String FIELD_recurring_creation_date = "creation_date";
    public static final String FIELD_recurring_last_update_date = "last_update_date";
    public static final String FIELD_recurring_amount = "amount";
    public static final String FIELD_recurring_recurring_pattern = "recurring_pattern";

    // Recurring - Table create statement
    private static final String CREATE_TABLE_recurring = "CREATE TABLE " + TABLE_recurring +
            "(" +
            FIELD_recurring_id + " INTEGER PRIMARY KEY, " +
            FIELD_recurring_category_id + " REAL(10,0) NOT NULL, " +
            FIELD_recurring_payee_id + " REAL(10,0) NOT NULL, " +
            FIELD_recurring_transaction_type + " TEXT(10) NOT NULL, " +
            FIELD_recurring_account_id + " REAL(10,0) NOT NULL, " +
            FIELD_recurring_to_account_id + " REAL(10,0), " +
            FIELD_recurring_start_date + " REAL(10,0), " +
            FIELD_recurring_end_date + " REAL(10,0), " +
            FIELD_recurring_notes + " TEXT(50), " +
            FIELD_recurring_creation_date + " REAL(10,0), " +
            FIELD_recurring_last_update_date + " REAL(10,0), " +
            FIELD_recurring_amount + " REAL(16,2), " +
            FIELD_recurring_recurring_pattern + " TEXT(30) " +
            ")";



    //------------------------------------
    // CONSTRUCTORS
    //------------------------------------

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(CREATE_TABLE_accounts);
        db.execSQL(CREATE_TABLE_transactions);
        db.execSQL(CREATE_TABLE_category);
        db.execSQL(CREATE_TABLE_payee);
        db.execSQL(CREATE_TABLE_transfers);
        db.execSQL(CREATE_TABLE_recurring);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {

            case 1: // from v1 -> v2

            case 2: // from v2 -> v3

        }
    }

    // Closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();

        if (db != null && db.isOpen()) db.close();
    }

    //------------------------------------
    // ACCOUNTS - OPERATIONS
    //------------------------------------

    /*
     *  create or update a accounts row using an object (Model Class)
     */
    public long saveAccounts(Accounts mAccounts) {
        Date date = new Date();
        String modifiedDate= new SimpleDateFormat("yyyyMMdd").format(date);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(FIELD_accounts_id, mAccounts.getId());
        values.put(FIELD_accounts_account_name, mAccounts.getAccountName());
        values.put(FIELD_accounts_account_type, mAccounts.getAccountType());
        values.put(FIELD_accounts_currency, mAccounts.getCurrency());
        values.put(FIELD_accounts_last_update_date, modifiedDate);
        values.put(FIELD_accounts_account_balance, mAccounts.getAccountBalance());
        long id = 0;
        if (mAccounts.getId() > 0) {
            // updating row
            values.put(FIELD_accounts_creation_date, modifiedDate);
            db.update(TABLE_accounts, values, FIELD_accounts_id + "=?", new String[] {String.valueOf(mAccounts.getId())});
            id = mAccounts.getId();
        } else {

            // inserting row
            id = db.insert(TABLE_accounts, null, values);

        }

        return id;
    }

    public int duplicateCheck(String tableName
            ,String columnName
            ,String dataString
            ,String idCheck){
        int countInt = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT COUNT(1) dup_check "+
                " FROM " + tableName +
                " WHERE "+ columnName + " =? "+
                " AND id <> ?";

        Cursor cur = db.rawQuery(selectQuery, new String[] { dataString, idCheck});
        if (cur.moveToFirst()) {
            countInt = cur.getInt(cur.getColumnIndex("dup_check"));
        }
        return countInt;
    }


    /*
     *  delete a accounts record by ID
     */
    public void deleteAccounts(long id) {

        if (id > 0) {

            SQLiteDatabase db = this.getWritableDatabase();

            // deleting row
            db.delete(TABLE_accounts, FIELD_accounts_id + "=?", new String[] {String.valueOf(id)});

        }

    }



    /*
     *  getting a single Accounts object by ID
     */
    public Accounts getAccounts(long id) {

        Accounts mAccounts = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_accounts + " WHERE " + FIELD_accounts_id + "=" + id;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {

                mAccounts = new Accounts();
                mAccounts.setId(cur.getInt(cur.getColumnIndex(FIELD_accounts_id)));
                mAccounts.setAccountName(cur.getString(cur.getColumnIndex(FIELD_accounts_account_name)));
                mAccounts.setAccountType(cur.getString(cur.getColumnIndex(FIELD_accounts_account_type)));
                mAccounts.setCurrency(cur.getString(cur.getColumnIndex(FIELD_accounts_currency)));
                mAccounts.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_accounts_creation_date)));
                mAccounts.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_accounts_last_update_date)));
                mAccounts.setAccountBalance(cur.getDouble(cur.getColumnIndex(FIELD_accounts_account_balance)));

            }
            cur.close();
        }

        return mAccounts;
    }



    /*
     *  getting all Accounts objects
     */
    public List<Accounts> getAllAccounts() {

        List<Accounts> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_accounts;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    Accounts mAccounts = new Accounts();
                    mAccounts.setId(cur.getInt(cur.getColumnIndex(FIELD_accounts_id)));
                    mAccounts.setAccountName(cur.getString(cur.getColumnIndex(FIELD_accounts_account_name)));
                    mAccounts.setAccountType(cur.getString(cur.getColumnIndex(FIELD_accounts_account_type)));
                    mAccounts.setCurrency(cur.getString(cur.getColumnIndex(FIELD_accounts_currency)));
                    mAccounts.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_accounts_creation_date)));
                    mAccounts.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_accounts_last_update_date)));
                    mAccounts.setAccountBalance(cur.getDouble(cur.getColumnIndex(FIELD_accounts_account_balance)));

                    list.add(mAccounts); // adding objects to the list

                } while (cur.moveToNext());
            }
            cur.close();
        }

        return list;
    }

    public Cursor getCurAccounts() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT id _id," +
                "account_name," +
                "account_type," +
                "currency," +
                "creation_date," +
                "last_update_date," +
                "account_balance " +
                " FROM " + TABLE_accounts;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }


    /*
     *  getting Accounts rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListAccounts(List<Accounts> list) {

        List<Map<String, String>> mArrayList = new ArrayList<>();

        for (Accounts mAccounts : list) {

            HashMap<String, String> map = new HashMap<>();

            map.put("accounts_id", String.valueOf(mAccounts.getId()));
            map.put("accounts_account_name", mAccounts.getAccountName());
            map.put("accounts_account_type", mAccounts.getAccountType());
            map.put("accounts_currency", mAccounts.getCurrency());
            map.put("accounts_creation_date", String.valueOf(mAccounts.getCreationDate()));
            map.put("accounts_last_update_date", String.valueOf(mAccounts.getLastUpdateDate()));
            map.put("accounts_account_balance", String.valueOf(mAccounts.getAccountBalance()));

            mArrayList.add(map);
        }

        return mArrayList;
    }



    //------------------------------------
    // TRANSACTIONS - OPERATIONS
    //------------------------------------

    /*
     *  create or update a transactions row using an object (Model Class)
     */
    public long saveTransactions(Transactions mTransactions) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(FIELD_transactions_id, mTransactions.getId());
        values.put(FIELD_transactions_account_id, mTransactions.getAccountId());
        values.put(FIELD_transactions_transaction_type, mTransactions.getTransactionType());
        values.put(FIELD_transactions_category_id, mTransactions.getCategoryId());
        values.put(FIELD_transactions_payee_id, mTransactions.getPayeeId());
        values.put(FIELD_transactions_transaction_date, mTransactions.getTransactionDate());
        values.put(FIELD_transactions_notes, mTransactions.getNotes());
        values.put(FIELD_transactions_creation_date, mTransactions.getCreationDate());
        values.put(FIELD_transactions_last_update_date, mTransactions.getLastUpdateDate());
        values.put(FIELD_transactions_amount, mTransactions.getAmount());
        values.put(FIELD_transactions_recurring_id, mTransactions.getRecurringId());
        values.put(FIELD_transactions_to_account_id, mTransactions.getToAccountId());
        values.put(FIELD_transactions_is_transfer_flag, mTransactions.getIsTransferFlag());

        long id = 0;

        if (mTransactions.getId() > 0) {

            // updating row
            db.update(TABLE_transactions, values, FIELD_transactions_id + "=?", new String[] {String.valueOf(mTransactions.getId())});

            id = mTransactions.getId();

        } else {

            // inserting row
            id = db.insert(TABLE_transactions, null, values);

        }

        return id;
    }



    /*
     *  delete a transactions record by ID
     */
    public void deleteTransactions(long id) {

        if (id > 0) {

            SQLiteDatabase db = this.getWritableDatabase();

            // deleting row
            db.delete(TABLE_transactions, FIELD_transactions_id + "=?", new String[] {String.valueOf(id)});

        }

    }



    /*
     *  getting a single Transactions object by ID
     */
    public Transactions getTransactions(long id) {

        Transactions mTransactions = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_transactions + " WHERE " + FIELD_transactions_id + "=" + id;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {

                mTransactions = new Transactions();
                mTransactions.setId(cur.getInt(cur.getColumnIndex(FIELD_transactions_id)));
                mTransactions.setAccountId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_account_id)));
                mTransactions.setTransactionType(cur.getString(cur.getColumnIndex(FIELD_transactions_transaction_type)));
                mTransactions.setCategoryId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_category_id)));
                mTransactions.setPayeeId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_payee_id)));
                mTransactions.setTransactionDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_transaction_date)));
                mTransactions.setNotes(cur.getString(cur.getColumnIndex(FIELD_transactions_notes)));
                mTransactions.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_creation_date)));
                mTransactions.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_last_update_date)));
                mTransactions.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_transactions_amount)));
                mTransactions.setRecurringId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_recurring_id)));
                mTransactions.setToAccountId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_to_account_id)));
                mTransactions.setIsTransferFlag(cur.getString(cur.getColumnIndex(FIELD_transactions_is_transfer_flag)));

            }
            cur.close();
        }

        return mTransactions;
    }



    /*
     *  getting all Transactions objects
     */
    public List<Transactions> getAllTransactions() {

        List<Transactions> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_transactions;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    Transactions mTransactions = new Transactions();
                    mTransactions.setId(cur.getInt(cur.getColumnIndex(FIELD_transactions_id)));
                    mTransactions.setAccountId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_account_id)));
                    mTransactions.setTransactionType(cur.getString(cur.getColumnIndex(FIELD_transactions_transaction_type)));
                    mTransactions.setCategoryId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_category_id)));
                    mTransactions.setPayeeId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_payee_id)));
                    mTransactions.setTransactionDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_transaction_date)));
                    mTransactions.setNotes(cur.getString(cur.getColumnIndex(FIELD_transactions_notes)));
                    mTransactions.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_creation_date)));
                    mTransactions.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_transactions_last_update_date)));
                    mTransactions.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_transactions_amount)));
                    mTransactions.setRecurringId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_recurring_id)));
                    mTransactions.setToAccountId(cur.getDouble(cur.getColumnIndex(FIELD_transactions_to_account_id)));
                    mTransactions.setIsTransferFlag(cur.getString(cur.getColumnIndex(FIELD_transactions_is_transfer_flag)));

                    list.add(mTransactions); // adding objects to the list

                } while (cur.moveToNext());
            }
            cur.close();
        }

        return list;
    }
    public Cursor getCurTransactions() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT id," +
                "account_id," +
                "transaction_type," +
                "category_id," +
                "payee_id," +
                "transaction_date." +
                "notes," +
                "creation_date," +
                "last_update_date," +
                "amount," +
                "recurring_id," +
                "to_account_id," +
                "is_transfer_flag " +
                " FROM " + TABLE_transactions;
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }


    /*
     *  getting Transactions rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListTransactions(List<Transactions> list) {

        List<Map<String, String>> mArrayList = new ArrayList<>();

        for (Transactions mTransactions : list) {

            HashMap<String, String> map = new HashMap<>();

            map.put("transactions_id", String.valueOf(mTransactions.getId()));
            map.put("transactions_account_id", String.valueOf(mTransactions.getAccountId()));
            map.put("transactions_transaction_type", mTransactions.getTransactionType());
            map.put("transactions_category_id", String.valueOf(mTransactions.getCategoryId()));
            map.put("transactions_payee_id", String.valueOf(mTransactions.getPayeeId()));
            map.put("transactions_transaction_date", String.valueOf(mTransactions.getTransactionDate()));
            map.put("transactions_notes", mTransactions.getNotes());
            map.put("transactions_creation_date", String.valueOf(mTransactions.getCreationDate()));
            map.put("transactions_last_update_date", String.valueOf(mTransactions.getLastUpdateDate()));
            map.put("transactions_amount", String.valueOf(mTransactions.getAmount()));
            map.put("transactions_recurring_id", String.valueOf(mTransactions.getRecurringId()));
            map.put("transactions_to_account_id", String.valueOf(mTransactions.getToAccountId()));
            map.put("transactions_is_transfer_flag", mTransactions.getIsTransferFlag());

            mArrayList.add(map);
        }
        return mArrayList;
    }



    //------------------------------------
    // CATEGORY - OPERATIONS
    //------------------------------------

    /*
     *  create or update a category row using an object (Model Class)
     */
    public long saveCategory(Category mCategory) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(FIELD_category_id, mCategory.getId());
        values.put(FIELD_category_category_name, mCategory.getCategoryName());
        values.put(FIELD_category_parent_category_id, mCategory.getParentCategoryId());

        long id = 0;

        if (mCategory.getId() > 0) {

            // updating row
            db.update(TABLE_category, values, FIELD_category_id + "=?", new String[] {String.valueOf(mCategory.getId())});

            id = mCategory.getId();

        } else {

            // inserting row
            id = db.insert(TABLE_category, null, values);

        }

        return id;
    }



    /*
     *  delete a category record by ID
     */
    public void deleteCategory(long id) {

        if (id > 0) {

            SQLiteDatabase db = this.getWritableDatabase();

            // deleting row
            db.delete(TABLE_category, FIELD_category_id + "=?", new String[] {String.valueOf(id)});

        }

    }



    /*
     *  getting a single Category object by ID
     */
    public Category getCategory(long id) {

        Category mCategory = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_category + " WHERE " + FIELD_category_id + "=" + id;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {

                mCategory = new Category();
                mCategory.setId(cur.getInt(cur.getColumnIndex(FIELD_category_id)));
                mCategory.setCategoryName(cur.getString(cur.getColumnIndex(FIELD_category_category_name)));
                mCategory.setParentCategoryId(cur.getInt(cur.getColumnIndex(FIELD_category_parent_category_id)));

            }
            cur.close();
        }

        return mCategory;
    }



    /*
     *  getting all Category objects
     */
    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_category;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Category mCategory = new Category();
                    mCategory.setId(cur.getInt(cur.getColumnIndex(FIELD_category_id)));
                    mCategory.setCategoryName(cur.getString(cur.getColumnIndex(FIELD_category_category_name)));
                    mCategory.setParentCategoryId(cur.getInt(cur.getColumnIndex(FIELD_category_parent_category_id)));
                    list.add(mCategory); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public List<Category> getAllParentCategory() {
        List<Category> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_category + " WHERE "+ FIELD_category_parent_category_id + " IS NULL ";
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Category mCategory = new Category();
                    mCategory.setId(cur.getInt(cur.getColumnIndex(FIELD_category_id)));
                    mCategory.setCategoryName(cur.getString(cur.getColumnIndex(FIELD_category_category_name)));
                    mCategory.setParentCategoryId(cur.getInt(cur.getColumnIndex(FIELD_category_parent_category_id)));
                    list.add(mCategory); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public Cursor getCurCategory() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT id _id," +
                "category_name," +
                "parent_category_id " +
                " FROM " + TABLE_category;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }



    /*
     *  getting Category rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListCategory(List<Category> list) {
        List<Map<String, String>> mArrayList = new ArrayList<>();
        for (Category mCategory : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("category_id", String.valueOf(mCategory.getId()));
            map.put("category_category_name", mCategory.getCategoryName());
            map.put("category_parent_category_id", String.valueOf(mCategory.getParentCategoryId()));
            mArrayList.add(map);
        }
        return mArrayList;
    }


    //------------------------------------
    // PAYEE - OPERATIONS
    //------------------------------------

    /*
     *  create or update a payee row using an object (Model Class)
     */
    public long savePayee(Payee mPayee) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(FIELD_payee_id, mPayee.getId());
        values.put(FIELD_payee_payee_name, mPayee.getPayeeName());
        long id = 0;
        if (mPayee.getId() > 0) {

            // updating row
            db.update(TABLE_payee, values, FIELD_payee_id + "=?", new String[] {String.valueOf(mPayee.getId())});

            id = mPayee.getId();

        } else {
            // inserting row
            id = db.insert(TABLE_payee, null, values);
        }
        return id;
    }



    /*
     *  delete a payee record by ID
     */
    public void deletePayee(long id) {
        if (id > 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            // deleting row
            db.delete(TABLE_payee, FIELD_payee_id + "=?", new String[] {String.valueOf(id)});
        }
    }



    /*
     *  getting a single Payee object by ID
     */
    public Payee getPayee(long id) {
        Payee mPayee = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_payee + " WHERE " + FIELD_payee_id + "=" + id;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mPayee = new Payee();
                mPayee.setId(cur.getInt(cur.getColumnIndex(FIELD_payee_id)));
                mPayee.setPayeeName(cur.getString(cur.getColumnIndex(FIELD_payee_payee_name)));
            }
            cur.close();
        }
        return mPayee;
    }



    /*
     *  getting all Payee objects
     */
    public List<Payee> getAllPayee() {
        List<Payee> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_payee;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Payee mPayee = new Payee();
                    mPayee.setId(cur.getInt(cur.getColumnIndex(FIELD_payee_id)));
                    mPayee.setPayeeName(cur.getString(cur.getColumnIndex(FIELD_payee_payee_name)));
                    list.add(mPayee); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public Cursor getCurPayee() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT id _id," +
                "payee_name " +
                " FROM " + TABLE_payee;
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }



    /*
     *  getting Payee rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListPayee(List<Payee> list) {
        List<Map<String, String>> mArrayList = new ArrayList<>();
        for (Payee mPayee : list) {
            HashMap<String, String> map = new HashMap<>();
            map.put("payee_id", String.valueOf(mPayee.getId()));
            map.put("payee_payee_name", String.valueOf(mPayee.getPayeeName()));
            mArrayList.add(map);
        }
        return mArrayList;
    }



    //------------------------------------
    // TRANSFERS - OPERATIONS
    //------------------------------------

    /*
     *  create or update a transfers row using an object (Model Class)
     */
    public long saveTransfers(Transfers mTransfers) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(FIELD_transfers_id, mTransfers.getId());
        values.put(FIELD_transfers_from_id, mTransfers.getFromId());
        values.put(FIELD_transfers_to_id, mTransfers.getToId());
        values.put(FIELD_transfers_amount, mTransfers.getAmount());
        long id = 0;
        if (mTransfers.getId() > 0) {
            // updating row
            db.update(TABLE_transfers, values, FIELD_transfers_id + "=?", new String[] {String.valueOf(mTransfers.getId())});
            id = mTransfers.getId();
        } else {
            // inserting row
            id = db.insert(TABLE_transfers, null, values);
        }
        return id;
    }



    /*
     *  delete a transfers record by ID
     */
    public void deleteTransfers(long id) {
        if (id > 0) {
            SQLiteDatabase db = this.getWritableDatabase();
            // deleting row
            db.delete(TABLE_transfers, FIELD_transfers_id + "=?", new String[] {String.valueOf(id)});
        }
    }



    /*
     *  getting a single Transfers object by ID
     */
    public Transfers getTransfers(long id) {
        Transfers mTransfers = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_transfers + " WHERE " + FIELD_transfers_id + "=" + id;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                mTransfers = new Transfers();
                mTransfers.setId(cur.getInt(cur.getColumnIndex(FIELD_transfers_id)));
                mTransfers.setFromId(cur.getDouble(cur.getColumnIndex(FIELD_transfers_from_id)));
                mTransfers.setToId(cur.getDouble(cur.getColumnIndex(FIELD_transfers_to_id)));
                mTransfers.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_transfers_amount)));
            }
            cur.close();
        }
        return mTransfers;
    }



    /*
     *  getting all Transfers objects
     */
    public List<Transfers> getAllTransfers() {
        List<Transfers> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_transfers;
        //Log.i(LOG, selectQuery);
        Cursor cur = db.rawQuery(selectQuery, null);
        if (cur != null) {
            if (cur.moveToFirst()) {
                do {
                    Transfers mTransfers = new Transfers();
                    mTransfers.setId(cur.getInt(cur.getColumnIndex(FIELD_transfers_id)));
                    mTransfers.setFromId(cur.getDouble(cur.getColumnIndex(FIELD_transfers_from_id)));
                    mTransfers.setToId(cur.getDouble(cur.getColumnIndex(FIELD_transfers_to_id)));
                    mTransfers.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_transfers_amount)));
                    list.add(mTransfers); // adding objects to the list
                } while (cur.moveToNext());
            }
            cur.close();
        }
        return list;
    }

    public Cursor getCurTransfers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_transfers;
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }


    /*
     *  getting Transfers rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListTransfers(List<Transfers> list) {

        List<Map<String, String>> mArrayList = new ArrayList<>();

        for (Transfers mTransfers : list) {

            HashMap<String, String> map = new HashMap<>();

            map.put("transfers_id", String.valueOf(mTransfers.getId()));
            map.put("transfers_from_id", String.valueOf(mTransfers.getFromId()));
            map.put("transfers_to_id", String.valueOf(mTransfers.getToId()));
            map.put("transfers_amount", String.valueOf(mTransfers.getAmount()));

            mArrayList.add(map);
        }

        return mArrayList;
    }



    //------------------------------------
    // RECURRING - OPERATIONS
    //------------------------------------

    /*
     *  create or update a recurring row using an object (Model Class)
     */
    public long saveRecurring(Recurring mRecurring) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(FIELD_recurring_id, mRecurring.getId());
        values.put(FIELD_recurring_category_id, mRecurring.getCategoryId());
        values.put(FIELD_recurring_payee_id, mRecurring.getPayeeId());
        values.put(FIELD_recurring_transaction_type, mRecurring.getTransactionType());
        values.put(FIELD_recurring_account_id, mRecurring.getAccountId());
        values.put(FIELD_recurring_to_account_id, mRecurring.getToAccountId());
        values.put(FIELD_recurring_start_date, mRecurring.getStartDate());
        values.put(FIELD_recurring_end_date, mRecurring.getEndDate());
        values.put(FIELD_recurring_notes, mRecurring.getNotes());
        values.put(FIELD_recurring_creation_date, mRecurring.getCreationDate());
        values.put(FIELD_recurring_last_update_date, mRecurring.getLastUpdateDate());
        values.put(FIELD_recurring_amount, mRecurring.getAmount());
        values.put(FIELD_recurring_recurring_pattern, mRecurring.getRecurringPattern());

        long id = 0;

        if (mRecurring.getId() > 0) {

            // updating row
            db.update(TABLE_recurring, values, FIELD_recurring_id + "=?", new String[] {String.valueOf(mRecurring.getId())});

            id = mRecurring.getId();

        } else {

            // inserting row
            id = db.insert(TABLE_recurring, null, values);

        }

        return id;
    }



    /*
     *  delete a recurring record by ID
     */
    public void deleteRecurring(long id) {

        if (id > 0) {

            SQLiteDatabase db = this.getWritableDatabase();

            // deleting row
            db.delete(TABLE_recurring, FIELD_recurring_id + "=?", new String[] {String.valueOf(id)});

        }

    }



    /*
     *  getting a single Recurring object by ID
     */
    public Recurring getRecurring(long id) {

        Recurring mRecurring = null;

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_recurring + " WHERE " + FIELD_recurring_id + "=" + id;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {

                mRecurring = new Recurring();
                mRecurring.setId(cur.getInt(cur.getColumnIndex(FIELD_recurring_id)));
                mRecurring.setCategoryId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_category_id)));
                mRecurring.setPayeeId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_payee_id)));
                mRecurring.setTransactionType(cur.getString(cur.getColumnIndex(FIELD_recurring_transaction_type)));
                mRecurring.setAccountId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_account_id)));
                mRecurring.setToAccountId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_to_account_id)));
                mRecurring.setStartDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_start_date)));
                mRecurring.setEndDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_end_date)));
                mRecurring.setNotes(cur.getString(cur.getColumnIndex(FIELD_recurring_notes)));
                mRecurring.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_creation_date)));
                mRecurring.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_last_update_date)));
                mRecurring.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_recurring_amount)));
                mRecurring.setRecurringPattern(cur.getString(cur.getColumnIndex(FIELD_recurring_recurring_pattern)));

            }
            cur.close();
        }

        return mRecurring;
    }



    /*
     *  getting all Recurring objects
     */
    public List<Recurring> getAllRecurring() {

        List<Recurring> list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_recurring;
        //Log.i(LOG, selectQuery);

        Cursor cur = db.rawQuery(selectQuery, null);

        if (cur != null) {
            if (cur.moveToFirst()) {
                do {

                    Recurring mRecurring = new Recurring();
                    mRecurring.setId(cur.getInt(cur.getColumnIndex(FIELD_recurring_id)));
                    mRecurring.setCategoryId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_category_id)));
                    mRecurring.setPayeeId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_payee_id)));
                    mRecurring.setTransactionType(cur.getString(cur.getColumnIndex(FIELD_recurring_transaction_type)));
                    mRecurring.setAccountId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_account_id)));
                    mRecurring.setToAccountId(cur.getDouble(cur.getColumnIndex(FIELD_recurring_to_account_id)));
                    mRecurring.setStartDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_start_date)));
                    mRecurring.setEndDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_end_date)));
                    mRecurring.setNotes(cur.getString(cur.getColumnIndex(FIELD_recurring_notes)));
                    mRecurring.setCreationDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_creation_date)));
                    mRecurring.setLastUpdateDate(cur.getDouble(cur.getColumnIndex(FIELD_recurring_last_update_date)));
                    mRecurring.setAmount(cur.getDouble(cur.getColumnIndex(FIELD_recurring_amount)));
                    mRecurring.setRecurringPattern(cur.getString(cur.getColumnIndex(FIELD_recurring_recurring_pattern)));

                    list.add(mRecurring); // adding objects to the list

                } while (cur.moveToNext());
            }
            cur.close();
        }

        return list;
    }

    public Cursor getCurRecurring() {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT id _id," +
                "category_id," +
                "payee_id," +
                "transaction_type," +
                "account_id," +
                "to_account_id," +
                "start_date," +
                "end_ate," +
                "notes," +
                "creation_date," +
                "last_update_date," +
                "amount," +
                "recurring_pattern" +
                " FROM " + TABLE_recurring;
        Cursor cur = db.rawQuery(selectQuery, null);
        return cur;
    }


    /*
     *  getting Recurring rows as mapped list for ListViews
     */
    public List<Map<String, String>> getListRecurring(List<Recurring> list) {

        List<Map<String, String>> mArrayList = new ArrayList<>();

        for (Recurring mRecurring : list) {

            HashMap<String, String> map = new HashMap<>();

            map.put("recurring_id", String.valueOf(mRecurring.getId()));
            map.put("recurring_category_id", String.valueOf(mRecurring.getCategoryId()));
            map.put("recurring_payee_id", String.valueOf(mRecurring.getPayeeId()));
            map.put("recurring_transaction_type", mRecurring.getTransactionType());
            map.put("recurring_account_id", String.valueOf(mRecurring.getAccountId()));
            map.put("recurring_to_account_id", String.valueOf(mRecurring.getToAccountId()));
            map.put("recurring_start_date", String.valueOf(mRecurring.getStartDate()));
            map.put("recurring_end_date", String.valueOf(mRecurring.getEndDate()));
            map.put("recurring_notes", mRecurring.getNotes());
            map.put("recurring_creation_date", String.valueOf(mRecurring.getCreationDate()));
            map.put("recurring_last_update_date", String.valueOf(mRecurring.getLastUpdateDate()));
            map.put("recurring_amount", String.valueOf(mRecurring.getAmount()));
            map.put("recurring_recurring_pattern", mRecurring.getRecurringPattern());

            mArrayList.add(map);
        }

        return mArrayList;
    }
}