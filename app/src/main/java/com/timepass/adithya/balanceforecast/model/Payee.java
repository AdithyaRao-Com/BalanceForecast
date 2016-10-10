package com.timepass.adithya.balanceforecast.model;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.timepass.adithya.balanceforecast.helper.DatabaseHelper;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.payee
*
*/
public class Payee implements Parcelable {

    // private members
    private int id;
    private String payeeName;


    /**
     * Constructor
     *
     * Example:
     * Payee myPayee = new Payee();
     */
    public Payee() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Payee myPayee = new Payee( val1, val2,.. );
     */
    public Payee(int id, String payee_name) {
        this.setId(id);
        this.setPayeeName(payee_name);
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

    public String getPayeeName() {
        return this.payeeName;
    }

    public void setPayeeName(String payee_name) {
        this.payeeName = payee_name;
    }



    /**
     * Methods
     */
    public boolean insertUpdateAccountToDB(Context ctx){
        DatabaseHelper db = new DatabaseHelper(ctx);
        /******************************************************
         *Validate category Name
         *****************************************************/
        this.payeeName = this.payeeName.trim();
        if (this.payeeName.equals("")){
            return false;
        }
        int tmpAccCount = db.duplicateCheck(db.TABLE_payee
                , db.FIELD_payee_payee_name
                , this.payeeName
                , String.valueOf(this.id));
        if(tmpAccCount>1){
            return false;
        }
        this.id = (int) db.savePayee(this);
        if(this.id > 0){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean deleteAccountFromDB(Context ctx){
        DatabaseHelper db = new DatabaseHelper(ctx);
        long longId = (long) this.id;
        if(!(this.id >0)){
            return false;
        } else {
            db.deleteCategory(longId);
            return true;
        }
    }
    @Override
    public String toString() {
        return "";
    }


    protected Payee(Parcel in) {
        id = in.readInt();
        payeeName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(payeeName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Payee> CREATOR = new Parcelable.Creator<Payee>() {
        @Override
        public Payee createFromParcel(Parcel in) {
            return new Payee(in);
        }

        @Override
        public Payee[] newArray(int size) {
            return new Payee[size];
        }
    };
}