package com.example.json_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Json_okhttp extends AppCompatActivity {

    TextView CVName,Batchname,TrackName;
    ImageView CourseIV;
    CardView CardCV;
    String name,mode,track,image;

    String url = "https://jsonkeeper.com/b/63OH";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_okhttp);

        CVName = findViewById(R.id.Cnamehttp);
        Batchname = findViewById(R.id.httpTVBatch);
        TrackName = findViewById(R.id.httpTVTracks);
        CourseIV = findViewById(R.id.Coursehttp);
        CardCV = findViewById(R.id.httpcard);


        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                        .url(url)
                        .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                    if (response.isSuccessful()){

                        String recs = response.body().string();
                        //in below line we make our cardview visible after  we get all the data.

                        Json_okhttp.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    JSONObject reader = new JSONObject(recs);
                                    Log.d("vbcf",reader.toString());

                                     name = reader.getString("courseName");
                                     mode = reader.getString("courseMode");
                                     track = reader.getString("courseTracks");
                                     image = reader.getString("courseimg");

                                     setData();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.d("sdd",e.getMessage().toString());
                                }
                            }
                        });
                    }
            }
        });

    }

    private void setData() {
        CVName.setText(name);
        Batchname.setText(mode);
        TrackName.setText(track);
        Log.d("dsdsds",name+"=="+mode+"=="+track);
        //picasso use to load image from url
        Picasso.get().load(image).into(CourseIV);
    }
}