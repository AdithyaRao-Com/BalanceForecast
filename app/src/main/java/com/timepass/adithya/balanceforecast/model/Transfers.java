package com.timepass.adithya.balanceforecast.model;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.transfers
*
*/
public class Transfers {

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

}
