package com.example.keeprawteach.csi.Patients;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.Model.Charge;
import com.example.keeprawteach.csi.Model.Upload;
import com.example.keeprawteach.csi.Payment.Payment;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Payments extends AppCompatActivity {


    ArrayList<Charge> arrayList;

    AdapterClass adapterClass;

    FirebaseDatabase database;

    DatabaseReference reference;

    ListView listView;

    String namehere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments);

        namehere = getIntent().getStringExtra("key");

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

                    String outdoor = (String) messageSnapshot.child("outdoor").getValue();

                    String investigation = (String) messageSnapshot.child("investigation").getValue();

                    String pharmacy = (String) messageSnapshot.child("pharmacy").getValue();

                    String admission = (String) messageSnapshot.child("admission").getValue();

                    String discharge = (String) messageSnapshot.child("discharge").getValue();

                    String extra = (String) messageSnapshot.child("extra").getValue();

                    String id = (String) messageSnapshot.child("id").getValue();

                    if (name.equalsIgnoreCase(namehere)) {

                        upload.setNamehere(name);

                        upload.setTotal("" + total);

                        upload.setTime(time);

                        upload.setTime(time);

                        upload.setStatus(status);

                        upload.setOutdoor(outdoor);

                        upload.setInvestigation(investigation);

                        upload.setPharmacy(pharmacy);

                        upload.setAdmission(admission);

                        upload.setDischarge(discharge);

                        upload.setExtra(extra);

                        upload.setId(id);

                        arrayList.add(upload);
                    }

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


                Toast.makeText(Payments.this, "Error..!!" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private class AdapterClass extends ArrayAdapter {

        ArrayList<Charge> status;


        public AdapterClass(Context context, ArrayList<Charge> status) {

            super(context, R.layout.charges, R.id.bb, status);

            this.status = status;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.charges, parent, false);

            TextView textView = (TextView) view.findViewById(R.id.bb);

            Charge upload = status.get(position);

            textView.setText("Name:\n" + upload.getNamehere()
                    + "\nTransaction Id:\n" + upload.getId()
                    + "\nOutdoor:\n" + upload.getOutdoor()
                    + "\nInvestigation Charges:\n"
                    + upload.getInvestigation() + "\nPharmacy Bill:\n"
                    + upload.getPharmacy() + "\nAdmission charges:\n"
                    + upload.getAdmission() + "\nDischarge Amount:\n"
                    + upload.getDischarge() + "\nExtra Charges:\n"
                    + upload.getDischarge() + "\nTotal Charges:\n"
                    + upload.getTotal() + "\nStatus:\n" + upload.getStatus() +
                    "\nDate:\n" + upload.getTime());

            final String total = upload.getTotal();

            final String id=upload.getId();

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clearbill(total,id);
                }
            });

            return view;
        }
    }

    private void clearbill(String total, String id) {

        Intent intent = new Intent(Payments.this, ClearBill.class);

        Bundle bundle = new Bundle();

        bundle.putString("total", total);

        bundle.putString("id", id);

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
