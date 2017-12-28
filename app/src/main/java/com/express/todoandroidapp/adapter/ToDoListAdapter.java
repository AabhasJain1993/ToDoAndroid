package com.express.todoandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.interfaces.IToDoItemClickListener;
import com.express.todoandroidapp.model.ToDoItemCategory;
import com.express.todoandroidapp.views.ToDoListHolder;

import java.util.List;

/**
 * Created by Aabhas_Jain on 12/27/2017.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListHolder>  {


    public static final String TAG= "SONG LIST ADAPTER";
    private List<ToDoItemCategory> mItemsList;
    private IToDoItemClickListener mToDoItemClickListener = null;

    public ToDoListAdapter(IToDoItemClickListener iToDoItemClickListener, List<ToDoItemCategory> list) {
        mToDoItemClickListener = iToDoItemClickListener;
        mItemsList = list;
    }

    @Override
    public ToDoListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snippet_todo_card, parent, false);
        return new ToDoListHolder(itemView, mToDoItemClickListener);
    }

    @Override
    public void onBindViewHolder(ToDoListHolder holder, int position) {
        ToDoItemCategory item = mItemsList.get(position);
        holder.bind(item.getCategoryTitle(), item.getToDoItemsListCount());
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }
}
