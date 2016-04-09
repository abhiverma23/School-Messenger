package com.techies.school.schoolmessenger;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    String [] menu = { "LOGIN", "SENDNOTIFICATION", "RECEIVENOTIFICATION", "LOGOUT", "QUIT"};
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.e("Entered in menu", "SUCESS");

        lv =(ListView) findViewById(R.id.menu_lv);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String temp = "";
                temp += lv.getItemAtPosition(position);
                try {
                    Intent intent = new Intent("android.intent.action." + menu[position]);
                    startActivity(intent);
                }catch (ActivityNotFoundException e)
                {
                    temp += " : Activity Under Construction";
                }finally {
                    Toast.makeText(getApplicationContext(), temp, Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
