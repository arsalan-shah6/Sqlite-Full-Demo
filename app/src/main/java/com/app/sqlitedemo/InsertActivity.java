package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sqlitedemo.Database.DBHelper;
import com.app.sqlitedemo.Model.Model;

public class InsertActivity extends AppCompatActivity {
 EditText names,courses,contacts,total_fees,fee_paids;
 Button save,showAll;
 DBHelper dbHelper;
 ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_insert );
        names=findViewById( R.id.name );
        courses=findViewById( R.id.course );
        contacts=findViewById( R.id.contact );
        total_fees=findViewById( R.id.total_fee );
        fee_paids=findViewById( R.id.fee_paid );
        save=findViewById( R.id.save );
        dbHelper=new DBHelper( this );
        progressDialog=new ProgressDialog( this );
        progressDialog.setMessage( "Please Wait" );
        showAll=findViewById( R.id.retrieve );
        showAll.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(InsertActivity.this,RetrieveDataActivity.class) );
            }
        } );
        save.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 String Name=names.getText().toString();
                 String Course=courses.getText().toString();
                 String Contact=contacts.getText().toString();
                 String TotalFee=total_fees.getText().toString();
                 String FeePaid=fee_paids.getText().toString();
                 if (Name.isEmpty()){
                     names.setError( "Enter Your Name" );
                 }else if (Course.isEmpty()){
                     courses.setError( "Enter COurse" );
                 }else if (Contact.isEmpty()){
                     contacts.setError( "Enter Your Contact" );
                 }else if (TotalFee.isEmpty()){
                     total_fees.setError( "Enter Total Fee" );
                 }else if (FeePaid.isEmpty()){
                     fee_paids.setError( "Enter Paid Fee" );
                 }else {
                     InsertData(Name,Course,Contact,TotalFee,FeePaid);
                 }


            }
        } );

    }

    private void InsertData(String name, String course, String contact, String totalFee, String feePaid) {
        int t=Integer.parseInt( totalFee );
        int p=Integer.parseInt( feePaid );
        Model student=new Model( name,course,contact,feePaid,totalFee );
        long result=dbHelper.addStudent(student);

        if (result==-1){

            Toast.makeText( InsertActivity.this, "Failed", Toast.LENGTH_SHORT ).show();
        }else {

            Toast.makeText( InsertActivity.this, "Student data stored"+result, Toast.LENGTH_SHORT ).show();
            names.setText( "" );
            courses.setText( "" );
            contacts.setText( "" );
            fee_paids.setText( "" );
            total_fees.setText( "" );
        }
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
        super.onBackPressed();
    }
}