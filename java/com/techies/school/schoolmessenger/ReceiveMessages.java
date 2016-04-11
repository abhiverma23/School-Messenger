package com.techies.school.schoolmessenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class ReceiveMessages extends AppCompatActivity {

    private RadioButton a,p,t,m;

    private String [] msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_messages);

        a = (RadioButton) findViewById(R.id.receive_all);
        p = (RadioButton) findViewById(R.id.receive_parents);
        t = (RadioButton) findViewById(R.id.receive_teachers);
        m = (RadioButton) findViewById(R.id.receive_management);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p.setChecked(false);
                t.setChecked(false);
                m.setChecked(false);
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setChecked(false);
                t.setChecked(false);
                m.setChecked(false);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setChecked(false);
                p.setChecked(false);
                m.setChecked(false);
            }
        });

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a.setChecked(false);
                p.setChecked(false);
                t.setChecked(false);
            }
        });


    }
}
