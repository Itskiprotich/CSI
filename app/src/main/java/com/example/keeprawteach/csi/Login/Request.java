package com.example.keeprawteach.csi.Login;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.keeprawteach.csi.MainActivity;
import com.example.keeprawteach.csi.Patients.PatientsDash;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class Request extends AppCompatActivity {

    private Button Cancel, Submit;

    private AutoCompleteTextView AA, BB, CC, DD, EE;


    FirebaseDatabase database;

    DatabaseReference reference;

    AlertDialog al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        Cancel = (Button) findViewById(R.id.terms);

        Submit = (Button) findViewById(R.id.login);


        AA = (AutoCompleteTextView) findViewById(R.id.aa);

        BB = (AutoCompleteTextView) findViewById(R.id.bb);

        CC = (AutoCompleteTextView) findViewById(R.id.cc);

        DD = (AutoCompleteTextView) findViewById(R.id.dd);

        EE = (AutoCompleteTextView) findViewById(R.id.ee);

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLog();
            }
        });
    }


    private void openLog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LayoutInflater lay = LayoutInflater.from(this);

        final View viewdata = lay.inflate(R.layout.login, null);

        final AutoCompleteTextView AA = (AutoCompleteTextView) viewdata.findViewById(R.id.aa);

        Button Cancel = (Button) viewdata.findViewById(R.id.terms);

        Button Login = (Button) viewdata.findViewById(R.id.login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String a = AA.getText().toString();
                if (a.isEmpty()){
                    Toast.makeText(Request.this, "Enter Username", Toast.LENGTH_SHORT).show();
                }else{
                    seach(a);
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

    private void seach(final String a) {

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Patient Records");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    String name = (String) messageSnapshot.child("name").getValue();

                    if (a.equalsIgnoreCase(name)) {

                        clear(name);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Request.this, "Error..!!" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });

    }


    public void Submit(View view) {


        String a = AA.getText().toString();

        String b = BB.getText().toString();

        String c = CC.getText().toString();

        String d = DD.getText().toString();

        String e = EE.getText().toString();

        if (a.isEmpty()) {
            Toast.makeText(Request.this, "Enter Name", Toast.LENGTH_SHORT).show();
        } else if (b.isEmpty()) {

            Toast.makeText(Request.this, "Enter Sex", Toast.LENGTH_SHORT).show();
        } else if (c.isEmpty()) {

            Toast.makeText(Request.this, "Enter Age", Toast.LENGTH_SHORT).show();
        } else if (d.isEmpty()) {

            Toast.makeText(Request.this, "Enter Address", Toast.LENGTH_SHORT).show();
        } else if (e.isEmpty()) {

            Toast.makeText(Request.this, "Enter Condition", Toast.LENGTH_SHORT).show();
        } else {
            openSubmit(a, b, c, d, e);
        }

    }

    private void openSubmit(final String a, final String b, final String c, final String d, final String e) {

        final ProgressDialog progressDialog = new ProgressDialog(Request.this);

        progressDialog.setIndeterminate(true);

        progressDialog.setMessage("Sending request...");

        progressDialog.show();

        new android.os.Handler().postDelayed(

                new Runnable() {

                    public void run() {

                        check_db(a, b, c, d, e);

                        progressDialog.dismiss();

                    }
                }, 3000);
    }

    private void check_db(final String a, String b, String c, String d, String e) {

        String time=java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        SendRequest upload = new SendRequest(a, b, c, d, e,time);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Patient Records");

        //adding an upload to firebase database
        String uploadId = reference.push().getKey();

        reference.child(uploadId).setValue(upload);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Toast.makeText(Request.this, "Request Sent Successfully", Toast.LENGTH_SHORT).show();

                clear(a);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Request.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void clear(String name) {


        Intent intent = new Intent(Request.this, PatientsDash.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", name);

        intent.putExtras(bundle);

        startActivity(intent);

        finish();
    }
}
