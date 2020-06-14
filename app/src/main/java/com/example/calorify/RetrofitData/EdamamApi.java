package com.example.calorify.RetrofitData;

import com.example.calorify.Model.NutritionAnalysis;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface EdamamApi {
    @POST("nutrition-details")
    Call<NutritionAnalysis> getCalculation(@Query("app_id") String api_id,
                                           @Query("app_key") String api_key,
                                           @Body RequestBody requestBody);
    void loadInfo(NutritionAnalysis nutritionAnalysis);
}
