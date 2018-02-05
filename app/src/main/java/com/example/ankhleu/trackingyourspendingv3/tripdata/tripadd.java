package com.example.ankhleu.trackingyourspendingv3.tripdata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.ankhleu.trackingyourspendingv3.MainActivity;

import java.util.ArrayList;

/**
 * Created by ankhleu on 2018/1/31.
 */

public class tripadd implements tripDAO {

    Context context;
    SQLiteDatabase db;

    public tripadd(MainActivity context)   //新增資烙用
    {
        this.context = context;
        tripDB helper = new tripDB(getactivity, "", context, 1);
        db = helper.getWritableDatabase();
    }

    public boolean add(trip_constructor t)  //增加資料細項
    {
        ContentValues cv = new ContentValues();
        cv.put("_id",t.id);
        cv.put("Title",t.title);
        cv.put("startdate",t.startdate);
        cv.put("enddate",t.enddate);
        cv.put("budget",t.budget);
        db.insert("trip",null,cv);
        return true;
    }
    @Override

    public ArrayList<trip_constructor> getList(){
        ArrayList<trip_constructor> mytrip = new ArrayList<>();
        Cursor c = db.query("trip", new String[]{"_id","Title","startdate","enddate","budget"},null,null,null,null,null);
        if(c.moveToFirst())
        {
            //用於獲取指定欄位名稱的int 型別的值
            trip_constructor t1 = new trip_constructor(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4));
            mytrip.add(t1);
            while (c.moveToFirst())
            {
                trip_constructor t = new trip_constructor(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getInt(4));
                mytrip.add(t);
            }
        }
        return mytrip;
    }

    @Override
    public trip_constructor getTrip(int id) {//透過ID來找
        Cursor c =db.query("trip", new String[]{"_id","Title","startdate","enddate","budget"},"_id=?",new String[]{String.valueOf(id)},null,null,null);
        if(c.moveToFirst())
        {
            trip_constructor t1 = new trip_constructor(c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5));
            return  t1;
        }
        return null;
    }

    @Override
    public boolean update(trip_constructor t) { //更新資料
        ContentValues cv = new ContentValues();
        cv.put("Title",t.title);
        cv.put("startdate",t.startdate);
        cv.put("enddate",t.enddate);
        cv.put("budget",t.budget);
        db.update("trip",cv,"_id=?",new String[]{String.valueOf(t.id)});
        return  true;
    }


    @Override

    public boolean delete(int id)  //照ID刪除資料
    {
        db.delete("trip","_id=?",new String[]{String.valueOf(id)});
        return true;
    }
}
