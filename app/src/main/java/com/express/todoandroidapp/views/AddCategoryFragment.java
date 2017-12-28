package com.express.todoandroidapp.views;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.express.todoandroidapp.R;

/**
 * Created by root on 28/12/17.
 */

public class AddCategoryFragment extends DialogFragment {

    public static AddCategoryFragment newInstance() {

        Bundle args = new Bundle();
        AddCategoryFragment fragment = new AddCategoryFragment();
        fragment.getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        inflater.inflate(R.layout.snippet_addcategory_dialog, container, false);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

}
