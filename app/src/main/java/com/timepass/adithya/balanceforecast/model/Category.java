package com.timepass.adithya.balanceforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

/*
*  Java - Model Class - dbPersonalExpense.category
*
*/
public class Category implements Parcelable {

    // private members
    private int id;
    private String categoryName;
    private int parentCategoryId;

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
    public Category(int id, String category_name, int parentCategoryId) {
        this.setId(id);
        this.setCategoryName(category_name);
        this.setParentCategoryId(parentCategoryId);
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

    public int getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(int parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    /**
     * Methods
     */

    @Override
    public String toString() {
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (getId() != category.getId()) return false;
        if (getParentCategoryId() != category.getParentCategoryId()) return false;
        return getCategoryName().equals(category.getCategoryName());

    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + getCategoryName().hashCode();
        result = 31 * result + getParentCategoryId();
        return result;
    }

    protected Category(Parcel in) {
        id = in.readInt();
        categoryName = in.readString();
        parentCategoryId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(categoryName);
        dest.writeInt(parentCategoryId);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Category> CREATOR = new Parcelable.Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };
}