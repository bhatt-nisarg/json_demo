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

import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Json_Retrofit extends AppCompatActivity {
        //creating variable
    CardView card;
    ImageView retImg;
    TextView rname,rmode,rtrack;
    private ProgressBar loadingPB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_retrofit);
        loadingPB = findViewById(R.id.idLoadingPB);
        card = findViewById(R.id.Retrocard);
        retImg = findViewById(R.id.RetroImg);
        rname = findViewById(R.id.RetroCourseName);
        rmode = findViewById(R.id.RetroBatch);
        rtrack = findViewById(R.id.RetroTracks);

        getCourse();
    }
   private void getCourse(){

        //in below line we creating a retrofit builder and passing our base url
       Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://jsonkeeper.com/b/")
                        //on below line we are calling add converter factory as GSON converter factory
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();  // at last we build our retrofit builder

       //below line is to create an instance for our retrofit api class
       RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
       Call<RecyclerData> call = retrofitAPI.getCourse();
       call.enqueue(new Callback<RecyclerData>() {
           @Override
           public void onResponse(Call<RecyclerData> call, Response<RecyclerData> response) {
               if (response.isSuccessful()){
                   loadingPB.setVisibility(View.GONE);
                   // in below line we are making our card
                   // view visible after we get all the data.
                   card.setVisibility(View.VISIBLE);
                   RecyclerData modal = response.body();


                   //after extracting all the data we are setting that data to all our view
                   rname.setText(modal.getCourseName());
                   rtrack.setText(modal.getCourseTracks());
                   rmode.setText(modal.getCourseMode());

                   Picasso.get().load(modal.getCourseimg()).into(retImg);


               }
           }

           @Override
           public void onFailure(Call<RecyclerData> call, Throwable t) {

               //displaying an error message in toast
               Toast.makeText(Json_Retrofit.this,"Fail to get the data..",Toast.LENGTH_SHORT).show();
           }
       });


   }
}