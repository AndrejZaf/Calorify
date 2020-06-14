package com.example.calorify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calorify.Adapters.MealAdapter;
import com.example.calorify.Model.NutritionAnalysis;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Button addMealButton;
    private TextView mFatResult;
    private List<NutritionAnalysis> dailyPlan;
    private Button listMealsButton;
    private SQLiteDatabase mDb;
    private RecyclerView mRv;
    private MealAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFatResult = findViewById(R.id.fatResult);
        addMealButton = findViewById(R.id.addButton);
        listMealsButton = findViewById(R.id.listMeals);
        dailyPlan = new ArrayList<>();

        mRv = findViewById(R.id.rv_meals_today);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRv.setLayoutManager(layoutManager);

        addMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = MainActivity.this;
                Class destinationActivity = AddMealActivity.class;
                Intent intent = new Intent(context, destinationActivity);
                startActivity(intent);
            }
        });

        listMealsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();
        if(intent.hasExtra("nutrition")){
            NutritionAnalysis analysis = (NutritionAnalysis) intent.getSerializableExtra("nutrition");
            dailyPlan.add(analysis);
            mFatResult.setText(analysis.getCalories().toString());
        }


        MealDbHelper dbHelper = new MealDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        //Works properly
        Cursor cursor = getAllMealsToday();
        mAdapter = new MealAdapter(this, cursor);
        mRv.setAdapter(mAdapter);
        // TODO Implement the RecyclerView here
//        Log.d("Cursor", String.valueOf(cursor.getCount()));
    }

    public Cursor getAllMealsToday(){
        return mDb.query(
                MealContract.MealPlannerEntry.TABLE_NAME,
                null,
                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP+">=date('now','localtime','start of day')",
                null,
                null,
                null,
                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP
        );
    }
}
