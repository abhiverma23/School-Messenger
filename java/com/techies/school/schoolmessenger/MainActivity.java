package com.techies.school.schoolmessenger;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
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
import java.util.Collections;
import java.util.List;

public class MainActivity extends Activity {
    //is project mai spinner.php file use hui hai.jisko aise hi res folder mai rukh diya hai but
//actual mai us file ko xamp server mai rakhna hai. or mysql mai class name ki table create
//karna hai.jismai 2 column roll_no and name lena hai or data rakhna hai ex roll=1111 and
//name = AAAA. or is project ki mainfiest file mai bhi update kiya gaya hai
    InputStream is=null;
    String result=null;
    String line=null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

            StrictMode.setThreadPolicy(policy);
            HttpClient httpclient = new DefaultHttpClient();
//192.168.1.5 apne computer ka IP address hai. u can check by cmd 'ipconfig' command and spinner.php
//file xamp server mai rakhi gayi hai usi se data read kar rahe hai data base se class table ka
            HttpPost httppost = new HttpPost("http://jaiveer.890m.com/spinner.php");
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
            is = entity.getContent();
            Log.e("Pass 1", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 1", e.toString());
            Toast.makeText(getApplicationContext(), "Invalid IP Address",
                    Toast.LENGTH_LONG).show();
        }

        try
        {
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is,"iso-8859-1"),8);

            StringBuilder sb = new StringBuilder();

            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }

            is.close();
            result = sb.toString();
            Log.e("Pass 2", "connection success ");
        }
        catch(Exception e)
        {
            Log.e("Fail 2", e.toString());
        }

        try
        {Log.e("jai 00","kan");
            JSONArray JA=new JSONArray(result);
            JSONObject json= null;
            Log.e("jai 01","kan");
            final String[] str1 = new String[JA.length()];
            final String[] str2 = new String[JA.length()];

            for(int i=0;i<JA.length();i++)
            {
                json=JA.getJSONObject(i);
                Log.e("jai 1","kan");
                str1[i] = json.getString("username");
                str2[i]=json.getString("pass");
                Log.e("jai 2","kan");
            }

            final Spinner sp = (Spinner) findViewById(R.id.spinner1);
            List<String> list = new ArrayList<String>();
            Log.e("jai 3","kan");
            for(int i=0;i<str2.length;i++)
            {
                list.add(str1[i]+str2[i]);
            }

            Collections.sort(list);

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>
                    (getApplicationContext(), android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sp.setAdapter(dataAdapter);

            sp.setOnItemSelectedListener(new OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> arg0, View arg1,int position, long id)
                {
                    // TODO Auto-generated method stub

                    String item=sp.getSelectedItem().toString();

                    Toast.makeText(getApplicationContext(), item,
                            Toast.LENGTH_LONG).show();

                    Log.e("Item",item);
                }

                public void onNothingSelected(AdapterView<?> arg0)
                {
                    // TODO Auto-generated method stub
                }
            });
        }
        catch(Exception e)
        {
            Log.e("Fail 3", e.toString());
        }
    }


}
