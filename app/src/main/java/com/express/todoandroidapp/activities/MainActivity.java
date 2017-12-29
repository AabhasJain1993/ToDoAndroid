package com.express.todoandroidapp.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.model.ToDoItemCategory;
import com.express.todoandroidapp.views.AddCategoryFragment;
import com.express.todoandroidapp.views.ToDoListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements ToDoListView.Listener, AddCategoryFragment.Listener{

    @OnClick(R.id.fab)
    void onClickButton() {
    //TODO Make a Dialog new Item and insert in database
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("AddCategoyFragment");
        if(prev ==null) {
            /** Create and show the dialog.*/
            AddCategoryFragment newFragment = AddCategoryFragment.newInstance();
            newFragment.setListener(this);
            newFragment.show(ft,"AddCategoyFragment" );
        }
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


    @Override
    public void onClickDialogAdd() {
        mListView.refreshList();
        Log.d("MainActvity", "referesh list called");
    }

    @Override
    public void onClickDialogCancel() {

    }

}
