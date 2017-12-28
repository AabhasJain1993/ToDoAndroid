package com.express.todoandroidapp.views;

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

    public ItemListHolder(View view, ICategoryItemClickListener iCategoryItemClickListener) {
        super(view);
        mToDoItemName = (TextView) view.findViewById(R.id.todoListItemValue);
        mToDoItemCount = (TextView)view.findViewById(R.id.todoListItemCount);

        mContext = view.getContext();
        itemView.setOnClickListener(this);
        mItemClickListener = iToDoItemClickListener;
    }

    public ItemListHolder(View itemView) {
        super(itemView);
    }

    public void bind(String itemName, boolean itemCount) {
        mToDoItemName.setText(itemName);
        mToDoItemCount.setText(itemCount);
    }


    @Override
    public void onClick(View view) {

    }
}
