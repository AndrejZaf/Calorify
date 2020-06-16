package com.example.calorify;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.ActionMenuItemView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorify.Adapters.MealAdapter;
import com.example.calorify.AddMealActivity;
import com.example.calorify.MainActivity;
import com.example.calorify.MealsActivity;
import com.example.calorify.Model.NutritionAnalysis;
import com.example.calorify.R;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;
import com.example.calorify.SettingsActivity;

public class HomeFragment extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener  {
    private Button addMealButton;
    private TextView mFatResult;
    private TextView mFatLabel;
    private TextView mFatUnit;
    private TextView mProteinResult;
    private TextView mProteinUnit;
    private TextView mProteinLabel;
    private TextView mCarbsResult;
    private TextView mCarbsLabel;
    private TextView mCarbsUnit;
    private Button listMealsButton;
    private SQLiteDatabase mDb;
    private RecyclerView mRv;
    private MealAdapter mAdapter;
    private TextView mCaloriesGoal;
    private Context mContext;
    private Integer calories;
    private TextView mRemaining;
    private Integer calorieGoal;
    private Integer remainingCalories;
    private TextView mCaloriesConsumed;
    private ProgressBar mProgressBar;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        Bundle bundle = this.getArguments();
//        NutritionAnalysis analysis = (NutritionAnalysis) this.getArguments().getSerializable("nutrition");
//        setHasOptionsMenu(true);
//        View view = inflater.inflate(R.layout.fragment_home, null, false);
//        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        AppCompatActivity activity = (AppCompatActivity) getActivity();
//        activity.setSupportActionBar(toolbar);
//        activity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_home, container,false);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        inflater.inflate(R.menu.meal_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        mFatResult = view.findViewById(R.id.fatResult);
        mFatUnit = view.findViewById(R.id.fatUnit);
        mFatLabel = view.findViewById(R.id.fatLabel);
        mProteinResult = view.findViewById(R.id.proteinResult);
        mProteinUnit = view.findViewById(R.id.proteinUnit);
        mProteinLabel = view.findViewById(R.id.proteinLabel);
        mCarbsResult = view.findViewById(R.id.carbsResult);
        mCarbsLabel = view.findViewById(R.id.carbsLabel);
        mCarbsUnit = view.findViewById(R.id.carbsUnit);
//        addMealButton = view.findViewById(R.id.addButton);
//        listMealsButton = view.findViewById(R.id.listMeals);
        mCaloriesGoal = view.findViewById(R.id.txt_calorie_goal);
        mRemaining = view.findViewById(R.id.calorieRemainingNumber);
        mProgressBar = view.findViewById(R.id.mainProgressBar);
        mCaloriesConsumed = view.findViewById(R.id.caloriesConsumed);
        mRv = view.findViewById(R.id.rv_meals_today);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRv.setLayoutManager(layoutManager);

//        addMealButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = getActivity();
//                Class destinationActivity = AddMealActivity.class;
//                Intent intent = new Intent(context, destinationActivity);
//                startActivity(intent);
//            }
//        });
//
//        listMealsButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), MealsActivity.class);
//                startActivity(intent);
//            }
//        });

//        Intent intent = getActivity().getIntent();
//        if(intent.hasExtra("nutrition")){
//            NutritionAnalysis analysis = (NutritionAnalysis) intent.getSerializableExtra("nutrition");
//        }


        MealDbHelper dbHelper = new MealDbHelper(getActivity());
        mDb = dbHelper.getReadableDatabase();
        //Works properly
        Cursor cursor = getAllMealsToday();
        Log.d("Cursor", String.valueOf(cursor.getCount()));
        mAdapter = new MealAdapter(getActivity(), cursor);
        mRv.setAdapter(mAdapter);

        setupSharedPreference();

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }
            //Test Comment
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                long id = (long) viewHolder.itemView.getTag();
                removeMeal(id);
                mAdapter.swapCursor(getAllMealsToday());
                Fragment fragment = new HomeFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.commit();
            }
        }).attachToRecyclerView(mRv);
//        Log.d("Cursor", String.valueOf(cursor.getCount()));
        int fatNumber = 0;
        int proteinNumber = 0;
        int carbsNumber = 0;
        String carbsLabel = null;
        String carbsUnit = null;
        String fatLabel = null;
        String fatUnit = null;
        String proteinLabel = null;
        String proteinUnit = null;
        boolean flag = true;
        calories = 0;
        while(cursor.moveToNext()){
            if(flag){
                carbsLabel = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS));
                carbsUnit = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT));
                fatLabel = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT));
                fatUnit = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT));
                proteinLabel = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN));
                proteinUnit = cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT));
                flag = false;
            }
            calories += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS)));
            fatNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY)));
            proteinNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY)));
            carbsNumber += (int)Double.parseDouble(cursor.getString(cursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY)));
        }
        Integer remainingCalories = calorieGoal - calories;
        Integer percentage = (int)((remainingCalories*100)/calorieGoal);
        percentage = 100 - percentage;
        mProgressBar.setProgress(percentage);
        mRemaining.setText(remainingCalories.toString());
//        Log.d("Percentage:", percentage.toString());
        mProteinResult.setText(String.valueOf(proteinNumber));
        mFatResult.setText(String.valueOf(fatNumber));
        mCarbsResult.setText(String.valueOf(carbsNumber));
        mCarbsUnit.setText(carbsUnit);
        mCarbsLabel.setText(carbsLabel);
        mFatUnit.setText(fatUnit);
        mFatLabel.setText(fatLabel);
        mProteinLabel.setText(proteinLabel);
        mProteinUnit.setText(proteinUnit);
        mCaloriesConsumed.setText(calories.toString());
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
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.meal_menu, menu);
//        return true;
//    }

//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int id = item.getItemId();
//        if(id == R.id.action_settings){
//            Intent intent = new Intent(getActivity(), SettingsActivity.class);
//            startActivity(intent);
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }



    private void setupSharedPreference(){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        mCaloriesGoal.setText(sharedPreferences.getString("calorie_goal", String.valueOf(2000)));
        calorieGoal = Integer.parseInt(sharedPreferences.getString("calorie_goal", String.valueOf(2000)));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals(getString(R.string.calories_key))){
            calorieGoal = Integer.parseInt(sharedPreferences.getString("calorie_goal", String.valueOf(2000)));
        }
    }
    private boolean removeMeal(long id){
        return mDb.delete(MealContract.MealPlannerEntry.TABLE_NAME, MealContract.MealPlannerEntry._ID+"="+id, null) > 0;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        PreferenceManager.getDefaultSharedPreferences(getActivity()).unregisterOnSharedPreferenceChangeListener(this);
    }
}
