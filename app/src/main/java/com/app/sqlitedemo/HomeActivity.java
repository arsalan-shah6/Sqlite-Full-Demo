package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {
Button insert,delete;
Button fetch_data,update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );
        insert=findViewById( R.id.insert );
        fetch_data=findViewById( R.id.fetch );

        update=findViewById( R.id.update );
        delete=findViewById( R.id.delete );

        delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeActivity.this,DeleteActivity.class) );
            }
        } );

        update.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeActivity.this,UpdateActivity.class) );

            }
        } );






        fetch_data.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeActivity.this,RetrieveDataActivity.class) );

            }
        } );

        insert.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(HomeActivity.this,InsertActivity.class ));
            }
        } );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}