package com.anjukakoralage.layoutindexdemo.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import com.anjukakoralage.layoutindexdemo.model.UserDetails;

/**
 * Created by anjukakoralage on 28,September,2019
 */
public class UserDataBase extends SQLiteOpenHelper {

    private static final String TAG = "UserDataBase";


    public UserDataBase(@Nullable Context context) {
        super(context, Constant.DataBase.DB_NAME, null, Constant.DataBase.DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {

            db.execSQL(Constant.DataBase.CREATE_TABLE_QUERY);

        }catch (SQLException e ){
            Log.e(TAG, "onCreate: ", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(Constant.DataBase.DROP_QUERY);
        this.onCreate(db);

    }

    public void addUserData(UserDetails userDetails){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        /*values.put(Constant.DataBase);
        values.put();
        values.put();
        values.put();
        values.put();*/

    }
}
