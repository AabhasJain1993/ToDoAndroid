package com.express.todoandroidapp.model;

import java.util.List;

/**
 * Created by root on 28/12/17.
 */

public class ToDoItemCategory {

    int id;
    String CategoryTitle;
    List<ToDoItem> toDoItemsList;

    public String getCategoryTitle() {
        return CategoryTitle;
    }

    public List<ToDoItem> getToDoItemsList() {
        return toDoItemsList;
    }

    public int getToDoItemsListCount() {
        return toDoItemsList.size();
    }

}
