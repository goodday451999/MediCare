package com.example.mohit.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FragmentIntermediate extends Fragment {
    View view;
    String strtext;
    TextView textView;
    JSONArray jsonArray;
    JSONArray jsonArray1;
    JSONObject jsonObject;
    public FragmentIntermediate() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {


        strtext = getArguments().getString("params");

        view = inflater.inflate(R.layout.intermediate_fragment, container,false);

        //Bundle bundle=getArguments();
        //String jsonString=bundle.getString("key");

        JSONObject obj=null;
        try {
            obj = new JSONObject(strtext);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonArray = obj.getJSONArray("Medicines");
            jsonArray1 = obj.getJSONArray("Price");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        StringBuilder s = new StringBuilder(100);
        for(int i = 0, cnt = jsonArray.length(); i< cnt; i++)
        {
            try {
                String jsonString = jsonArray.getString(i);
                s.append(i+1);
                s.append(" . ");
                s.append(jsonString.toString());
                s.append("\n Rs.");
                String str=jsonArray1.getString(i);
                s.append(str.toString());
                s.append("\n \n");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /*StringBuilder s1 = new StringBuilder(100);
        for(int i = 0, cnt = jsonArray1.length(); i< cnt; i++)
        {
            try {
                String jsonString = jsonArray1.getString(i);
                s1.append(jsonString.toString());
                s1.append("\n ");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

        textView = (TextView) view.findViewById(R.id.text1);
        textView.setText(s);


        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
