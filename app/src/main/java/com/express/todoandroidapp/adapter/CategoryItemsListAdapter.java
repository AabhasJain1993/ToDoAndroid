package com.express.todoandroidapp.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.interfaces.ICategoryItemClickListener;
import com.express.todoandroidapp.interfaces.IToDoItemClickListener;
import com.express.todoandroidapp.model.ToDoItem;
import com.express.todoandroidapp.model.ToDoItemCategory;
import com.express.todoandroidapp.views.ItemListHolder;
import com.express.todoandroidapp.views.ToDoListHolder;

import java.util.List;

/**
 * Created by root on 28/12/17.
 */

public class CategoryItemsListAdapter extends RecyclerView.Adapter<ItemListHolder>  {

    public static final String TAG= "CATEGORY ITEMS LIST ADAPTER";
    private List<ToDoItem> mItemsList;
    private ICategoryItemClickListener mCategoryItemListener = null;

    public CategoryItemsListAdapter(ICategoryItemClickListener iCategoryItemClickListener, List<ToDoItem> list) {
        mCategoryItemListener = iCategoryItemClickListener;
        mItemsList = list;
    }

    @Override
    public ItemListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.snippet_categoryitem_card, parent, false);
        return new ItemListHolder(itemView, mCategoryItemListener);
    }

    @Override
    public void onBindViewHolder(ItemListHolder holder, int position) {
        ToDoItem item = mItemsList.get(position);
        holder.bind(item.getItemTitle(), item.getIsItemDone());
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }
}
