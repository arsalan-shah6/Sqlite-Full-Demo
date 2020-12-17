package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sqlitedemo.Database.DBHelper;

public class DeleteActivity extends AppCompatActivity {
 EditText deletes;
 Button deleteBtn;
 DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_delete );
        deletes=findViewById( R.id.delete );
        deleteBtn=findViewById( R.id.delete_btn );

        dbHelper=new DBHelper(  this);
        deleteBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Delete=deletes.getText().toString();
                if (Delete.isEmpty()){
                    deletes.setError( "Enter Name" );
                }else {
                    DeleteRecord( Delete );
                }
            }
        } );
    }

    private void DeleteRecord(String delete) {

        Integer deleterow=dbHelper.DeleteRecord(delete);
        if (deleterow>0){
            Toast.makeText( this, "Delete Record", Toast.LENGTH_SHORT ).show();
            deletes.setText( "" );
            
        }else {
            Toast.makeText( this, "Data not delete", Toast.LENGTH_SHORT ).show();
        }


    }


}