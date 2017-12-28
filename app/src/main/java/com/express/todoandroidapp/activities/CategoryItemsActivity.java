package com.express.todoandroidapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.interfaces.ICategoryItemClickListener;
import com.express.todoandroidapp.model.ToDoItemCategory;
import com.express.todoandroidapp.views.CategoryItemsListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 28/12/17.
 */

public class CategoryItemsActivity extends AppCompatActivity implements CategoryItemsListView.Listener{

    @Bind(R.id.todoCategoryList)
    CategoryItemsListView mCategoryItemsListView;

    @OnClick(R.id.fabCategory)
    void onClickButton() {
        //TODO Make a Dialog new Item and insert in database
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
        intent.putExtra("AddData", true);

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoryitems);
        ButterKnife.bind(this);
        mCategoryItemsListView.setListener(this);
    }


    @Override
    public void onItemClicked(ToDoItemCategory category) {
        Intent intent = new Intent(this, EditActivity.class);
        startActivity(intent);
        intent.putExtra("AddData", false);
    }
}
