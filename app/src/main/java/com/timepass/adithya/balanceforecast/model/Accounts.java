package com.timepass.adithya.balanceforecast.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.accounts
*  2016-07-06 19:58:39
*/
public class Accounts implements Parcelable {

    // private members
    private int id;
    private String accountName;
    private String accountType;
    private String currency;
    private double creationDate;
    private double lastUpdateDate;
    private double acountBalance;


    /**
     * Constructor
     *
     * Example:
     * Accounts myAccounts = new Accounts();
     */
    public Accounts() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Accounts myAccounts = new Accounts( val1, val2,.. );
     */
    public Accounts(int id
            , String account_name
            , String account_type
            , String currency
            , double creation_date
            , double last_update_date
            , double account_balance) {
        this.setId(id);
        this.setAccountName(account_name);
        this.setAccountType(account_type);
        this.setCurrency(currency);
        this.setCreationDate(creation_date);
        this.setLastUpdateDate(last_update_date);
        this.setAccountBalance(account_balance);
    }


    /**
     * Getters and Setters
     */

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public void setAccountName(String account_name) {
        this.accountName = account_name;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String account_type) {
        this.accountType = account_type;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(double creation_date) {
        this.creationDate = creation_date;
    }

    public double getLastUpdateDate() {
        return this.lastUpdateDate;
    }

    public void setLastUpdateDate(double last_update_date) {
        this.lastUpdateDate = last_update_date;
    }

    public double getAccountBalance() {
        return this.acountBalance;
    }

    public void setAccountBalance(double account_balance) {
        this.acountBalance = account_balance;
    }

    /**
     * Methods
     */

    @Override
    public String toString() {
        return this.accountName;
    }

    protected Accounts(Parcel in) {
        id = in.readInt();
        accountName = in.readString();
        accountType = in.readString();
        currency = in.readString();
        creationDate = in.readDouble();
        lastUpdateDate = in.readDouble();
        acountBalance = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(accountName);
        dest.writeString(accountType);
        dest.writeString(currency);
        dest.writeDouble(creationDate);
        dest.writeDouble(lastUpdateDate);
        dest.writeDouble(acountBalance);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Accounts> CREATOR = new Parcelable.Creator<Accounts>() {
        @Override
        public Accounts createFromParcel(Parcel in) {
            return new Accounts(in);
        }

        @Override
        public Accounts[] newArray(int size) {
            return new Accounts[size];
        }
    };
}