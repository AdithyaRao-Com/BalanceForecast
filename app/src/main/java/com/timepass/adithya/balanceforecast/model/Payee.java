package com.timepass.adithya.balanceforecast.model;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.payee
*
*/
public class Payee {

    // private members
    private int id;
    private double payeeName;


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
    public Payee(int id, double payee_name) {
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

    public double getPayeeName() {
        return this.payeeName;
    }

    public void setPayeeName(double payee_name) {
        this.payeeName = payee_name;
    }



    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }

}



