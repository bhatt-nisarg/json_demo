package com.example.json_demo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitAPI {

    //USE GET METHOD TO FETCH DATA AND IN SIDE GIVE LAST PARAMETER OF OUR URL
    @GET("63OH")

    //AS WE ARE CALLING DATA FROM ARRAY SO WE ARE CALLING IT WITH JSON OBJECT AND NAME THE METHOD getCourse();
    Call<RecyclerData> getCourse();
}
