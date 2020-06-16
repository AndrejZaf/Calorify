package com.example.calorify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
//    private Button addMealButton;
//    private TextView mFatResult;
//    private TextView mProteinResult;
//    private TextView mCarbsResult;
//    private Button listMealsButton;
//    private SQLiteDatabase mDb;
//    private RecyclerView mRv;
//    private MealAdapter mAdapter;
//    private TextView mCaloriesGoal;
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();

        bottomNav = findViewById(R.id.bot_navigation);
        hideBottomBar(false);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new
            BottomNavigationView.OnNavigationItemSelectedListener(){
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
                    switch(item.getItemId()){
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_add:
                            selectedFragment = new AddMealFragment();
                            break;
                        case R.id.nav_search:
                            selectedFragment = new MealsFragment();
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();
                    return true;
                }
            };
        //
//        mFatResult = findViewById(R.id.fatResult);
//        mProteinResult = findViewById(R.id.proteinResult);
//        mCarbsResult = findViewById(R.id.carbsResult);
//        addMealButton = findViewById(R.id.addButton);
//        listMealsButton = findViewById(R.id.listMeals);
//        mCaloriesGoal = findViewById(R.id.txt_calorie_goal);
//
//        mRv = findViewById(R.id.rv_meals_today);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        mRv.setLayoutManager(layoutManager);
//
//        addMealButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = MainActivity.this;
//                Class destinationActivity = AddMealActivity.class;
//                Intent intent = new Intent(context, destinationActivity);
//                startActivity(intent);
//            }
//        });
//
//        listMealsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MainActivity.this, MealsActivity.class);
//                startActivity(intent);
//            }
//        });
//
//        Intent intent = getIntent();
//        if(intent.hasExtra("nutrition")){
//            NutritionAnalysis analysis = (NutritionAnalysis) intent.getSerializableExtra("nutrition");
//        }
//
//
//        MealDbHelper dbHelper = new MealDbHelper(this);
//        mDb = dbHelper.getReadableDatabase();
//        //Works properly
//        Cursor cursor = getAllMealsToday();
//
//        mAdapter = new MealAdapter(this, cursor);
//        mRv.setAdapter(mAdapter);
//
//        setupSharedPreference();
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
//                return false;
//            }
//            //Test Comment
//            @Override
//            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
//                long id = (long) viewHolder.itemView.getTag();
//                removeMeal(id);
//                mAdapter.swapCursor(getAllMealsToday());
//            }
//        }).attachToRecyclerView(mRv);
////        Log.d("Cursor", String.valueOf(cursor.getCount()));
//        int fatNumber = 0;
//        int proteinNumber = 0;
//        int carbsNumber = 0;
//        while(cursor.moveToNext()){
//            fatNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY)));
//            proteinNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY)));
//            carbsNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY)));
//        }
//        mProteinResult.setText(String.valueOf(proteinNumber));
//        mFatResult.setText(String.valueOf(fatNumber));
//        mCarbsResult.setText(String.valueOf(carbsNumber));



//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
//    }
//
//    public Cursor getAllMealsToday(){
//        return mDb.query(
//                MealContract.MealPlannerEntry.TABLE_NAME,
//                null,
//                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP+">=date('now','localtime','start of day')",
//                null,
//                null,
//                null,
//                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP
//        );
//    }
//


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meal_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.nav_settings){
            Fragment fragment = new SettingsFragment();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideBottomBar(boolean isHidden){
        bottomNav.setVisibility(isHidden ? View.GONE : View.VISIBLE);
    }
//
//    private void setupSharedPreference(){
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mCaloriesGoal.setText(sharedPreferences.getString("calorie_goal", String.valueOf(2000)));
//        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
//    }
//
//
//    @Override
//    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
//        if(key.equals(getString(R.string.calories_key))){
//            mCaloriesGoal.setText(sharedPreferences.getString(key, getResources().getString(R.string.default_calories_goal)));
//        }
//    }
//    private boolean removeMeal(long id){
//        return mDb.delete(MealContract.MealPlannerEntry.TABLE_NAME, MealContract.MealPlannerEntry._ID+"="+id, null) > 0;
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mAdapter.swapCursor(getAllMealsToday());
//        Cursor cursor = getAllMealsToday();
//        int fatNumber = 0;
//        int proteinNumber = 0;
//        int carbsNumber = 0;
//        while(cursor.moveToNext()){
//            fatNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY)));
//            proteinNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY)));
//            carbsNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY)));
//        }
//        mProteinResult.setText(String.valueOf(proteinNumber));
//        mFatResult.setText(String.valueOf(fatNumber));
//        mCarbsResult.setText(String.valueOf(carbsNumber));
//    }
}
