package com.example.mohit.splash;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.lang.Void;
import java.lang.String;
import java.net.URL;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class Hipage5 extends AppCompatActivity {

    String json_string;
    JSONArray jsonArray;
    JSONObject jsonObject;

    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hipage5);
        json_string=getIntent().getExtras().getString("json_data");



        try {
            jsonObject=new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count=0;

            String Disease_name,Symptom,Medicine,stringArray;
            StringBuilder s = new StringBuilder(100);

                JSONObject JO= jsonArray.getJSONObject(0);
                Disease_name =JO.getString("Disease_name");
                TextView textView2= (TextView) findViewById(R.id.textView2);
                textView2.setText(Disease_name);

                jsonArray = JO.getJSONArray("Price");


            String[] string = new String[jsonArray.length()];
            for(int i = 0, cnt = jsonArray.length(); i< cnt; i++)
            {
                try {
                    String jsonString = jsonArray.getString(i);
                    s.append(jsonString.toString());
                    s.append(", ");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


}
