package com.example.calorify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calorify.Model.NutritionAnalysis;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;
import com.example.calorify.RetrofitData.EdamamClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class AddMealActivity extends AppCompatActivity{
    private EditText mMealName;
    private EditText mIngredients;
    private Button mButtonCalculation;
    private Button mAddButton;
    public NutritionAnalysis resultAnalysis;
    private SQLiteDatabase mDb;

    private final String api_id = "1a1e1904";
    private final String api_key = "607aa715a3e0a0479f1736694f98d190";
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
        mButtonCalculation = findViewById(R.id.calculateMealButton);
        mMealName = findViewById(R.id.mealName);
        mIngredients = findViewById(R.id.mealIngredients);
        mAddButton = findViewById(R.id.buttonAddMealPlan);

        mButtonCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdamamAsyncTask task = new EdamamAsyncTask();
                task.execute(api_id, api_key, mIngredients.getText().toString());
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = AddMealActivity.this;
                Class destinationActivity = MainActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                intent.putExtra("nutrition", resultAnalysis);

                MealDbHelper dbHelper = new MealDbHelper(context);
                mDb = dbHelper.getWritableDatabase();
                addToMealList(mAddButton);

                startActivity(intent);
            }
        });
    }

    class EdamamAsyncTask extends AsyncTask<String, Integer, NutritionAnalysis> {

        @Override
        protected NutritionAnalysis doInBackground(String... strings) {
            String[] array = strings[2].split("\n");
            HashMap<String, String[]> params = new HashMap<>();
            params.put("ingr", array);
            String strRequestBody = new Gson().toJson(params);

            final RequestBody requestBody = RequestBody.create(MediaType.
                    parse("application/json"),strRequestBody);
            final Call<NutritionAnalysis> call = EdamamClient.getService().getCalculation(strings[0],strings[1],requestBody);
            Log.d("Url: ", call.request().url().toString());
            try{
                return call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Implement a progress bar for the loading animation
        }

        @Override
        protected void onPostExecute(NutritionAnalysis nutritionAnalysis) {
//        super.onPostExecute(nutritionAnalysis);
            resultAnalysis = nutritionAnalysis;
            Toast.makeText(getApplicationContext(), resultAnalysis.getCalories().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    public void addToMealList(View view){
        if(mMealName.getText().length() == 0 || mIngredients.getText().length() == 0){
            return;
        }
        String mealName = mMealName.getText().toString();
        String ingredients = mIngredients.getText().toString();
        String calories = resultAnalysis.getCalories().toString();
        String fat = resultAnalysis.getTotalNutrients().getFAT().getQuantity().toString();
        String protein = resultAnalysis.getTotalNutrients().getPROCNT().getQuantity().toString();
        String sugars = resultAnalysis.getTotalNutrients().getSUGAR().getQuantity().toString();
        addMeal(mealName, ingredients, calories, fat, protein, sugars);

        mMealName.getText().clear();
        mIngredients.getText().clear();
    }

    private long addMeal(String name, String ingredients, String calories, String fat, String protein, String sugar){
        ContentValues cv = new ContentValues();
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_NAME, name);
        cv.put(MealContract.MealPlannerEntry.COLUMN_INGREDIENTS, ingredients);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS, calories);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT, fat);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN, protein);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_SUGAR, sugar);
        return mDb.insert(MealContract.MealPlannerEntry.TABLE_NAME, null, cv);
    }

}
