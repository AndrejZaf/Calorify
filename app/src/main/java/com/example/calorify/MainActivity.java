package com.example.calorify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calorify.Adapters.MealAdapter;
import com.example.calorify.Model.NutritionAnalysis;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    private Button addMealButton;
    private TextView mFatResult;
    private List<NutritionAnalysis> dailyPlan;
    private Button listMealsButton;
    private SQLiteDatabase mDb;
    private RecyclerView mRv;
    private MealAdapter mAdapter;
    private TextView mCaloriesGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFatResult = findViewById(R.id.fatResult);
        addMealButton = findViewById(R.id.addButton);
        listMealsButton = findViewById(R.id.listMeals);
        mCaloriesGoal = findViewById(R.id.txt_calorie_goal);
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

        setupSharedPreference();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeMeal(id);
                mAdapter.swapCursor(getAllMealsToday());
            }
        }).attachToRecyclerView(mRv);
//        Log.d("Cursor", String.valueOf(cursor.getCount()));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupSharedPreference(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mCaloriesGoal.setText(sharedPreferences.getString("calorie_goal", String.valueOf(2000)));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.calories_key))){
            mCaloriesGoal.setText(sharedPreferences.getString(key, getResources().getString(R.string.default_calories_goal)));
        }
    }
    private boolean removeMeal(long id){
        return mDb.delete(MealContract.MealPlannerEntry.TABLE_NAME, MealContract.MealPlannerEntry._ID+"="+id, null) > 0;
    }
}
