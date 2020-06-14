package com.example.calorify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

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
                long id = (long) viewHolder.itemView.getTag();
                removeMeal(id);
                mAdapter.swapCursor(getAllMeals());
            }
        }).attachToRecyclerView(mMealList);

    }

    private boolean removeMeal(long id){
        return mDb.delete(MealContract.MealPlannerEntry.TABLE_NAME, MealContract.MealPlannerEntry._ID+"="+id, null) > 0;
    }

    private Cursor getAllMeals(){
        return mDb.query(
                MealContract.MealPlannerEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP
        );
    }
}
