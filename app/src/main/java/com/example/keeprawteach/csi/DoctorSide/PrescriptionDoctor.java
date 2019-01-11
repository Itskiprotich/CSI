package com.example.keeprawteach.csi.DoctorSide;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.keeprawteach.csi.Model.Pres;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class PrescriptionDoctor extends AppCompatActivity {

    private AutoCompleteTextView AA, BB, CC, DD, EE, FF, GG;

    private Button button;

    String a, b, c, d, e, f, g;

    FirebaseDatabase database;

    DatabaseReference reference;

    ProgressDialog progressDialog;

    String namehere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_doctor);

        namehere = getIntent().getStringExtra("key");

        button = (Button) findViewById(R.id.send);

        AA = (AutoCompleteTextView) findViewById(R.id.aa);

        BB = (AutoCompleteTextView) findViewById(R.id.bb);

        CC = (AutoCompleteTextView) findViewById(R.id.cc);

        DD = (AutoCompleteTextView) findViewById(R.id.dd);

        EE = (AutoCompleteTextView) findViewById(R.id.ee);

        FF = (AutoCompleteTextView) findViewById(R.id.ff);

        GG = (AutoCompleteTextView) findViewById(R.id.gg);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SavePrescription();
            }
        });
        AA.setText(namehere);
    }

    public void SavePrescription() {

        a = AA.getText().toString();

        b = BB.getText().toString();

        c = CC.getText().toString();

        d = DD.getText().toString();

        e = EE.getText().toString();

        f = FF.getText().toString();

        g = GG.getText().toString();

        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || f.isEmpty() || g.isEmpty()) {

            Toast.makeText(this, "Check all fields", Toast.LENGTH_SHORT).show();
        } else {
            savetoDB(a, b, c, d, e, f, g);
        }
    }

    private void savetoDB(String a, String b, String c, String d, String e, String f, String g) {

        String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        Pres upload = new Pres(a, b, c, d, e, f, g, time);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Prescription");

        //adding an upload to firebase database
        String uploadId = reference.push().getKey();

        reference.child(uploadId).setValue(upload);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                Toast.makeText(PrescriptionDoctor.this, "Prescription Sent Successfully", Toast.LENGTH_SHORT).show();

                clear();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

//                progressDialog.dismiss();

                Toast.makeText(PrescriptionDoctor.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void clear() {
        AA.setText("");

        BB.setText("");

        CC.setText("");

        DD.setText("");

        EE.setText("");

        FF.setText("");

        GG.setText("");
    }
}
