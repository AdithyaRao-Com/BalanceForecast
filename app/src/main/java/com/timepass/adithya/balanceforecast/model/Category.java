package com.timepass.adithya.balanceforecast.model;

/*
*  Java - Model Class - dbPersonalExpense.category
*
*/
public class Category {

    // private members
    private int id;
    private String categoryName;


    /**
     * Constructor
     *
     * Example:
     * Category myCategory = new Category();
     */
    public Category() {
        //--
    }

    /**
     * Constructor
     *
     * Example:
     * Category myCategory = new Category( val1, val2,.. );
     */
    public Category(int id, String category_name) {
        this.setId(id);
        this.setCategoryName(category_name);
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

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String category_name) {
        this.categoryName = category_name;
    }



    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }

}


