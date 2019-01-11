package com.example.keeprawteach.csi.Each;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.DoctorSide.PatientDoctor;
import com.example.keeprawteach.csi.Model.Upload;
import com.example.keeprawteach.csi.Patients.PatientsDash;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Calendar;

public class Each extends AppCompatActivity {

    private Toolbar toolbar;

    SwipeRefreshLayout swipeRefreshLayout;

    String namehere;

    AlertDialog al;

    ArrayList<Upload> arrayList;

    AdapterClass adapterClass;

    FirebaseDatabase database;

    DatabaseReference reference;

    ListView listView;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each);

        namehere = getIntent().getStringExtra("key");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(namehere);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList = new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.Swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();
            }
        });
        loadData();

        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewPatient();

            }
        });
    }

    private void passion(String str) {

        Toast.makeText(this, "" + str, Toast.LENGTH_SHORT).show();
    }

    private void openNewPatient() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater lay = LayoutInflater.from(this);

        final View viewdata = lay.inflate(R.layout.newpatient, null);

        final AutoCompleteTextView AA = (AutoCompleteTextView) viewdata.findViewById(R.id.aa);

        final AutoCompleteTextView BB = (AutoCompleteTextView) viewdata.findViewById(R.id.bb);

        final AutoCompleteTextView CC = (AutoCompleteTextView) viewdata.findViewById(R.id.cc);

        final AutoCompleteTextView DD = (AutoCompleteTextView) viewdata.findViewById(R.id.dd);

        final AutoCompleteTextView EE = (AutoCompleteTextView) viewdata.findViewById(R.id.ee);

        Button Cancel = (Button) viewdata.findViewById(R.id.terms);

        Button Login = (Button) viewdata.findViewById(R.id.login);


        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String a = AA.getText().toString();

                String b = BB.getText().toString();

                String c = CC.getText().toString();

                String d = DD.getText().toString();

                String e = EE.getText().toString();

                if (a.isEmpty()) {
                    Toast.makeText(Each.this, "Enter Name", Toast.LENGTH_SHORT).show();
                } else if (b.isEmpty()) {

                    Toast.makeText(Each.this, "Enter Sex", Toast.LENGTH_SHORT).show();
                } else if (c.isEmpty()) {

                    Toast.makeText(Each.this, "Enter Age", Toast.LENGTH_SHORT).show();
                } else if (d.isEmpty()) {

                    Toast.makeText(Each.this, "Enter Address", Toast.LENGTH_SHORT).show();
                } else if (e.isEmpty()) {

                    Toast.makeText(Each.this, "Enter Condition", Toast.LENGTH_SHORT).show();
                } else {
                    openPaypall(a, b, c, d, e);
                }


            }
        });
        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                al.dismiss();

            }
        });


        builder.setView(viewdata);

        al = builder.create();

        al.show();

    }

    private void openPaypall(String user, String pass, String c, String d, String e) {

        String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        Upload upload = new Upload(user, pass, c, d, e, time);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Patient Records");

        //adding an upload to firebase database
        String uploadId = reference.push().getKey();

        reference.child(uploadId).setValue(upload);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                al.dismiss();

//                Toast.makeText(Each.this, "Patient Added Successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                al.dismiss();

                Toast.makeText(Each.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadData() {
        if (namehere != null) {

            if (namehere.equalsIgnoreCase("Patient Records")) {

                addData();

            }
            if (namehere.equalsIgnoreCase("Doctor Records")) {

            }
        }

    }

    private void addData() {

        swipeRefreshLayout.setRefreshing(true);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Patient Records");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();

                swipeRefreshLayout.setRefreshing(false);

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

//                    Upload upload = messageSnapshot.getValue(Upload.class);

                    Upload upload = new Upload();

                    String name = (String) messageSnapshot.child("name").getValue();

                    String sex = (String) messageSnapshot.child("sex").getValue();

                    String age = (String) messageSnapshot.child("age").getValue();

                    String address = (String) messageSnapshot.child("address").getValue();

                    String status = (String) messageSnapshot.child("condition").getValue();

                    String time = (String) messageSnapshot.child("time").getValue();

                    upload.setName(name);

                    upload.setSex(sex);

                    upload.setAge("" + age);

                    upload.setAddress(address);

                    upload.setCondition(status);

                    upload.setTime(time);

                    arrayList.add(upload);

                }

                String[] uploads = new String[arrayList.size()];

                for (int i = 0; i < uploads.length; i++) {
                    uploads[i] = arrayList.get(i).getName();
                }

                adapterClass = new AdapterClass(getApplicationContext(), arrayList);

                listView.setAdapter(adapterClass);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                swipeRefreshLayout.setRefreshing(false);

                Toast.makeText(Each.this, "Error..!!" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private class AdapterClass extends ArrayAdapter {

        ArrayList<Upload> status;


        public AdapterClass(Context context, ArrayList<Upload> status) {

            super(context, R.layout.doctors, R.id.bb, status);

            this.status = status;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.doctors, parent, false);

            TextView textView = (TextView) view.findViewById(R.id.bb);

            Upload upload = status.get(position);

            textView.setText("Name:" + upload.getName() + "\nAge:" + upload.getAge() + "\nSex:" + upload.getSex()
                    + "\nAddress:" + upload.getAddress() + "\nDate:" + upload.getTime());

            final String name = upload.getName();//

            final String all= textView.getText().toString();

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    openEach(name,all);
                }
            });

            return view;
        }
    }

    private void openEach(String name,String all) {

        Intent intent = new Intent(Each.this, PatientDoctor.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", name);

        bundle.putString("details", all);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return super.onSupportNavigateUp();
    }
}
