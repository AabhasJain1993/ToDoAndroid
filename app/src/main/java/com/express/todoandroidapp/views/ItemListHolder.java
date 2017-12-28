package com.express.todoandroidapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.interfaces.ICategoryItemClickListener;
import com.express.todoandroidapp.interfaces.IToDoItemClickListener;

/**
 * Created by root on 28/12/17.
 */

public class ItemListHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{

    TextView mToDoTextViewTitle, mToDoTextValue;
    Context mContext;
    ICategoryItemClickListener mListener = null;

    public ItemListHolder(View view, ICategoryItemClickListener iCategoryItemClickListener) {
        super(view);
        mToDoTextViewTitle = (TextView) view.findViewById(R.id.todoCategoryItem);
        mToDoTextValue = (TextView)view.findViewById(R.id.todoCategoryValue);

        mContext = view.getContext();
        itemView.setOnClickListener(this);
        mListener = iCategoryItemClickListener;
    }

    public void bind(String itemName, boolean itemValue) {
        mToDoTextViewTitle.setText(itemName);
        if(itemValue == false) {
            mToDoTextValue.setText("Pending");
        } else {
            mToDoTextValue.setText("Done");
        }
    }


    @Override
    public void onClick(View view) {
        if(mListener!=null) {
            mListener.onCategoryItemClick(getAdapterPosition());
        }

    }
}
