package com.timepass.adithya.balanceforecast.model;

/**
 * Created by Adithya Rao on 9/18/16.
 */

/*
*  Java - Model Class - dbPersonalExpense.recurring
*
*/
public class Recurring {

    // private members
    private int id;
    private double categoryId;
    private double payeeId;
    private String transactionType;
    private double accountId;
    private double toAccountId;
    private double startDate;
    private double endDate;
    private String notes;
    private double creationDate;
    private double lastUpdateDate;
    private double amount;
    private String recurringPattern;


    /**
     * Constructor
     *
     * Example:
     * Recurring myRecurring = new Recurring();
     */
    public Recurring() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Recurring myRecurring = new Recurring( val1, val2,.. );
     */
    public Recurring(int id, double category_id, double payee_id, String transaction_type, double account_id, double to_account_id, double start_date, double end_date, String notes, double creation_date, double last_update_date, double amount, String recurring_pattern) {
        this.setId(id);
        this.setCategoryId(category_id);
        this.setPayeeId(payee_id);
        this.setTransactionType(transaction_type);
        this.setAccountId(account_id);
        this.setToAccountId(to_account_id);
        this.setStartDate(start_date);
        this.setEndDate(end_date);
        this.setNotes(notes);
        this.setCreationDate(creation_date);
        this.setLastUpdateDate(last_update_date);
        this.setAmount(amount);
        this.setRecurringPattern(recurring_pattern);
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

    public String getTransactionType() {
        return this.transactionType;
    }

    public void setTransactionType(String transaction_type) {
        this.transactionType = transaction_type;
    }

    public double getAccountId() {
        return this.accountId;
    }

    public void setAccountId(double account_id) {
        this.accountId = account_id;
    }

    public double getToAccountId() {
        return this.toAccountId;
    }

    public void setToAccountId(double to_account_id) {
        this.toAccountId = to_account_id;
    }

    public double getStartDate() {
        return this.startDate;
    }

    public void setStartDate(double start_date) {
        this.startDate = start_date;
    }

    public double getEndDate() {
        return this.endDate;
    }

    public void setEndDate(double end_date) {
        this.endDate = end_date;
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

    public String getRecurringPattern() {
        return this.recurringPattern;
    }

    public void setRecurringPattern(String recurring_pattern) {
        this.recurringPattern = recurring_pattern;
    }



    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }

}
