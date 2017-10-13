package com.sivaram.session11assignment2.utils;

import android.content.Context;

import com.sivaram.session11assignment2.database.DBHelper;

/**
 * Created by User on 13/10/2017.
 */

public class CommonUtilities {

    // A Singleton Class Creates Object if not created
    public static DBHelper getDBObject(Context context){
        DBHelper dbHelper = DBHelper.getInstance(context);
        return dbHelper;
    }
}
