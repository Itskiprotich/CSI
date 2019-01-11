package com.example.keeprawteach.csi.Patients;

import android.content.Context;
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

import com.example.keeprawteach.csi.Model.Pres;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Prescription extends AppCompatActivity {

    private ListView listView;

    ArrayList<Pres> arrayList;

    AdapterClass adapterClass;

    FirebaseDatabase database;

    DatabaseReference reference;

    String namehere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription);

        namehere = getIntent().getStringExtra("key");

        arrayList=new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);

        addData();
    }
    private void addData() {

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Prescription");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    Pres upload=new Pres();

                    String patient = (String) messageSnapshot.child("patient").getValue();

                    String doctor = (String) messageSnapshot.child("doctor").getValue();

                    String complain = (String) messageSnapshot.child("complain").getValue();

                    String history = (String) messageSnapshot.child("history").getValue();

                    String prescription = (String) messageSnapshot.child("prescription").getValue();

                    String advice = (String) messageSnapshot.child("advice").getValue();

                    String investigation = (String) messageSnapshot.child("investigation").getValue();

                    String time = (String) messageSnapshot.child("time").getValue();

                    if (namehere.equalsIgnoreCase(patient)) {

                        upload.setPatient(patient);

                        upload.setDoctor(doctor);

                        upload.setComplain(complain);

                        upload.setHistory(history);

                        upload.setPrescription(prescription);

                        upload.setAdvice(advice);

                        upload.setInvestigation(investigation);

                        upload.setTime(time);

                        arrayList.add(upload);
                    }

                }

                String[] uploads = new String[arrayList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = arrayList.get(i).getPatient();
                }

                adapterClass = new AdapterClass(getApplicationContext(), arrayList);

                listView.setAdapter(adapterClass);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Prescription.this, "Error..!!"+databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }
    private class AdapterClass extends ArrayAdapter {

        ArrayList<Pres> status;


        public AdapterClass(Context context, ArrayList<Pres> status) {

            super(context, R.layout.pres, R.id.bb, status);

            this.status = status;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.pres, parent, false);

            TextView textView = (TextView) view.findViewById(R.id.bb);

            Pres upload = status.get(position);

            textView.setText("Patient Name:\n"+upload.getPatient()+"\nDoctor's Name:\n"+upload.getDoctor()+"\nComplain:\n"+upload.getComplain()
                    +"\nHistory:\n"+upload.getHistory()+"\nMain Prescription:\n"+upload.getPrescription()+"\nDoctor's Advice:\n"+upload.getAdvice()+"\nInvestigation:\n"+upload.getInvestigation()+"\nDate:\n"+upload.getTime());



            return view;
        }
    }
}
