package com.express.todoandroidapp.views;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.express.todoandroidapp.R;
import com.express.todoandroidapp.activities.MainActivity;
import com.express.todoandroidapp.adapter.ToDoListAdapter;
import com.express.todoandroidapp.db.DatabaseManager;
import com.express.todoandroidapp.db.QueryExecutor;
import com.express.todoandroidapp.interfaces.IToDoItemClickListener;
import com.express.todoandroidapp.model.ToDoItemCategory;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by root on 16/12/17.
 */

public class ToDoListView extends RelativeLayout implements IToDoItemClickListener{

    @Bind(R.id.todoList)
    RecyclerView mTodoList;

    private Listener mListener = null;

    ToDoListView mToDoListView;
    private Context mContext;
    private ToDoListAdapter mToDoListAdapter = null;
    private List<ToDoItemCategory> mCategoryItemsList;


    public interface Listener {
        void onCategoryItemClicked(ToDoItemCategory category);
    }


    public ToDoListView(Context context) {
        this(context,null);
    }

    public ToDoListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView(context);
    }

    void initView(Context context) {
        inflate(context, R.layout.snippet_todo_listview, this);
        ButterKnife.bind(this);

        mToDoListView = this;
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mTodoList.setLayoutManager(linearLayoutManager);
        getDataFromDatabase();
       // setDataFromAPI();
    }

    void getDataFromDatabase() {

        //TODO get data from database and store in List
        refreshList();
        Log.d("ToDoListview", mCategoryItemsList.toString());
        mToDoListAdapter = new ToDoListAdapter(mToDoListView, mCategoryItemsList);
        mTodoList.setAdapter(mToDoListAdapter);

    }

    @Override
    public void onItemClick(int position) {
        if(mListener!=null) {
            mListener.onCategoryItemClicked(mCategoryItemsList.get(position));
        }
    }

    public void setListener(Listener listener) {
        mListener = listener;
    }


//    void setDataFromAPI() {
//
//        Call<List<Song>> call =  ApiHelper.getAPInterface().songList();
//
//        call.enqueue(new Callback<List<Song>>() {
//            @Override
//            public void onResponse(Response<List<Song>> response, Retrofit retrofit) {
//                Log.d("Success : ", response.body() + "");
//                mCategoryItemsList = response.body();
//
//                mSongsListAdapter = new SongsListAdapter(mToDoListView, mCategoryItemsList);
//                mTodoList.setAdapter(mSongsListAdapter);
//        }
//
//            @Override
//            public void onFailure(Throwable t) {
//                Log.d("Failure : ", "Failure");
//            }
//        });
//
//    }

//    public ArrayList<String> getAdapter() {
//        ArrayList<String> songsList = new ArrayList<>();
//        for(int i = 0; i< mCategoryItemsList.size(); i++) {
//            songsList.add(mCategoryItemsList.get(i).getSongName());
//        }
//        return songsList;
//    }

    public void refreshList() {
        DatabaseManager manager= DatabaseManager.getInstance();
        SQLiteDatabase db=manager.openDatabase();
        QueryExecutor qe = new QueryExecutor();
        mCategoryItemsList = qe.getAllCategoryItems(db);
        manager.closeDatabase();
        Log.d("refereshing list", mCategoryItemsList.toString());
        if(mToDoListAdapter != null) {

            ((MainActivity)mContext).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Log.d("refreshing list","notifydatasetchanged");
                    mToDoListAdapter = new ToDoListAdapter(mToDoListView, mCategoryItemsList);
                    mTodoList.setAdapter(mToDoListAdapter);
                    }
                });

        }

    }
}
