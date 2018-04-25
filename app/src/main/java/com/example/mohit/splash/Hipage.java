package com.example.mohit.splash;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.app.ProgressDialog;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;


public class Hipage extends AppCompatActivity {
    String json_string;
    String JSON_STRING;
    String temp;
    String prev="Wait";
    String jsonurl= "https://evacuative-builders.000webhostapp.com/getfromjson.php";

    ArrayList<String> items=new ArrayList<>();
    SpinnerDialog spinnerDialog;
    protected Button btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hipage2);
        //final Intent i = new Intent(this,DetailsInfo.class);

        Bundle appleData = getIntent().getExtras();
        if(appleData==null)
            return;
        String firstmessage=appleData.getString("firstmessage");
        String[] splited = firstmessage.split("\\s+");
        final TextView Second_Text=(TextView) findViewById(R.id.Second_Text);
        Second_Text.setText("Hi "+splited[0]);

        initItems();

         {
            spinnerDialog = new SpinnerDialog(Hipage.this, items, "select items");
            spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
                @Override
                public void onClick(String item, int position) {

                    Toast.makeText(Hipage.this, item, Toast.LENGTH_SHORT).show();
                    new JSONTask().execute(jsonurl, item);
                }

            });

            btnShow = (Button) findViewById(R.id.btnShow);
            btnShow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    spinnerDialog.showSpinerDialog();
                }

            });
        }
    }


    private void initItems() {
        for(int i=0;i<100;i++)
        {
            items.add("Asthma");
            items.add("Allergies");
            items.add("Underactive thyroid");
            items.add("Yellow fever");
            items.add("Cold");
            items.add("Fever");
            items.add("Gallstones");
            items.add("Flu");
            items.add("Food Poisoning");
            items.add("Dysphagia");
            items.add("Dehydration");
            items.add("Dental abscess");
            items.add("Diarrhoea");
            items.add("Depression");
            items.add("Gallstones");
            items.add("Gum disease");
            items.add("High Cholesterol");
            items.add("Hepatitis B");
            items.add("Indigestion");
            items.add("Measles");
            items.add("Malaria");
            items.add("Insomnia");
            items.add("Pneumonia");
            items.add("Sunburn");
            items.add("Tonsillitis");
            items.add("Tuberculosis (TB)");
            items.add("Ringworm");
            items.add("Osteoarthritis");
            items.add("Iron Deficiency Anaemia");
            items.add("Labyrinthitis");
            break;
        }
    }


    public class JSONTask extends AsyncTask<String,Void,String >{
        ProgressDialog loading;
        @Override
        protected String doInBackground(String... params) {

            HttpURLConnection connection = null;
            BufferedReader reader = null;
            URL url ;

            try {
                url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                //connection.connect();
                connection.setRequestMethod("POST");
                connection.setDoInput(true);
                connection.setDoOutput(true);

                HashMap<String,String > data = new HashMap<String, String>();
                data.put("Disease_name",params[1]);
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(data));
                writer.flush();
                writer.close();
                os.close();

                int responseCode=connection.getResponseCode();

                String response ="";
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    reader=new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    temp = reader.readLine();
                    response = temp;
                }
                else {
                    response="Error Registering";
                }

                return response;
            }
            catch (MalformedURLException e) {
                e.printStackTrace();
                return "MalformedURLException Exception";
            } catch (IOException e) {
                e.printStackTrace();
                return "IO Exception";
            }

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            loading = ProgressDialog.show(Hipage.this, "Please Wait", null, true, true);
        }

        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            loading.dismiss();
            Intent intent_name = new Intent();
            intent_name.setClass(getApplicationContext(),DetailsInfo.class);
            intent_name.putExtra("json_data",s);
            startActivity(intent_name);
        }

        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
            StringBuilder result = new StringBuilder();
            boolean first = true;
            for (Map.Entry<String, String> entry : params.entrySet()) {
                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
            }

            return result.toString();
        }
    }



}

