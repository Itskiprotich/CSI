package com.example.keeprawteach.csi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.keeprawteach.csi.Each.Doctors;
import com.example.keeprawteach.csi.Each.Each;
import com.example.keeprawteach.csi.Each.Staff;
import com.example.keeprawteach.csi.Feedback.ReceiveFeedback;
import com.example.keeprawteach.csi.Payment.Payment;

public class MainActivity extends AppCompatActivity {

    private ImageView PATIENT,DOCTOR,STAFF,FEEDBACK,PAYMENT;

    private Toolbar toolbar;

    String namehere;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        namehere = getIntent().getStringExtra("key");

        toolbar=(Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        PATIENT=(ImageView)findViewById(R.id.patient);

        DOCTOR=(ImageView)findViewById(R.id.doctor);

        STAFF=(ImageView)findViewById(R.id.staff);

        FEEDBACK=(ImageView)findViewById(R.id.feed);

        PAYMENT=(ImageView)findViewById(R.id.payment);

        textView=(TextView)findViewById(R.id.welcome);

        textView.setText("Welcome\n"+namehere);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            return true;
        }
        if (id==R.id.aaaa){

            about();

            return true;
        }
        if (id==R.id.bbbb){

            lock();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void about() {

        AlertDialog.Builder al = new AlertDialog.Builder(this);

        al.setTitle("About");

        al.setMessage("IOT based Hospial Management System\nVersion 1.1.0");

        al.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = al.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {

        lock();

    }

    private void lock() {

        AlertDialog.Builder al = new AlertDialog.Builder(this);

        al.setMessage("Are you sure You want to Exit?");

        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                    finish();

            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        AlertDialog alertDialog = al.create();
        alertDialog.show();
    }

    public void Patient(View view) {

        Proceed("Patient Records");

    }

    private void Proceed(String name) {

        Intent intent=new Intent(MainActivity.this, Each.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", name);

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void Feedback(View view) {


        Intent intent=new Intent(MainActivity.this, ReceiveFeedback.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", "Staff Records");

        intent.putExtras(bundle);

        startActivity(intent);

    }

    public void Staff(View view) {
//        Proceed();

        Intent intent=new Intent(MainActivity.this, Staff.class);

        Bundle bundle = new Bundle();

        bundle.putString("key", "Staff Records");

        intent.putExtras(bundle);

        startActivity(intent);
    }

    public void Doctors(View view) {
//        Proceed();

        Intent intent=new Intent(MainActivity.this, Doctors.class);


        Bundle bundle = new Bundle();

        bundle.putString("key", "Doctors Records");

        intent.putExtras(bundle);

        startActivity(intent);

    }

    public void Payment(View view) {


        Intent intent=new Intent(MainActivity.this, Payment.class);


        Bundle bundle = new Bundle();

        bundle.putString("key", "Doctors Records");

        intent.putExtras(bundle);

        startActivity(intent);
    }
}
