package com.example.calorify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.calorify.Adapters.MealAdapter;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;

public class MealsActivity extends AppCompatActivity {
    private SQLiteDatabase mDb;
    private RecyclerView mMealList;
    private MealAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        mMealList = findViewById(R.id.rv_meals);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mMealList.setLayoutManager(layoutManager);

        MealDbHelper dbHelper = new MealDbHelper(this);
        mDb = dbHelper.getReadableDatabase();
        // Data is stored, create a recycler view
        Cursor cursor = getAllMeals();
        mAdapter = new MealAdapter(this, cursor);
        mMealList.setAdapter(mAdapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                //Left direction 4
                //Right direction 8
                if(direction == 8){
                    long id = (long) viewHolder.itemView.getTag();
                    removeMeal(id);
                    mAdapter.swapCursor(getAllMeals());
                } else if(direction == 4){
                    long id = (long) viewHolder.itemView.getTag();
                    if(getMeal(id))
                        Toast.makeText(MealsActivity.this, "Meal added to your plan", Toast.LENGTH_SHORT).show();
                    mAdapter.swapCursor(getAllMeals());
                }
            }
        }).attachToRecyclerView(mMealList);

    }

    private boolean removeMeal(long id){
        return mDb.delete(MealContract.MealEntry.TABLE_NAME, MealContract.MealEntry._ID+"="+id, null) > 0;
    }

    private boolean getMeal(long id){
        String query = "SELECT * FROM " + MealContract.MealEntry.TABLE_NAME + " WHERE " + MealContract.MealEntry._ID + "=" + id;
        Cursor cur = mDb.rawQuery(query, null);
        while(cur.moveToNext()){
            String mealName = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_NAME));
            String ingredients = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_INGREDIENTS));
            String calories = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS));
            String fat = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT));
            String fatUnit = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT));
            String fatQuantity = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY));
            String protein = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN));
            String proteinUnit = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT));
            String proteinQuantity = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY));
            String carbs = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS));
            String carbsUnit = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT));
            String carbsQuantity = cur.getString(cur.getColumnIndex(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY));
            addMeal(mealName, ingredients, calories, fat, fatUnit, fatQuantity, protein, proteinUnit, proteinQuantity, carbs, carbsUnit, carbsQuantity);
        }
        return true;
    }

    private Cursor getAllMeals(){
        return mDb.query(
                MealContract.MealEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MealContract.MealEntry.COLUMN_TIMESTAMP
        );
    }
    private long addMeal(String name, String ingredients, String calories, String fat, String fatUnit, String fatQuantity, String protein, String proteinUnit, String proteinQuantity, String carbs, String carbsUnit, String carbsQuantity){
        ContentValues cv = new ContentValues();
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_NAME, name);
        cv.put(MealContract.MealPlannerEntry.COLUMN_INGREDIENTS, ingredients);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS, calories);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT, fat);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT, fatUnit);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY, fatQuantity);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN, protein);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT, proteinUnit);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY, proteinQuantity);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS, carbs);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT, carbsUnit);
        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY, carbsQuantity);
        return mDb.insert(MealContract.MealPlannerEntry.TABLE_NAME, null, cv);
    }
}
