package com.express.todoandroidapp.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.express.todoandroidapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 28/12/17.
 */

public class EditActivity extends AppCompatActivity {

    @Bind(R.id.addItemView)
    TextView titleView;

    @Bind(R.id.delete)
    TextView deleteView;

    @Bind(R.id.addphotobtn)
    TextView addPhotoBtn;

    @OnClick(R.id.crossbtn)
    void close() {
        finish();
    }

    @OnClick(R.id.save)
    void saveIndb() {


    }

    @OnClick(R.id.delete)
    void deletefromDB() {

    }

    private boolean mIsAddData = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        ButterKnife.bind(this);

        mIsAddData = getIntent().getBooleanExtra("AddData", false);

        if(!mIsAddData) {
            titleView.setText("Add Item");
            deleteView.setVisibility(View.GONE);
            addPhotoBtn.setText("ADD PHOTO");
        } else {
            titleView.setText("Edit Item");
            deleteView.setVisibility(View.VISIBLE);
            addPhotoBtn.setText("CHANGE PHOTO");
        }

    }
}

