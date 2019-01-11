package com.example.keeprawteach.csi.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.keeprawteach.csi.MainActivity;
import com.example.keeprawteach.csi.Model.Upload;
import com.example.keeprawteach.csi.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private Button button;

    private AutoCompleteTextView AA,BB;

    String a,b;

    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        button=(Button)findViewById(R.id.login);

        AA=(AutoCompleteTextView)findViewById(R.id.aa);

        BB=(AutoCompleteTextView)findViewById(R.id.bb);
    }

    public void Login(View view) {

        a=AA.getText().toString();

        b=BB.getText().toString();

        if (a.isEmpty()){

            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        }else if (b.isEmpty()){

            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        }else{

            check(a,b);
        }
    }

    private void check(final String a, final String b) {


        final ProgressDialog progressDialog = new ProgressDialog(Login.this);

        progressDialog.setIndeterminate(true);

        progressDialog.setMessage("Authenticating...");

        progressDialog.show();

        new android.os.Handler().postDelayed(

                new Runnable() {

                    public void run() {

                        check_db(a,b);

                        progressDialog.dismiss();

                    }
                }, 3000);
    }

    private void check_db(final String a, final String b) {


        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Doctors Records");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    String name = (String) messageSnapshot.child("username").getValue();

                    String pass = (String) messageSnapshot.child("password").getValue();

                    if (a.equalsIgnoreCase(name)){

                        if (b.equalsIgnoreCase(pass)){

                            finish();

                            Intent intent=new Intent(Login.this, MainActivity.class);

                            Bundle bundle = new Bundle();

                            bundle.putString("key", a);

                            intent.putExtras(bundle);

                            startActivity(intent);

                        }else{
                            Toast.makeText(Login.this, "Incorrect Login  Credentials", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(Login.this, "Error..!!"+databaseError, Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void Request(View view) {

        Intent intent=new Intent(Login.this,Request.class);

        startActivity(intent);
    }
}
