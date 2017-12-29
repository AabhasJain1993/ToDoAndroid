package com.express.todoandroidapp.model;

import java.util.List;

/**
 * Created by root on 28/12/17.
 */

public class ToDoItemCategory {


    String CategoryTitle;
    int toDoItemsCount;

    public String getCategoryTitle() {
        return CategoryTitle;
    }


    public int getToDoItemsListCount() {
        return toDoItemsCount;
    }

    public void setCategoryTitle(String str) {
        CategoryTitle = str;
    }


    public void setToDoItemsListCount(int count) {
        toDoItemsCount = count;
    }


}
