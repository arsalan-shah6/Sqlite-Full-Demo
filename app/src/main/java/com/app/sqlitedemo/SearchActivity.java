package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sqlitedemo.Database.DBHelper;

public class SearchActivity extends AppCompatActivity {
 EditText search_bar;
 Button search_btn;
    TextView name,course,contact,total_fee,fee_paid;
    DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        search_bar=findViewById( R.id.search_bar );
        search_btn=findViewById( R.id.search_btn );

        name=findViewById( R.id.name );
        course=findViewById( R.id.course );
        contact=findViewById( R.id.contact );
        total_fee=findViewById( R.id.total_fee );
        fee_paid=findViewById( R.id.fee_paid );


        search_btn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbHelper=new DBHelper( SearchActivity.this );
               Cursor result= dbHelper.SearchStudent( search_bar.getText().toString() );

                while (result.moveToNext()){

                    course.setText( result.getString( 2 ) );
                    course.setText( result.getString( 3 ) );
                    total_fee.setText( result.getString( 4 ) );
                    fee_paid.setText( result.getString( 5 ) );


                }

            }
        } );

    }
}