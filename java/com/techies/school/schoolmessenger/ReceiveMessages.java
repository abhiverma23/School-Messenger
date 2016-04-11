package com.techies.school.schoolmessenger;

import android.inputmethodservice.ExtractEditText;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ReceiveMessages extends AppCompatActivity {

    private RadioButton a,p,t,m;

    //protected String [] msg;

    final String _default = "all";

    ListView listView1;
    List<String> li;

    //Some Thing For Receiving From Internet
    InputStream is=null;
    String result=null;
    String line=null;
    public ExtractEditText e;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive_messages);

        Log.e("Pass 1", "Program Entered ");

        a = (RadioButton) findViewById(R.id.receive_all);
        p = (RadioButton) findViewById(R.id.receive_parents);
        t = (RadioButton) findViewById(R.id.receive_teachers);
        m = (RadioButton) findViewById(R.id.receive_management);

        Log.e("Pass 1", "List View and List creating ");
        listView1 = (ListView)findViewById(R.id.receive_listview);
        li = new ArrayList<>();
        Log.e("Pass 1", "List View and List created Now Calling Default");
        updateList(_default);
        Log.e("Pass 1", "Default Called Now Stable");

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

    protected void updateList(String type){

        Log.e("Pass 2", "Adding Items to list");
        li.add(type);
        Log.e("Pass 2", "First items added to list");
        getDataIn();
        Log.e("Pass 2", "All items to list setting Adapter");
        listView1.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, li));
        Log.e("Pass 2", "Adapter created now Clearing List (li)");
    }

    private void getDataIn(){

        Log.e("Pass 3", "Starting All");
        li.clear();
        Log.e("Pass 3", "Entery level list clear");
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            Log.e("Pass 3.0", "Line 1 Executed");
            StrictMode.setThreadPolicy(policy);
            Log.e("Pass 3.0", "Line 2 Executed");
            @SuppressWarnings("deprecation") HttpClient httpclient = new DefaultHttpClient();
            Log.e("Pass 3.0", "httpclient created");
            @SuppressWarnings("deprecation") HttpPost httppost = new HttpPost(
                    "http://jaiveer.890m.com/spinner_getmsg.php");
            Log.e("Pass 3.0", "Link setup done");
            HttpResponse response = httpclient.execute(httppost);
            Log.e("Pass 3.0", "Link executed");
            HttpEntity entity = response.getEntity();
            Log.e("Pass 3.0", "Entity setup success");
            is = entity.getContent();
        }catch (Exception ex){
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            Log.e("Pass 3.1", "Creating BufferReader");
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);
            Log.e("Pass 3.1", "Reader created");
            StringBuilder sb = new StringBuilder();
            Log.e("Pass 3.1", "Start reading lines");
            while ((line = reader.readLine()) != null)
            {
                sb.append(line).append("\n");
            }
            Log.e("Pass 3.1", "All lines reading done");
            is.close();
            result = sb.toString();
            Log.e("Pass 3.1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try {
            JSONArray JA = new JSONArray(result);
            JSONObject json;
            Log.e("jai 01", "kan");

            for (int i = 0; i < JA.length(); i++) {
                json = JA.getJSONObject(i);
                Log.e("jai 1", "kan");
                li.add(json.getString("msgby") + "\n" + json.getString("msg"));
                Log.e("jai 2", "kan");
            }
        }catch (Exception e){
            Log.e("Fail 3", e.toString());
        }
    }

}