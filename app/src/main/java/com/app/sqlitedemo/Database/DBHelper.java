package com.app.sqlitedemo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.app.sqlitedemo.Model.Model;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper( Context context) {
        super( context, "Student", null, 1 );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL( "CREATE TABLE tb1_student( id INTEGER PRIMARY KEY AUTOINCREMENT , name TEXT , course TEXT , contact TEXT , total_fee TEXT , fee_paid TEXT)" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addStudent(Model student) {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put( "name",student.getName() );
        values.put("course",student.getCourse());
        values.put("contact",student.getContact());
        values.put("total_fee",student.getTotal_fee());
        values.put( "fee_paid",student.getFee_paid() );

        long r= db.insert( "tb1_student" ,null,values);
        return r;
    }
    public Cursor readAllData(){
        SQLiteDatabase db=getReadableDatabase();
        String Query="select * from tb1_student order by id desc ";
        Cursor cursor=db.rawQuery( Query,null);
        return cursor;
    }
    public Cursor SearchStudent(String search){
        SQLiteDatabase db=getWritableDatabase();
        String Query="select * from tb1_student where name="+""+search ;
        Cursor cursor=db.rawQuery( Query,null );

        return  cursor;
    }
    public  Boolean UpdateData(Model student){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put( "name",student.getName() );
        contentValues.put("course",student.getCourse());
        contentValues.put("contact",student.getContact());
        contentValues.put("total_fee",student.getTotal_fee());
        contentValues.put( "fee_paid",student.getFee_paid() );

          db.update( "tb1_student", contentValues, "name=?" ,new String[]{student.getName()});
                return true;

    }
   public  Integer DeleteRecord(String delete) {
       SQLiteDatabase db = getWritableDatabase();


       int result= db.delete( "tb1_student" ,"name=?",new String[]{delete});
       return result;

   }


    public int deleteItem(String name) {
        SQLiteDatabase db=getWritableDatabase();
        return    db.delete( "tb1_student","name=?",new String[]{name} );

    }
}



