package com.techies.school.schoolmessenger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;

public class ReceiveMessages extends AppCompatActivity {

    private RadioButton a,p,t,m;

    private String [] msg;

    final String _default = "all";

    ListView listView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_messages);

        a = (RadioButton) findViewById(R.id.receive_all);
        p = (RadioButton) findViewById(R.id.receive_parents);
        t = (RadioButton) findViewById(R.id.receive_teachers);
        m = (RadioButton) findViewById(R.id.receive_management);

        listView1 = (ListView)findViewById(R.id.receive_listview);

        updateList(_default);

        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList("all");
                p.setChecked(false);
                t.setChecked(false);
                m.setChecked(false);
            }
        });

        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList("Parents");
                a.setChecked(false);
                t.setChecked(false);
                m.setChecked(false);
            }
        });

        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList("Teachers");
                a.setChecked(false);
                p.setChecked(false);
                m.setChecked(false);
            }
        });

        m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateList("Management");
                a.setChecked(false);
                p.setChecked(false);
                t.setChecked(false);
            }
        });


    }

    protected void updateList(String type)
    {
        String[] mList = {
                type,
                "Today - Sunny - 88/63",
                "Ok what is your status?",
                "Today - Sunny - 88/63",
                "Tomorrow - DontKnow - 55/22",
                "Hello World!",
                "How are you?",
                "What are you doing?",
                "Ok what is your status?",
                "Today - Sunny - 88/63",
                "Tomorrow - DontKnow - 55/22",
                "Hello World!",
                "How are you?",
                "What are you doing?",
                "Ok what is your status?",
                "Good bye..!"
        };

        listView1.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, mList));

    }
}
