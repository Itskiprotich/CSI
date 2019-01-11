package com.example.keeprawteach.csi.DoctorSide;

import android.content.Context;
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
import android.widget.Toast;

import com.example.keeprawteach.csi.R;

public class PaymentsDoctor extends AppCompatActivity {

    String namehere,details;

    private TextView textView,CC;

    private ListView listView;

    String[] values=new String[]{"Outdoor Charges","Investigation Charges","Pharmacy Charges","Admission Charges","Discharge Charges","Extra Charges"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payments_doctor);

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

    private void passion(String str) {

        Toast.makeText(this, ""+str, Toast.LENGTH_SHORT).show();
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
