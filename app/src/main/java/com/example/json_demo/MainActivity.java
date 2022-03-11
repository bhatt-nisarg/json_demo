package com.example.json_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static final String JSON_STR = "{\"employee\":{\"name\":\"Nisarg\",\"salary\":0}}";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView textView = findViewById(R.id.jsonText);
        try {
            JSONObject emp = (new JSONObject(JSON_STR)).getJSONObject("employee");
            String empname = emp.getString("name");
            int salary = emp.getInt("salary");


            String str = "Employee Name :" + empname +"\n" + "Employee Salary : " + salary;
            textView.setText(str);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}