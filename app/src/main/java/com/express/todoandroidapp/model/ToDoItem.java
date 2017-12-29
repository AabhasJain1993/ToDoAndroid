package com.express.todoandroidapp.model;

import android.graphics.Bitmap;

/**
 * Created by root on 28/12/17.
 */

public class ToDoItem {

    int id;
    String itemTitle;
    boolean isItemDone = false;
    String itemDescription;
    String imagePath;

    public String getItemTitle() {
        return itemTitle;
    }

    public boolean getIsItemDone() {
        return isItemDone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemTitle(String title) {
        itemTitle = title;
    }

    public void setItemDescription(String description) {
        itemDescription = description;
    }

    public void setIfDone(boolean ifDone) {
        isItemDone = ifDone;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
