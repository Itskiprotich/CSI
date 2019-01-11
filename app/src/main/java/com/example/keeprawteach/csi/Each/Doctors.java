package com.example.keeprawteach.csi.Each;

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
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.Model.DoctorModel;
import com.example.keeprawteach.csi.Model.Upload;
import com.example.keeprawteach.csi.R;
import com.example.keeprawteach.csi.ViewData.ViewInfor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Doctors extends AppCompatActivity {


    private Toolbar toolbar;

    SwipeRefreshLayout swipeRefreshLayout;

    String namehere;

    AlertDialog al;

    ArrayList<DoctorModel> arrayList;

    AdapterClass adapterClass;

    FirebaseDatabase database;

    DatabaseReference reference;

    ListView listView;

    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        namehere = getIntent().getStringExtra("key");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(namehere);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        arrayList=new ArrayList<>();

        listView = (ListView) findViewById(R.id.list);

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.Swipe);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadData();
            }
        });
        loadData();

        floatingActionButton=(FloatingActionButton)findViewById(R.id.fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openNewPatient();

            }
        });
    }

    private void openNewPatient() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater lay = LayoutInflater.from(this);

        final View viewdata = lay.inflate(R.layout.newdoctor, null);

        final AutoCompleteTextView AA = (AutoCompleteTextView) viewdata.findViewById(R.id.aa);

        final AutoCompleteTextView BB = (AutoCompleteTextView) viewdata.findViewById(R.id.bb);

        final AutoCompleteTextView CC = (AutoCompleteTextView) viewdata.findViewById(R.id.cc);

        final AutoCompleteTextView DD = (AutoCompleteTextView) viewdata.findViewById(R.id.dd);

        final AutoCompleteTextView EE = (AutoCompleteTextView) viewdata.findViewById(R.id.ee);

        final AutoCompleteTextView FF = (AutoCompleteTextView) viewdata.findViewById(R.id.ff);

        final AutoCompleteTextView GG = (AutoCompleteTextView) viewdata.findViewById(R.id.gg);

        final AutoCompleteTextView HH = (AutoCompleteTextView) viewdata.findViewById(R.id.hh);

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

                String f = FF.getText().toString();

                String g = GG.getText().toString();

                String h = HH.getText().toString();

                if (a.isEmpty()) {
                    Toast.makeText(Doctors.this, "Enter Doctor Name", Toast.LENGTH_SHORT).show();
                } else if (b.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Idenification", Toast.LENGTH_SHORT).show();
                } else if (c.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Speciality", Toast.LENGTH_SHORT).show();
                } else if (d.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Gender", Toast.LENGTH_SHORT).show();
                } else if (e.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Phone", Toast.LENGTH_SHORT).show();
                } else if (f.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }else if (g.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Password", Toast.LENGTH_SHORT).show();
                }else if (h.isEmpty()) {

                    Toast.makeText(Doctors.this, "Enter Confirm password", Toast.LENGTH_SHORT).show();
                }
                else {

                    if (g.equalsIgnoreCase(h)) {
                        openPaypall(a, b, c, d, e,f,g);
                    }else{
                        Toast.makeText(Doctors.this, "Password does not match....Try again!!", Toast.LENGTH_SHORT).show();
                    }
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

    private void openPaypall(String user, String pass, String c, String d, String e, String f, String g) {


        DoctorModel upload=new DoctorModel(user,pass,c,d,e,f,g);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Doctors Records");

        //adding an upload to firebase database
        String uploadId = reference.push().getKey();

        reference.child(uploadId).setValue(upload);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                al.dismiss();

                Toast.makeText(Doctors.this, "Doctor's Added Successfully", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                al.dismiss();

                Toast.makeText(Doctors.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });




    }

    private void loadData() {
        if (namehere != null) {

            if (namehere.equalsIgnoreCase("Doctors Records")) {

                addData();

            }
        }

    }

    private void addData() {

        swipeRefreshLayout.setRefreshing(true);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Doctors Records");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                arrayList.clear();

                swipeRefreshLayout.setRefreshing(false);

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

//                    Upload upload = messageSnapshot.getValue(Upload.class);

                    DoctorModel upload=new DoctorModel();

                    String name = (String) messageSnapshot.child("name").getValue();

                    String identity = (String) messageSnapshot.child("identity").getValue();

                    String speciality = (String) messageSnapshot.child("speciality").getValue();

                    String gender = (String) messageSnapshot.child("gender").getValue();

                    String phone = (String) messageSnapshot.child("phone").getValue();

                    upload.setName(name);

                    upload.setIdentity(identity);

                    upload.setSpeciality(speciality);

                    upload.setGender(gender);

                    upload.setPhone(phone);

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

                Toast.makeText(Doctors.this, "Error..!!"+databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    private class AdapterClass extends ArrayAdapter {

        ArrayList<DoctorModel> status;


        public AdapterClass(Context context, ArrayList<DoctorModel> status) {

            super(context, R.layout.doctors, R.id.bb, status);

            this.status = status;

        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.doctors, parent, false);

            TextView textView = (TextView) view.findViewById(R.id.bb);

            DoctorModel upload = status.get(position);

            textView.setText("Name:"+upload.getName()+"\nIdentity:"+upload.getIdentity()+"\nSpeciality:"+upload.getSpeciality()
                    +"\nGender:"+upload.getGender()+"\nPhone:"+upload.getPhone());

            final String name=textView.getText().toString();

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    openInfor(name);
                }
            });



            return view;
        }
    }

    private void openInfor(String name) {

        Intent intent=new Intent(Doctors.this, ViewInfor.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", name);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {

        finish();

        return super.onSupportNavigateUp();
    }
}
