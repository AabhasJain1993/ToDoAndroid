package com.express.todoandroidapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.express.todoandroidapp.R;
import com.express.todoandroidapp.interfaces.IToDoItemClickListener;

/**
 * Created by Aabhas_Jain on 12/27/2017.
 */

public class ToDoListHolder extends RecyclerView.ViewHolder  implements View.OnClickListener {

    TextView mToDoItemName, mToDoItemCount;
    Context mContext;
    IToDoItemClickListener mItemClickListener = null;

    public ToDoListHolder(View view, IToDoItemClickListener iToDoItemClickListener) {
        super(view);
        mToDoItemName = (TextView) view.findViewById(R.id.todoListItemValue);
        mToDoItemCount = (TextView)view.findViewById(R.id.todoListItemCount);

        mContext = view.getContext();
        itemView.setOnClickListener(this);
        mItemClickListener = iToDoItemClickListener;
    }

    public void bind(String itemName, int itemCount) {
        mToDoItemName.setText(itemName);
        mToDoItemCount.setText(itemCount);
    }

    @Override
    public void onClick(View v) {
        Log.d("clicked","clicked");
        int position = getAdapterPosition();
        if(mItemClickListener !=null)
            mItemClickListener.onItemClick(position);
        else
            Toast.makeText(mContext, "Null click Listener", Toast.LENGTH_LONG).show();
    }
}
