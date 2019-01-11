package com.example.keeprawteach.csi.DoctorSide;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.keeprawteach.csi.R;

public class ViewInforDoctor extends AppCompatActivity {


    private Button button;

    private TextView textView;

    String namehere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_infor_doctor);

        namehere = getIntent().getStringExtra("key");

        button=(Button)findViewById(R.id.ok);

        textView=(TextView)findViewById(R.id.text);

        textView.setText(""+namehere);
    }

    public void OK(View view) {

        finish();
    }
}
