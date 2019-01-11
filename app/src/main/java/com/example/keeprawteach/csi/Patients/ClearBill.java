package com.example.keeprawteach.csi.Patients;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ClearBill extends AppCompatActivity {

    String id, total;

    private TextView textView;

    private EditText editText;

    private Button button;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear_bill);

        id = getIntent().getStringExtra("id");

        total = getIntent().getStringExtra("total");

        textView = (TextView) findViewById(R.id.name);

        textView.setText("Transaction Id:\t" + id + "\nYou're required to make a total payment of " + total + "\nFor the Medical Bill..\nComplete payments are accepted here");

        button = (Button) findViewById(R.id.yes);

        editText = (EditText) findViewById(R.id.pay);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seeData();
            }
        });

    }

    private void seeData() {

        String money = editText.getText().toString();

        if (money.isEmpty()) {

            Toast.makeText(this, "Enter Amount", Toast.LENGTH_SHORT).show();
        } else {
            if (money.equalsIgnoreCase(total)) {

                save(id);

            } else {
                Toast.makeText(this, "Complete payment are only accepted..Try again!1", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void save(final String id) {

        final String status = "Paid";

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Payment");

        Query pendingTasks = reference.orderByChild("id").equalTo(id);

        pendingTasks.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot tasksSnapshot) {
                for (DataSnapshot snapshot: tasksSnapshot.getChildren()) {

                    snapshot.getRef().child("status").setValue(status);

                    finish();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
