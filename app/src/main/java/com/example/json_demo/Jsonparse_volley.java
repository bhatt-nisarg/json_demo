package com.example.json_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class Jsonparse_volley extends AppCompatActivity {


    //creating variable for textview,imageview,cardview and progressbar
    private TextView courseNameTV, courseTracksTV,courseBatchTV;
    private ImageView courseIV;
    private ProgressBar loadingPB;
    private CardView courseCV;

    //below line is the variable for url from where we will be querying our data.
    String url = "https://jsonkeeper.com/b/63OH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsonparse_volley);

        //initializing our all view
        loadingPB = findViewById(R.id.idLoadingPB);
        courseCV = findViewById(R.id.idCVCourse);
        courseNameTV = findViewById(R.id.idTVCourseName);
        courseTracksTV = findViewById(R.id.idTVTracks);
        courseBatchTV = findViewById(R.id.idTVBatch);
        courseIV = findViewById(R.id.idIVCourse);

        //creating a new variable for our request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // as our data is in json object format so we are using
        // json object request to make data request from our url.
        // in below line we are making a json object
        // request and creating a new json object request.
        // inside our json object request we are calling a
        // method to get the data, "url" from where we are
        // calling our data,"null" as we are not passing any data.
        // later on we are calling response listener method
        // to get the response from our API.

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //inside response method we are hiding our progress bar
                        loadingPB.setVisibility(View.GONE);

                        //in below line we make our cardview visible after  we get all the data.
                        courseCV.setVisibility(View.VISIBLE);
                        try {
                            //now we get our response from api in json object format.
                            //in below line we are extracting a string with its key value from our json object
                            Log.d("abs",response.toString());
                            String coursename = response.getString("courseName");
                            String courseTracks = response.getString("courseTracks");
                            String courseMode = response.getString("courseMode");
                            String courseImageURL = response.getString("courseimg");

                            Log.d("asfg",courseImageURL + " " + coursename + " " + courseTracks + " " + courseMode);
                            //after extracting all the data we are setting that data to all our views
                            courseNameTV.setText(coursename);
                            courseTracksTV.setText(courseTracks);
                            courseBatchTV.setText(courseMode);

                            //picasso use to load image from url
                            Picasso.get().load(courseImageURL).into(courseIV);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //display error message
            Toast.makeText(Jsonparse_volley.this,"Fail to get data ",Toast.LENGTH_SHORT).show();
            }
        });

        //at the last we are adding our json object request to our request queue to fetch all the json data
       queue.add(jsonObjectRequest);
    }
}
