package com.example.keeprawteach.csi.DoctorSide;

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
import android.widget.ListView;
import android.widget.TextView;

import com.example.keeprawteach.csi.Patients.Payments;
import com.example.keeprawteach.csi.Patients.Prescription;
import com.example.keeprawteach.csi.R;
import com.example.keeprawteach.csi.ViewData.ViewInfor;

public class PatientDoctor extends AppCompatActivity {


    String namehere,details;

    private TextView textView,CC;

    private ListView listView;

    String[] values=new String[]{"Prescription","Payments","Profile"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_doctor);

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

    private void passion(String name) {

        if (name.equalsIgnoreCase("Prescription")){

            Intent intent=new Intent(PatientDoctor.this, PrescriptionDoctor.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", namehere);

            intent.putExtras(bundle);

            startActivity(intent);
        }

        if (name.equalsIgnoreCase("Payments")){

            Intent intent=new Intent(PatientDoctor.this, Charges.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", namehere);

            intent.putExtras(bundle);

            startActivity(intent);
        }

        if (name.equalsIgnoreCase("Profile")){

            Intent intent=new Intent(PatientDoctor.this, ViewInforDoctor.class);

            Bundle bundle = new Bundle();

            bundle.putString("key", details);

            intent.putExtras(bundle);

            startActivity(intent);
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
