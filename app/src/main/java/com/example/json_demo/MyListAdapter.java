package com.example.json_demo;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.HashMap;

public class MyListAdapter extends ArrayAdapter<HashMap<String,String>> {
    private final ArrayList<HashMap<String,String>> listitem;
    private final Activity context;
    CardView evencard,oddcard;
    public MyListAdapter(Activity context, ArrayList<HashMap<String,String>> listitem) {
        super(context, R.layout.activity_json_array,listitem);
        this.listitem = listitem;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View view,ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowview = inflater.inflate(R.layout.list_item,null,true);
        evencard = rowview.findViewById(R.id.cardeven);
        oddcard = rowview.findViewById(R.id.cardodd);
//        Log.d("nb", listitem[position]);
        if (position%2 == 0){

            oddcard.setVisibility(View.INVISIBLE);
            //even
            TextView eid = (TextView) rowview.findViewById(R.id.evenid);
            TextView ename = (TextView) rowview.findViewById(R.id.evenname);
            TextView eemail = (TextView) rowview.findViewById(R.id.evenemail);
            TextView  eaddress= (TextView) rowview.findViewById(R.id.evenaddress);
            TextView emobile = (TextView) rowview.findViewById(R.id.evenmobile);
            TextView ehome = (TextView) rowview.findViewById(R.id.evenhome);
            TextView eoffice = (TextView) rowview.findViewById(R.id.evenoffice);
            TextView egender = (TextView) rowview.findViewById(R.id.evengender);

            eid.setText( listitem.get(position).get("id"));
            ename.setText( listitem.get(position).get("name"));
            eemail.setText( listitem.get(position).get("email"));
            eaddress.setText(listitem.get(position).get("address"));
            emobile.setText( listitem.get(position).get("mobile"));
            ehome.setText( listitem.get(position).get("home"));
            eoffice.setText( listitem.get(position).get("office"));
            egender.setText(listitem.get(position).get("gender"));




        }
        else {
            //odd
            evencard.setVisibility(View.INVISIBLE);
            TextView id = (TextView) rowview.findViewById(R.id.id);
            TextView name = (TextView) rowview.findViewById(R.id.name);
            TextView email = (TextView) rowview.findViewById(R.id.email);
            TextView  address= (TextView) rowview.findViewById(R.id.address);
            TextView mobile = (TextView) rowview.findViewById(R.id.mobile);
            TextView home = (TextView) rowview.findViewById(R.id.home);
            TextView office = (TextView) rowview.findViewById(R.id.office);
            TextView gender = (TextView) rowview.findViewById(R.id.gender);

            id.setText( listitem.get(position).get("id"));
            name.setText( listitem.get(position).get("name"));
            email.setText( listitem.get(position).get("email"));
            address.setText( listitem.get(position).get("address"));
            mobile.setText( listitem.get(position).get("mobile"));
            home.setText( listitem.get(position).get("home"));
            office.setText( listitem.get(position).get("office"));
            gender.setText( listitem.get(position).get("gender"));

        }

        return rowview;


    }
}
