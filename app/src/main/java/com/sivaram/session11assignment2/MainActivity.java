package com.sivaram.session11assignment2;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.sivaram.session11assignment2.database.DBHelper;
import com.sivaram.session11assignment2.utils.CommonUtilities;
import com.sivaram.session11assignment2.utils.Constants;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> productNamesList = new ArrayList<String>();
    AutoCompleteTextView productsAutoCompleteTextView;
    DBHelper dbHelper;
    String[] columnNames = new String[]{
            Constants.PRODUCTNAME
    };

    String[] productsList = new String[] {
       "Alliteration","Oxymoron","Combination","Tautology","Theronym","Mimetics","Eponym","Description", "Synecdoche",
       "Poetics","Metonymy","Allusion","Haplology", "Clipping","Morphological borrowing","Omission","Acronym adaptation"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productsAutoCompleteTextView = (AutoCompleteTextView) findViewById(R.id.productsAutoCompleteTextView);


        dbHelper = CommonUtilities.getDBObject(this);

        deleteAllProducts();
        createProductsList();
        showAllProducts();

        ArrayAdapter<String> productNamesAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line, productNamesList);
        productsAutoCompleteTextView.setAdapter(productNamesAdapter);


    }

    private void deleteAllProducts(){
        dbHelper.deleteAllProducts(Constants.TABLE_NAME,"",null);
    }
    private void createProductsList(){
        for (String productName : productsList) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(Constants.PRODUCTNAME,productName);
            dbHelper.insertProducts(Constants.TABLE_NAME,contentValues);
        }
    }

    private void showAllProducts(){
        Cursor c = dbHelper.getProductsList(Constants.TABLE_NAME,columnNames,null,null);

        c.moveToFirst();
        if (c!= null && c.getCount() >0){
            do{
                String productName = c.getString(c.getColumnIndex(Constants.PRODUCTNAME));

                Toast.makeText(this, productName, Toast.LENGTH_SHORT).show();
                productNamesList.add(productName);
            }while(c.moveToNext());
        }
    }
}
