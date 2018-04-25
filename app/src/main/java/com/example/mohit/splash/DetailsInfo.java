package com.example.mohit.splash;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class DetailsInfo extends AppCompatActivity {
    public TabLayout tabLayout;
    public AppBarLayout appBarLayout;
    public ViewPager viewPager;
    String json_string;
    JSONArray jsonArray;
    JSONArray jsonArray1,jsonArray2,jsonArray3,jsonArray4;
    JSONObject jsonObject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        json_string=getIntent().getExtras().getString("json_data");

        Bundle bundle = new Bundle();
        Bundle bundle1 = new Bundle();
        Bundle bundle2 = new Bundle();

        try {
            jsonObject=new JSONObject(json_string);
            jsonArray = jsonObject.getJSONArray("server_response");

            int count=0;

            String Disease_name,Symptom,Medicine,stringArray;

            JSONObject JO= jsonArray.getJSONObject(0);
            Disease_name =JO.getString("Disease_name");

            jsonArray = JO.getJSONArray("Symptoms");
            jsonArray1 = JO.getJSONArray("Medicines");
            jsonArray2 = JO.getJSONArray("Price");
            jsonArray3 = JO.getJSONArray("Shop_name");
            jsonArray4 = JO.getJSONArray("Address");

            String json = "{\"Symptoms\":"+jsonArray+"}";
            JSONObject object = (JSONObject) new JSONTokener(json).nextValue();
            bundle.putString("params",object.toString());


            String json1 = "{\"Medicines\":"+jsonArray1+","+"\"Price\":"+jsonArray2+"}";
            JSONObject object1 = (JSONObject) new JSONTokener(json1).nextValue();
            bundle1.putString("params",object1.toString());

            String json2 = "{\"Shop_name\":"+jsonArray3+","+"\"Address\":"+jsonArray4+"}";
            JSONObject object2 = (JSONObject) new JSONTokener(json2).nextValue();
            bundle2.putString("params",object2.toString());

            } catch (JSONException e) {
                e.printStackTrace();
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout_id);
        appBarLayout = (AppBarLayout) findViewById(R.id.appBarLayout_id);
        viewPager = (ViewPager) findViewById(R.id.viewPager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //Adding Fragment
        FragmentInitial myObj = new FragmentInitial();
        adapter.AddFragment(myObj,"Symptoms");
        myObj.setArguments(bundle);
        //adapter.AddFragment(new FragmentInitial(),"Symptoms");
        FragmentIntermediate myObj1 = new FragmentIntermediate();
        adapter.AddFragment(myObj1,"Medicines");
        myObj1.setArguments(bundle1);

        FragmentCritical myObj2 = new FragmentCritical();
        adapter.AddFragment(myObj2,"Medical Stores");
        myObj2.setArguments(bundle2);

        //adapter.AddFragment(new FragmentIntermediate(),"Medicines");
        //adapter.AddFragment(new FragmentCritical(),"Medical Stores");
        //Adapter Setup

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

    }
}
