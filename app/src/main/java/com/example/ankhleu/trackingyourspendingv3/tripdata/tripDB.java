package com.example.ankhleu.trackingyourspendingv3.tripdata;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v4.app.FragmentActivity;

/**
 * Created by ankhleu on 2018/1/31.
 */

public class tripDB extends SQLiteOpenHelper {
    final static  String DB_NAME = "Trip.sqlite"; //資料庫名稱(旅行)
    final static int VERSION = 1 ;
    final static String _Trip = "trip";     // 資料表名稱(活動)
    final static String _Payout = "payout"; // 資料表名稱(活動紀錄細項)

    public  tripDB(FragmentActivity activity, String s, Context context, int i)
    {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE \"trip\" ( `_id` INTEGER NOT NULL PRIMARY KEY , `Title` TEXT, `startdate` TEXT, `enddate` TEXT, `budget` INTEGER )");
        db.execSQL("CREATE TABLE \"tripdetail\" ( `_id` INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, `money` INTEGER, `_date` TEXT, `subject` TEXT, `Currency` TEXT, `note` TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
