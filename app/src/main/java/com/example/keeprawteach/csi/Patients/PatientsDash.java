package com.example.keeprawteach.csi.Patients;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.keeprawteach.csi.Feedback.SendFeedback;
import com.example.keeprawteach.csi.Login.Request;
import com.example.keeprawteach.csi.R;
import com.example.keeprawteach.csi.ViewData.ViewInfor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientsDash extends AppCompatActivity {

    String namehere,details;

    private TextView  textView,CC;

    private ListView listView;

    String[] values=new String[]{"Prescription","Payments","Profile","Ask a Doctor"};


    FirebaseDatabase database;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_dash);

        namehere = getIntent().getStringExtra("key");

        details = getIntent().getStringExtra("details");

        textView=(TextView)findViewById(R.id.pp);

        CC=(TextView)findViewById(R.id.cc);

        textView.setText("Patient's Details:"+namehere);

        CC.setText(""+details);

        listView=(ListView)findViewById(R.id.itemlist);

        MyAdapter adapter=new MyAdapter(getApplicationContext(),values);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Object o = listView.getItemAtPosition(position);

                String str=(String)o;//As you are using Default String Adapter

                passion(str);
            }
        });
    }

    private void searchData() {

        database = FirebaseDatabase.getInstance();

        reference = database.getReference("Patient Records");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot messageSnapshot : dataSnapshot.getChildren()) {

                    String name = (String) messageSnapshot.child("name").getValue();

                    String sex = (String) messageSnapshot.child("sex").getValue();

                    String age = (String) messageSnapshot.child("age").getValue();

                    String address = (String) messageSnapshot.child("address").getValue();

                    String status = (String) messageSnapshot.child("condition").getValue();

                    String time = (String) messageSnapshot.child("time").getValue();

                    if (namehere.equalsIgnoreCase(name)) {

                        details="Name:\n"+name+"\nGender:\n"+sex+"\nAge:\n"+age+"\nAddress:\n"+address+"\nDate:\n"+time;

                        Intent intent = new Intent(PatientsDash.this, ViewInfor.class);

                        Bundle bundle = new Bundle();

                        bundle.putString("key", details);

                        intent.putExtras(bundle);

                        startActivity(intent);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Toast.makeText(PatientsDash.this, "Error..!!" + databaseError, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void passion(String name) {

        if (name.equalsIgnoreCase("Prescription")){

            Intent intent=new Intent(PatientsDash.this, Prescription.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", namehere);

            intent.putExtras(bundle);

            startActivity(intent);
        }

        if (name.equalsIgnoreCase("Payments")){

            Intent intent=new Intent(PatientsDash.this, Payments.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", namehere);

            intent.putExtras(bundle);

            startActivity(intent);
        }

        if (name.equalsIgnoreCase("Ask a Doctor")){

            Intent intent=new Intent(PatientsDash.this, SendFeedback.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", namehere);

            intent.putExtras(bundle);

            startActivity(intent);
        }

        if (name.equalsIgnoreCase("Profile")){

            if (details==null){

                searchData();

            }else {

                Intent intent = new Intent(PatientsDash.this, ViewInfor.class);

                Bundle bundle = new Bundle();

                bundle.putString("key", details);

                intent.putExtras(bundle);

                startActivity(intent);
            }
        }

    }

    private class MyAdapter extends ArrayAdapter {

        Context context;

        String[] values;

        public MyAdapter(Context context, String[] values) {
            super(context,R.layout.listpatients,R.id.bb,values);

            this.context = context;

            this.values = values;

        }

        @NonNull
        @Override
        public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View row=inflater.inflate(R.layout.listpatients,parent,false);

            TextView textView=(TextView)row.findViewById(R.id.bb);

            textView.setText(values[position]);

            return row;
        }
    }
}
