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
    Bitmap itemImage;

    public String getItemTitle() {
        return itemTitle;
    }

    public boolean getIsItemDone() {
        return isItemDone;
    }
}
