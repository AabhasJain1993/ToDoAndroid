package com.express.todoandroidapp.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.siftr.utils.Logger;
import com.siftr.whatsappcleaner.model.CaffeFileAnalyserResult;

import java.util.ArrayList;
import java.util.List;

public class QueryExecutor {

    //private static final String TABLE_KNOWN_FOLDER="knowfolder";
    private static final String TABLE_TO_DO_ITEMS ="todoitems";

    private static final String KEY_ID="id";
    private static final String KEY_CATEGORY="category";
    private static final String KEY_ITEM_NUMBER="number";

    private static final String KEY_TITLE="title";
    private static final String KEY_DESCRIPTION="description";
    private static final String KEY_IF_DONE="isdone";
    private static final String KEY_IMAGE_PATH = "path";

//    public void insertNewKnownFolderValue(SQLiteDatabase db,List<KnownFolder> knownFoldersList){
//        db.beginTransaction();
//        deleteTable(db, TABLE_KNOWN_FOLDER);
//        try{
//            for(KnownFolder knownFolder:knownFoldersList) {
//                ContentValues values = new ContentValues();
//                values.put(KEY_PATH, knownFolder.getPath());
//                values.put(KEY_DISPLAY_NAME, knownFolder.getDisplayName());
//                values.put(KEY_IS_JUNK, knownFolder.isJunk() == true ? 1 : 0);
//                values.put(KEY_IS_SHOW_THUMBNAILS, knownFolder.isShowThumnails() == true ? 1 : 0);
//                db.insert(TABLE_KNOWN_FOLDER, null, values);
//            }
//            db.setTransactionSuccessful();
//        }catch (Exception e){
//
//        }finally {
//            db.endTransaction();
//        }
//    }

    private void deleteTable(SQLiteDatabase db, String tableName){
        try{
            db.execSQL("delete from " + tableName);
        }catch (Exception e){
            Log.i("Delete Error", e.getLocalizedMessage());
        }
    }

//    public List<KnownFolder> getKnowFolderList(SQLiteDatabase db){
//        List<KnownFolder> knownFolderList=new ArrayList<>();
//        String selectQuery="SELECT * from "+TABLE_KNOWN_FOLDER;
//        try{
//            Cursor cursor=db.rawQuery(selectQuery,null);
//            if(cursor.moveToFirst()){
//                do{
//                    KnownFolder knownFolder=new KnownFolder(cursor.getString(1),cursor.getString(2),cursor.getInt(3)==1?true:false,
//                            cursor.getInt(4)==1?true:false);
//                    knownFolderList.add(knownFolder);
//                }while(cursor.moveToNext());
//            }
//            cursor.close();
//        }catch (Exception e) {
//            Logger.i("DB Error "+e.getLocalizedMessage());
//        }
//        return knownFolderList;
//    }

    public boolean insertNewCategory(SQLiteDatabase db, String tableName) {
        List<String> categories=new ArrayList<>();
        String selectQuery="SELECT "+ KEY_CATEGORY+" from "+TABLE_TO_DO_ITEMS;
        try{
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do{
                    categories.add(cursor.getString(0));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        if(categories.contains(tableName.toLowerCase())) {
            return false;
        }

        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_CATEGORY, tableName.toLowerCase());
            db.insert(TABLE_TO_DO_ITEMS, null, contentValues);
            String CREATE_NEW_TABLE="CREATE TABLE "+ tableName.toLowerCase() + "("
                    + KEY_TITLE + " TEXT PRIMARY KEY, "
                    + KEY_DESCRIPTION + " TEXT, "+ KEY_IF_DONE + " BOOLEAN, "+ KEY_IMAGE_PATH + "TEXT )";
            db.execSQL(CREATE_NEW_TABLE);
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }finally {
            db.endTransaction();
        }

        return true;

    }

    public void insertNewItemInCategory(SQLiteDatabase db, String tableName, String title, String description, boolean isCompleted, String imagePath) {
        db.beginTransaction();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put(KEY_TITLE, title.toLowerCase());
            contentValues.put(KEY_DESCRIPTION, description);
            contentValues.put(KEY_IF_DONE, isCompleted);
            contentValues.put(KEY_IMAGE_PATH, imagePath);
            db.insert(tableName.toLowerCase(), null, contentValues);
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }finally {
            db.endTransaction();
        }

    }

    public void editItemInCategory(SQLiteDatabase db, String tableName, String title, String description, boolean isCompleted, String imagePath) {
        String selectQuery="SELECT * from "+tableName.toLowerCase()+ " WHERE " + KEY_TITLE + " = " + title.toLowerCase();
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                caffeFileAnalyserResult.result=cursor.getInt(0);
                caffeFileAnalyserResult.max=cursor.getFloat(1);
            }
            cursor.close();
        }catch(Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return caffeFileAnalyserResult;



    }



    public void insertNewUnanalysedFilePath(SQLiteDatabase db, List<String> unanalysedFilesPath){
        db.beginTransaction();
        deleteTable(db, TABLE_UNANALYSED_PHOTOS);
        try{
            for(String filePath: unanalysedFilesPath) {
                ContentValues values = new ContentValues();
                values.put(KEY_PATH, filePath);
                db.insert(TABLE_UNANALYSED_PHOTOS, null, values);
            }
            db.setTransactionSuccessful();
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }finally {
            db.endTransaction();
        }
    }

    public void deleteUnanalysedDB(SQLiteDatabase db){
        deleteTable(db, TABLE_UNANALYSED_PHOTOS);
    }

    public List<String> getSavedUnanalysedFilePath(SQLiteDatabase db){
        List<String> unAnalysedFiles=new ArrayList<>();
        String selectQuery="SELECT * from "+TABLE_UNANALYSED_PHOTOS;
        try{
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do{
                    unAnalysedFiles.add(cursor.getString(0));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return unAnalysedFiles;
    }

    public CaffeFileAnalyserResult getPhotoTag(SQLiteDatabase db, String filePath){
        CaffeFileAnalyserResult caffeFileAnalyserResult=new CaffeFileAnalyserResult();
        String selectQuery="SELECT "+KEY_PHOTO_TAG_INDEX+", "+KEY_CONFIDENCE+" from "+TABLE_PHOTO_TAG+" where "+KEY_PATH+" = '"+filePath+"'";
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                caffeFileAnalyserResult.result=cursor.getInt(0);
                caffeFileAnalyserResult.max=cursor.getFloat(1);
            }
            cursor.close();
        }catch(Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return caffeFileAnalyserResult;
    }

    public void setPhotoTag(SQLiteDatabase db, String filePath, int tagIndex, float confidence, long time){
        ContentValues values=new ContentValues();
        values.put(KEY_PATH,filePath);
        values.put(KEY_PHOTO_TAG_INDEX,tagIndex);
        values.put(KEY_CONFIDENCE,confidence);
        values.put(KEY_TAG_TIME, time);
        try{
            db.insert(TABLE_PHOTO_TAG, null, values);
        }catch (Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
    }

    public long getLatestTagTime(SQLiteDatabase db){
        long time=0;
        String selectQuery="SELECT "+KEY_TAG_TIME+" from "+TABLE_PHOTO_TAG +" order by "+KEY_TAG_TIME+" desc limit 1";
        try{
            Cursor cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                time=cursor.getLong(0);
            }
            cursor.close();
        }catch(Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return time;
    }

    public void addFaceVector(SQLiteDatabase db, String path, String vector){
        ContentValues values=new ContentValues();
        values.put(KEY_PATH,path);
        values.put(KEY_FACE_VECTOR, vector);
        try{
            db.insert(TABLE_FACE_VECTOR, null, values);
        }catch (Exception e){
            Log.i("DB Error ",e.getLocalizedMessage());
        }

    }

    public void deleteFaceVector(SQLiteDatabase db, String path){
        try{
            db.execSQL("delete from "+ TABLE_FACE_VECTOR+" where "+KEY_PATH+" = '"+path+"'");
        }catch (Exception e){
            Log.i("DB Error ",e.getLocalizedMessage());
        }
    }

    public List<String> getAllSavedFaceVector(SQLiteDatabase db){
        List<String> savedPaths=new ArrayList<>();
        String selectQuery="SELECT "+KEY_PATH+" from "+TABLE_FACE_VECTOR;
        Cursor cursor=null;
        try{
            cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                do{
                    savedPaths.add(cursor.getString(0));
                }while(cursor.moveToNext());
            }
            cursor.close();
        }catch(Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return savedPaths;
    }

    public String getFaceVectorValue(SQLiteDatabase db, String path){
        String selectQuery="SELECT "+KEY_FACE_VECTOR+" from "+TABLE_FACE_VECTOR+ " where "+KEY_PATH+" = '"+path+"'";
        Cursor cursor=null;
        String value="0";
        try{
            cursor=db.rawQuery(selectQuery,null);
            if(cursor.moveToFirst()){
                value= cursor.getString(0);
            }
            cursor.close();
        }catch(Exception e){
            Log.i("DB Error ", e.getLocalizedMessage());
        }
        return value;
    }
}