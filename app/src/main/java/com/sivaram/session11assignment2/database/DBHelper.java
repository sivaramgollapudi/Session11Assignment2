package com.sivaram.session11assignment2.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.widget.Toast;

import com.sivaram.session11assignment2.utils.Constants;

/**
 * Created by User on 13/10/2017.
 */

public class DBHelper {

    private SQLiteDatabase db;
    private final Context context;
    private final ProductsDatabase productsDatabase;
    private static DBHelper db_helper;

    // Default Constructor to create database
    public DBHelper(Context context) {
        this.context = context;
        productsDatabase = new ProductsDatabase(context, Constants.DATABASE_NAME,null,Constants.DATABASE_VERSION);

    }

    // Check whether the instance of DBHelper object exists or not. if not exists create
    public static  DBHelper getInstance(Context context){
        try{
            if(db_helper == null){
                db_helper = new DBHelper(context);
                db_helper.open();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  db_helper;
    }

    // Open Connection with Database.
    public void open() throws SQLiteException {
        try{
            db = productsDatabase.getWritableDatabase();
        }catch (Exception e){
            e.printStackTrace();
            db= productsDatabase.getReadableDatabase();
        }
    }

    // CLose Database Connection.
    public void close(){
        if(db.isOpen()){
            db.close();
        }
    }

    public long insertProducts(String tableName, ContentValues contentValues){
        long id = 0 ;
        try{
            db.beginTransaction();
            id = db.insert(tableName, null, contentValues);
            db.setTransactionSuccessful();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally {
            db.endTransaction();
        }
        return id;
    }

    // Read Records from Respective Table
    public Cursor getProductsList(String tableName, String[] columns, String where, String orderby){
        Cursor c = db.query(false,tableName, columns, where, null,null,null, orderby, null);
        if (db == null) Toast.makeText(context, "DB IS NULL", Toast.LENGTH_SHORT).show();
        return c;
    }

    public void deleteAllProducts(String tableName, String where, String[] whereArgs){
        try{
            db.beginTransaction();
            db.delete(tableName,where,whereArgs);
            db.setTransactionSuccessful();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            db.endTransaction();
        }
    }
}
