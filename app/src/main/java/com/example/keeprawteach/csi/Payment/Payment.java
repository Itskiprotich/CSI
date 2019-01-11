package com.example.keeprawteach.csi.Payment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.Model.Charge;
import com.example.keeprawteach.csi.Model.Upload;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class Payment extends AppCompatActivity {

    ArrayList<Charge> arrayList;

    AdapterClass adapterClass;

    FirebaseDatabase database;

    DatabaseReference reference;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        arrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);

        loadData();
    }

    private void loadData() {
        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Payment");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    Charge upload = new Charge();

                    String name = (String) messageSnapshot.child("namehere").getValue();

                    String total = (String) messageSnapshot.child("total").getValue();

                    String time = (String) messageSnapshot.child("time").getValue();

                    String status = (String) messageSnapshot.child("status").getValue();

                    upload.setNamehere(name);

                    upload.setTotal(""+total);

                    upload.setTime(time);

                    upload.setTime(time);

                    upload.setStatus(status);

                    arrayList.add(upload);

                }

                String[] uploads = new String[arrayList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = arrayList.get(i).getNamehere();
                }

                adapterClass = new AdapterClass(getApplicationContext(), arrayList);

                listView.setAdapter(adapterClass);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {


                Toast.makeText(Payment.this, "Error..!!" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }
    private class AdapterClass extends ArrayAdapter {

        ArrayList<Charge> status;


        public AdapterClass(Context context, ArrayList<Charge> status) {

            super(context, R.layout.doctors, R.id.bb, status);

            this.status = status;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.doctors, parent, false);

            TextView textView = (TextView) view.findViewById(R.id.bb);

            Charge upload = status.get(position);

            textView.setText("Name:" + upload.getNamehere() + "\nTotal Amount:" + upload.getTotal() + "\nDate:" + upload.getTime()+"\nStatus:"+upload.getStatus());

            return view;
        }
    }
}
