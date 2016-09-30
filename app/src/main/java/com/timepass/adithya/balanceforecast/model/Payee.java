package com.timepass.adithya.balanceforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

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