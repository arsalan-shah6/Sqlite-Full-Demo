package com.app.sqlitedemo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.app.sqlitedemo.Database.DBHelper;
import com.app.sqlitedemo.HomeActivity;
import com.app.sqlitedemo.Model.RetrieveDataModel;
import com.app.sqlitedemo.R;
import com.app.sqlitedemo.RetrieveDataActivity;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.ID;

public class RetrieveDataAdapter extends RecyclerView.Adapter<RetrieveDataAdapter.holder> implements Filterable {
    List<RetrieveDataModel> dataHolder;
    List<RetrieveDataModel> dataHolderFilter;

    public RetrieveDataAdapter(List<RetrieveDataModel> dataHolder) {
        this.dataHolder = dataHolder;
        this.dataHolderFilter=dataHolder;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from( parent.getContext() ).inflate( R.layout.row_design ,parent,false);
        return new holder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {

        holder.name.setText( dataHolderFilter.get( position ).getName() );
        holder.course.setText( dataHolderFilter.get( position ).getCourse() );
        holder.contact.setText( dataHolderFilter.get( position ).getContact() );
        holder.total_fee.setText( dataHolderFilter.get( position ).getTotal_fee() );
        holder.fee_paid.setText( dataHolderFilter.get( position ).getFee_paid() );

        holder.t1_delete.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               DBHelper dbHelper=new DBHelper(view.getContext() );
               String pos=dataHolder.get( position ).getName();
              int a= dbHelper.deleteItem(pos);
              if (a>0){
                  Toast.makeText( view.getContext(), "delete", Toast.LENGTH_SHORT ).show();

                 dataHolderFilter.remove( position );
                 notifyDataSetChanged();

              }else {
                  Toast.makeText( view.getContext(), "faided", Toast.LENGTH_SHORT ).show();
              }
               

            }
        } );

    }

    @Override
    public int getItemCount()
    {
        return  dataHolderFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String Character=charSequence.toString();
                if (Character.isEmpty()){
                    dataHolderFilter=dataHolder;
                }else {
                    List<RetrieveDataModel>filterlist=new ArrayList<>();
                    for (RetrieveDataModel row:dataHolder){
                        if (row.getName().toLowerCase().contains( Character.toLowerCase() )){
                            filterlist.add(row);
                        }
                    }
                    dataHolderFilter=filterlist;
                }
                FilterResults filterResults=new FilterResults();
                filterResults.values=dataHolderFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                 dataHolderFilter=(ArrayList<RetrieveDataModel>) filterResults.values;
                 notifyDataSetChanged();

            }
        };

    }

    class holder extends RecyclerView.ViewHolder {
        TextView name,course,contact,total_fee,fee_paid,t1_delete;
        Button t2_delete;
        public holder(@NonNull View itemView) {
            super( itemView );
            name=itemView.findViewById( R.id.name );
            course=itemView.findViewById( R.id.course );
            contact=itemView.findViewById( R.id.contact );
            total_fee=itemView.findViewById( R.id.total_fee );
            fee_paid=itemView.findViewById( R.id.fee_paid );
            t1_delete=itemView.findViewById( R.id.delete );



    }
}


}
