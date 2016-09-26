package com.timepass.adithya.balanceforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.transfers
*
*/
public class Transfers implements Parcelable {

    // private members
    private int id;
    private double fromId;
    private double toId;
    private double amount;


    /**
     * Constructor
     *
     * Example:
     * Transfers myTransfers = new Transfers();
     */
    public Transfers() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Transfers myTransfers = new Transfers( val1, val2,.. );
     */
    public Transfers(int id, double from_id, double to_id, double amount) {
        this.setId(id);
        this.setFromId(from_id);
        this.setToId(to_id);
        this.setAmount(amount);
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

    public double getFromId() {
        return this.fromId;
    }

    public void setFromId(double from_id) {
        this.fromId = from_id;
    }

    public double getToId() {
        return this.toId;
    }

    public void setToId(double to_id) {
        this.toId = to_id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }


    protected Transfers(Parcel in) {
        id = in.readInt();
        fromId = in.readDouble();
        toId = in.readDouble();
        amount = in.readDouble();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeDouble(fromId);
        dest.writeDouble(toId);
        dest.writeDouble(amount);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Transfers> CREATOR = new Parcelable.Creator<Transfers>() {
        @Override
        public Transfers createFromParcel(Parcel in) {
            return new Transfers(in);
        }

        @Override
        public Transfers[] newArray(int size) {
            return new Transfers[size];
        }
    };
}
