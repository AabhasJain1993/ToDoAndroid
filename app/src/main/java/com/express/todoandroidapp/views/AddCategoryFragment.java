package com.express.todoandroidapp.views;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.BinderThread;
import android.support.v4.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.db.DatabaseManager;
import com.express.todoandroidapp.db.QueryExecutor;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by root on 28/12/17.
 */

public class AddCategoryFragment extends DialogFragment {

    private AddCategoryFragment.Listener mListener = null;

    @Bind(R.id.addcategory)
    EditText editText;

    @Bind(R.id.alertTitleCategory)
    TextView alertTitle;

    @OnClick(R.id.addbtn)
    void onClickaddBtn() {
        if(editText.getText().toString()!=null) {
            DatabaseManager manager= DatabaseManager.getInstance();
            SQLiteDatabase db=manager.openDatabase();
            QueryExecutor qe = new QueryExecutor();
            if(!qe.insertNewCategory(db, editText.getText().toString().toLowerCase())) {
                alertTitle.setVisibility(View.VISIBLE);
            } else{
                if(mListener !=null) {
                    mListener.onClickDialogAdd();
                }
                removeFragment();
            }
            manager.closeDatabase();
        }
    }

    @OnClick(R.id.addcategory)
    void addCategory() {
        alertTitle.setVisibility(View.GONE);
    }


    @OnClick(R.id.cancelbtn)
    void onClickCancelBtn() {
        if(mListener !=null) {
            mListener.onClickDialogCancel();
        }
        removeFragment();

    }




    public interface Listener{
        void onClickDialogAdd();
        void onClickDialogCancel();
    }
    public static AddCategoryFragment newInstance() {

        Bundle args = new Bundle();
        AddCategoryFragment fragment = new AddCategoryFragment();
     //   fragment.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        fragment.setArguments(args);
        fragment.setCancelable(true);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.snippet_addcategory_dialog, container, false);
        //onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this,view);
        return view;
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try {
            if (mListener == null) {
                mListener = (AddCategoryFragment.Listener) activity;
            }
        } catch (Exception e) {
        }
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    private void removeFragment() {
        getActivity()
                .getSupportFragmentManager()
                .beginTransaction()
                .remove(this)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                .commitAllowingStateLoss();
    }
}
