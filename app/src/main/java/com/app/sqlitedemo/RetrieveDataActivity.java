package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.app.sqlitedemo.Adapter.RetrieveDataAdapter;
import com.app.sqlitedemo.Database.DBHelper;
import com.app.sqlitedemo.Model.RetrieveDataModel;

import java.util.ArrayList;

public class RetrieveDataActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<RetrieveDataModel> dataModels;
    RetrieveDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_retrieve_data );
        recyclerView=findViewById(R.id.recycler_view );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        dataModels=new ArrayList<>();
        Cursor cursor=new DBHelper( this ).readAllData();
        while (cursor.moveToNext()){
            RetrieveDataModel retrieveDataModel=new RetrieveDataModel( cursor.getString( 1 ),cursor.getString( 2 ),cursor.getString( 3 ) ,cursor.getString( 4 ),cursor.getString( 5 ));
          dataModels.add( retrieveDataModel );
        }
        adapter=new RetrieveDataAdapter( dataModels);
        recyclerView.setAdapter(  adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate( R.menu.main_menu,menu );
        MenuItem item=menu.findItem( R.id.search );
        SearchView searchView=(SearchView)item.getActionView();
        searchView.setMaxWidth( Integer.MAX_VALUE );
        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                adapter.getFilter().filter( s );

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter( s );
                return false;
            }
        } );
        return super.onCreateOptionsMenu( menu );
    }

    @Override
    public void onBackPressed() {
        startActivity( new Intent(RetrieveDataActivity.this,HomeActivity.class) );
        super.onBackPressed();
    }
}