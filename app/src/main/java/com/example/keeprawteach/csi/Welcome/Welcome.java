package com.example.keeprawteach.csi.Welcome;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.keeprawteach.csi.Login.Login;
import com.example.keeprawteach.csi.MainActivity;
import com.example.keeprawteach.csi.R;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */

                Intent intent=new Intent(Welcome.this, Login.class);

                startActivity(intent);

                finish();
            }
        }, 2000);
    }
}
