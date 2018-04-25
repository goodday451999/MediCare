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


public class FragmentInitial extends Fragment {
    View view;
    String strtext;
    TextView textView;
    JSONArray jsonArray;
    JSONObject jsonObject;
    public FragmentInitial() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        strtext = getArguments().getString("params");

        view = inflater.inflate(R.layout.initial_fragment, container,false);

        //Bundle bundle=getArguments();
        //String jsonString=bundle.getString("key");

        JSONObject obj=null;
        try {
            obj = new JSONObject(strtext);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonArray = obj.getJSONArray("Symptoms");
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
                s.append("\n \n");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }

        textView = (TextView) view.findViewById(R.id.text);
        textView.setText(s);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
