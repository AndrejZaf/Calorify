package com.example.calorify.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MealDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mealplanner.db";
    private static final int DATABASE_VERSION = 1;

    public MealDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MEALLIST_TABLE = "CREATE TABLE " + MealContract.MealPlannerEntry.TABLE_NAME + " (" +
                MealContract.MealPlannerEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_NAME + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_INGREDIENTS + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_SUGAR + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +" );";
        db.execSQL(SQL_CREATE_MEALLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MealContract.MealPlannerEntry.TABLE_NAME);
        onCreate(db);
    }
}
