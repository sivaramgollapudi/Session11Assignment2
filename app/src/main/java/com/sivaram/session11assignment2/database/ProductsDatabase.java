package com.sivaram.session11assignment2.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sivaram.session11assignment2.utils.Constants;

/**
 * Created by User on 13/10/2017.
 */

public class ProductsDatabase extends SQLiteOpenHelper {

    Context context;
    String createTableSQL = Constants.CREATE_TABLE + " " + Constants.TABLE_NAME + " ( " +
                            Constants.PRODUCTNAME + " " + Constants.TEXT_DATA_TYPE + " ) ";

    public ProductsDatabase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTableSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        context.deleteDatabase(Constants.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
