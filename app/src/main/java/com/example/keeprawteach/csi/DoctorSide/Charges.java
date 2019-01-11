package com.example.keeprawteach.csi.DoctorSide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.keeprawteach.csi.Model.Charge;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Random;

public class Charges extends AppCompatActivity {

    private AutoCompleteTextView AA, BB, CC, DD, EE, FF;

    String a, b, c, d, e, f, namehere;

    private Button button;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_charges);

        namehere = getIntent().getStringExtra("key");

        AA = (AutoCompleteTextView) findViewById(R.id.aa);

        BB = (AutoCompleteTextView) findViewById(R.id.bb);

        CC = (AutoCompleteTextView) findViewById(R.id.cc);

        DD = (AutoCompleteTextView) findViewById(R.id.dd);

        EE = (AutoCompleteTextView) findViewById(R.id.ee);

        FF = (AutoCompleteTextView) findViewById(R.id.ff);

        button = (Button) findViewById(R.id.log);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAll();
            }
        });

    }

    private void checkAll() {

        a = AA.getText().toString();

        b = BB.getText().toString();

        c = CC.getText().toString();

        d = DD.getText().toString();

        e = EE.getText().toString();

        f = FF.getText().toString();
        if (a.isEmpty() || b.isEmpty() || c.isEmpty() || d.isEmpty() || e.isEmpty() || f.isEmpty()) {
            Toast.makeText(this, "check all fields", Toast.LENGTH_SHORT).show();
        } else {
            sendDta(a, b, c, d, e, f);
        }
    }

    private void sendDta(String a, String b, String c, String d, String e, String f) {

        int aa = Integer.parseInt(a);

        int bb = Integer.parseInt(a);

        int cc = Integer.parseInt(a);

        int dd = Integer.parseInt(a);

        int ee = Integer.parseInt(a);

        int ff = Integer.parseInt(a);

        int total = aa + bb + cc + dd + ee + ff;

        String alldata = "" + total;

        Random rand = new Random();

        // Generate random integers in range 0 to 999
        int rand_int1 = rand.nextInt(1000)+1000;

        String r=""+rand_int1;

        String time = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        Charge upload = new Charge(a, b, c, d, e, f, time, namehere, "Not Paid",alldata,r);

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Payment");

        //adding an upload to firebase database
        String uploadId = reference.push().getKey();

        reference.child(uploadId).setValue(upload);

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

//                Toast.makeText(Charges.this, "Saved Successfully", Toast.LENGTH_SHORT).show();

                finish();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Charges.this, "Failed..", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
