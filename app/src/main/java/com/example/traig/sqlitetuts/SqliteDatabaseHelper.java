package com.example.traig.sqlitetuts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;
/**
 * Created by traig on 1/15/2018.
 */

public class SqliteDatabaseHelper extends SQLiteOpenHelper {
    private static final String database_name = "favourit";
    private static final int database_version = 1;
    private static final String Tab = "ACCOUNT";
    private String column_id = "id",column_content ="content",column_pos ="pos";
//    private  String data_statement =
//            "create table" + database_name +"( " + column_id + " integer primary key autoincrement,"
//            +column_content + "text not null" + column_pos +"text not null";
    public SqliteDatabaseHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
          String data_statement =
                "create table " + Tab +"( " + column_id + " INTEGER PRIMARY KEY,"
                        +column_content + " TEXT," + column_pos + " TEXT" + ")";
        sqLiteDatabase.execSQL(data_statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // Drop older table if existed
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + Tab);
            //create again
            onCreate(sqLiteDatabase);
    }

    void addFavorit(favouritItem favouritItem1){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_content,favouritItem1.get_Content());
        contentValues.put(column_pos,favouritItem1.get_pos());
        Log.d(TAG, "addFavorit: ");
        //inserting row
        sqLiteDatabase.insert(Tab,null,contentValues);
        sqLiteDatabase.close();//closing database connection

    }

     favouritItem getData(int id){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(Tab,/*column name */ new String[] {column_id,column_content,column_pos},column_id +
        "=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor != null)
            cursor.moveToFirst();
        favouritItem favouritItem1 = new favouritItem(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                cursor.getString(2));
        cursor.close();
        sqLiteDatabase.close();
         Log.d(TAG, "getData: ");
        return favouritItem1;
    }

    //updating single data

    int updateData(favouritItem favouritItem1){
         SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_content,favouritItem1.get_Content());
        contentValues.put(column_pos,favouritItem1.get_pos());
        return sqLiteDatabase.update(Tab,contentValues,column_id + " = ?",
                new String[] { String.valueOf(favouritItem1.get_id()) });
    }

    //get all data
    List<favouritItem> getAllData(){
        List<favouritItem> dataList = new ArrayList<>();
        //selection all query
        String SelectionQuery = "SELECT * FROM" + Tab;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(SelectionQuery,null);
        //looping through all rows and add to list
        if(cursor.moveToFirst()){
            do {
                favouritItem favouritItem1 = new favouritItem(Integer.parseInt(cursor.getString(0)),cursor.getString(1),
                        cursor.getString(2));
                dataList.add(favouritItem1);

            }while (cursor.moveToNext());
        }//end if

        //return
        return  dataList;
    }

    //delete data
    void deleteData(favouritItem favouritItem1){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete(Tab,column_id + " = ?",
                new String[] { String.valueOf(favouritItem1.get_id()) });
        sqLiteDatabase.close();
    }
    int getCount(){
        String countQuery = "SELECT * FROM" +Tab;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(countQuery,null);
        cursor.close();

        //return
        return cursor.getCount();
    }
}
