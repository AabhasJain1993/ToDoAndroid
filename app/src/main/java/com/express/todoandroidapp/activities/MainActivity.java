package com.express.todoandroidapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.model.ToDoItemCategory;
import com.express.todoandroidapp.views.ToDoListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ToDoListView.Listener{

    @OnClick(R.id.fab)
    void onClickButton() {
    //TODO Make a Dialog new Item and insert in database

    }

    @Bind(R.id.todoListView)
    ToDoListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mListView.setListener(this);
    }

    @Override
    public void onCategoryItemClicked(ToDoItemCategory category) {
        Intent intent = new Intent(this, CategoryItemsActivity.class);
        startActivity(intent);
    }
}
