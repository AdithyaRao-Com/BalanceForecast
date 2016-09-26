package com.timepass.adithya.balanceforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.transactions
*
*/
public class Transactions implements Parcelable {

    // private members
    private int id;
    private double accountId;
    private String transactionType;
    private double categoryId;
    private double payeeId;
    private double transactionDate;
    private String notes;
    private double creationDate;
    private double lastUpdateDate;
    private double amount;
    private double recurringId;
    private double toAccountId;
    private String isTransferFlag;


    /**
     * Constructor
     *
     * Example:
     * Transactions myTransactions = new Transactions();
     */
    public Transactions() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Transactions myTransactions = new Transactions( val1, val2,.. );
     */
    public Transactions(int id
            , double account_id
            , String transaction_type
            , double category_id
            , double payee_id, double transaction_date
            , String notes, double creation_date
            , double last_update_date
            , double amount
            , double recurring_id
            , double to_account_id
            , String is_transfer_flag) {
        this.setId(id);
        this.setAccountId(account_id);
        this.setTransactionType(transaction_type);
        this.setCategoryId(category_id);
        this.setPayeeId(payee_id);
        this.setTransactionDate(transaction_date);
        this.setNotes(notes);
        this.setCreationDate(creation_date);
        this.setLastUpdateDate(last_update_date);
        this.setAmount(amount);
        this.setRecurringId(recurring_id);
        this.setToAccountId(to_account_id);
        this.setIsTransferFlag(is_transfer_flag);
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

    public double getAccountId() {
        return this.accountId;
    }

    public void setAccountId(double account_id) {
        this.accountId = account_id;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transaction_type) {
        this.transactionType = transaction_type;
    }

    public double getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(double category_id) {
        this.categoryId = category_id;
    }

    public double getPayeeId() {
        return this.payeeId;
    }

    public void setPayeeId(double payee_id) {
        this.payeeId = payee_id;
    }

    public double getTransactionDate() {
        return this.transactionDate;
    }

    public void setTransactionDate(double transaction_date) {
        this.transactionDate = transaction_date;
    }

    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
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

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRecurringId() {
        return this.recurringId;
    }

    public void setRecurringId(double recurring_id) {
        this.recurringId = recurring_id;
    }

    public double getToAccountId() {
        return this.toAccountId;
    }

    public void setToAccountId(double to_account_id) {
        this.toAccountId = to_account_id;
    }

    public String getIsTransferFlag() {
        return this.isTransferFlag;
    }

    public void setIsTransferFlag(String is_transfer_flag) {
        this.isTransferFlag = is_transfer_flag;
    }



    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }


    protected Transactions(Parcel in) {
        id = in.readInt();
        accountId = in.readDouble();
        transactionType = in.readString();
        categoryId = in.readDouble();
        payeeId = in.readDouble();
        transactionDate = in.readDouble();
        notes = in.readString();
        creationDate = in.readDouble();
        lastUpdateDate = in.readDouble();
        amount = in.readDouble();
        recurringId = in.readDouble();
        toAccountId = in.readDouble();
        isTransferFlag = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(accountId);
        dest.writeString(transactionType);
        dest.writeDouble(categoryId);
        dest.writeDouble(payeeId);
        dest.writeDouble(transactionDate);
        dest.writeString(notes);
        dest.writeDouble(creationDate);
        dest.writeDouble(lastUpdateDate);
        dest.writeDouble(amount);
        dest.writeDouble(recurringId);
        dest.writeDouble(toAccountId);
        dest.writeString(isTransferFlag);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Transactions> CREATOR = new Parcelable.Creator<Transactions>() {
        @Override
        public Transactions createFromParcel(Parcel in) {
            return new Transactions(in);
        }

        @Override
        public Transactions[] newArray(int size) {
            return new Transactions[size];
        }
    };
}


