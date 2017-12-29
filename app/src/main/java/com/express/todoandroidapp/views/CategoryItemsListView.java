package com.express.todoandroidapp.views;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.adapter.CategoryItemsListAdapter;
import com.express.todoandroidapp.db.DatabaseManager;
import com.express.todoandroidapp.db.QueryExecutor;
import com.express.todoandroidapp.interfaces.ICategoryItemClickListener;
import com.express.todoandroidapp.model.ToDoItem;
import com.express.todoandroidapp.model.ToDoItemCategory;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 28/12/17.
 */

public class CategoryItemsListView extends RelativeLayout implements ICategoryItemClickListener {


    @Bind(R.id.todoCategoryItemListRecycler)
    RecyclerView mTodoList;

    private Listener mListener = null;

    CategoryItemsListView mCategoryItemsListView = null;

    private CategoryItemsListAdapter mCategoryItemListAdapter;
    private List<ToDoItem> mToDoCategoryItemsList;
    private String mCategoryName;

    public interface Listener {
        void onItemClicked(ToDoItemCategory category);
    }

    public CategoryItemsListView(Context context) {
        this(context ,null);
    }

    public CategoryItemsListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initview(context);
    }

    void initview(Context context) {
        inflate(context, R.layout.snippet_categoryitems_list, this);
        ButterKnife.bind(this);

        mCategoryItemsListView = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTodoList.setLayoutManager(linearLayoutManager);
        getDataFromDatabase();
        // setDataFromAPI();
    }


    void getDataFromDatabase() {

        //TODO get data from database and store in List
        refreshList();
        mCategoryItemListAdapter = new CategoryItemsListAdapter(this, mToDoCategoryItemsList);
        mTodoList.setAdapter(mCategoryItemListAdapter);

    }

    private void refreshList() {

        DatabaseManager manager= DatabaseManager.getInstance();
        SQLiteDatabase db=manager.openDatabase();
        QueryExecutor qe = new QueryExecutor();
        mToDoCategoryItemsList = qe.getAllCategoryItemsCategoryWise(db, mCategoryName);
        manager.closeDatabase();
        Log.d("refereshing list", mToDoCategoryItemsList.toString());
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }

    @Override
    public void onCategoryItemClick(int position) {

    }

    public void setCategoryName(String categoryName) {
        mCategoryName = categoryName;
    }
}
