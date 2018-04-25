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

/**
 * Created by hp on 29-Mar-18.
 */

public class FragmentCritical extends Fragment {
    View view;
    String strtext;
    TextView textView;
    JSONArray jsonArray;
    JSONArray jsonArray1;
    JSONObject jsonObject;
    public FragmentCritical() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        strtext = getArguments().getString("params");

        view = inflater.inflate(R.layout.critical_fragment, container,false);

        //Bundle bundle=getArguments();
        //String jsonString=bundle.getString("key");

        JSONObject obj=null;
        try {
            obj = new JSONObject(strtext);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            jsonArray = obj.getJSONArray("Shop_name");
            jsonArray1 = obj.getJSONArray("Address");
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
                s.append("\n");

                String str = jsonArray1.getString(i);
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
                s1.append("\n \n");
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }*/

        textView = (TextView) view.findViewById(R.id.text3);
        textView.setText(s);

        /*textView = (TextView) view.findViewById(R.id.text4);
        textView.setText(s);*/
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
