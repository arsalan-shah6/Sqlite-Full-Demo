package com.app.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.sqlitedemo.Database.DBHelper;
import com.app.sqlitedemo.Model.Model;

public class UpdateActivity extends AppCompatActivity {
    EditText names,courses,contacts,total_fees,fee_paids;
    Button update;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_update );
        names=findViewById( R.id.name );
        courses=findViewById( R.id.course );
        contacts=findViewById( R.id.contact );
        total_fees=findViewById( R.id.total_fee );
        fee_paids=findViewById( R.id.fee_paid );
        update=findViewById( R.id.update );
        dbHelper=new DBHelper( this );
        update.setOnClickListener( new View.OnClickListener() {
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
                    UpdateRecord(Name,Course,Contact,TotalFee,FeePaid);
                }
            }
        } );
    }

    private void UpdateRecord(String name, String course, String contact, String totalFee, String feePaid) {
        Model student=new Model( name,course,contact,totalFee,feePaid );
        Boolean IsUpdate=dbHelper.UpdateData( student );
        if (IsUpdate==true){
            Toast.makeText( this, "Update Record", Toast.LENGTH_SHORT ).show();
            names.setText( "" );
            courses.setText( "" );
            contacts.setText( "" );
            fee_paids.setText( "" );
            total_fees.setText( "" );
        }else {
            Toast.makeText( this, "Failed Update", Toast.LENGTH_SHORT ).show();
        }
    }
}