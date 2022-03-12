package com.example.json_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JsonArray extends AppCompatActivity {

    private String TAG = JsonArray.class.getSimpleName();
    private ListView lv;


    ArrayList<HashMap<String,String>> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_array);

        contactList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);

        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void,Void,Void>{

        protected void onPreExecute(){
            super.onPreExecute();
            Toast.makeText(JsonArray.this,"Json Data is downloading",Toast.LENGTH_LONG).show();
        }

        @Override
        protected Void doInBackground(Void...voids) {

            Handler handler = new Handler();

            //Making request to url and getting response
            String url = "https://api.androidhive.info/contacts/";
            Log.d("qwes",url);
            String jsonStr = handler.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            Log.d("anmb",jsonStr);
            if (jsonStr != null){
                try {

                    JSONObject jsonObj = new JSONObject(jsonStr);

                    //getting Json Array node
                    JSONArray contacts = jsonObj.getJSONArray("contacts");

                    Log.d("lkjh",contacts.toString());

                    //looping through All Contacts
                    for (int i = 0;i<contacts.length();i++){

                        JSONObject c = contacts.getJSONObject(i);
                        Log.d("ccccc",c.toString());
                        String id= c.getString("id");
                        String name = c.getString("name");
                        String email = c.getString("email");
                        String address = c.getString("address");
                        String gender = c.getString("gender");

                        //phone node is JSON Object
                        JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");

//                        //tmp hash map for single contact
                        HashMap<String,String> contact = new HashMap<>();

                        //adding each child node to hashmap key => value
                        contact.put("id",id);
                        contact.put("name",name);
                        contact.put("email",email);
                        contact.put("address",address);
                        contact.put("gender",gender);
                        contact.put("mobile",mobile);
                        contact.put("home",home);
                        contact.put("office",office);
                        Log.d("fghy", id + name );



                        //add contact to list
                        contactList.add(contact);


                    }


                } catch (final JSONException e) {
//                    e.printStackTrace();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"json parsing error : "+e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }else {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Couldn't get json from server",Toast.LENGTH_LONG).show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            Log.d("sdsdds", String.valueOf(contactList.size()));
            ListAdapter adapter = new SimpleAdapter(JsonArray.this,contactList,R.layout.list_item,new String[]{"id","name","email","address","gender","mobile","home","office"},new int[]{R.id.id,R.id.name,R.id.email,R.id.address,R.id.gender,R.id.mobile,R.id.home,R.id.office});

            lv.setAdapter(adapter);
        }
    }
}

