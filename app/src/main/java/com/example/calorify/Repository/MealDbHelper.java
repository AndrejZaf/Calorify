package com.example.calorify.Repository;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MealDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mealplanner.db";
    private static final int DATABASE_VERSION = 5;

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
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY + " TEXT NOT NULL, " +
                MealContract.MealPlannerEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +" );";

        final String SQL_CREATE_MEAL_TABLE = "CREATE TABLE " + MealContract.MealEntry.TABLE_NAME + " (" +
                MealContract.MealEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MealContract.MealEntry.COLUMN_MEAL_NAME + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_INGREDIENTS + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS + " TEXT NOT NULL, " +
                //PROCRNT
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY + " TEXT NOT NULL, " +
                //FAT
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY + " TEXT NOT NULL, " +
                //CHOCDF
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY + " TEXT NOT NULL, " +
                //FASAT
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_QUANTITY + " TEXT NOT NULL, " +
                //FAPU
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_QUANTITY + " TEXT NOT NULL, " +
                //FIBTG
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_QUANTITY + " TEXT NOT NULL, " +
                //CHOLE
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_QUANTITY + " TEXT NOT NULL, " +
                //NA
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_QUANTITY + " TEXT NOT NULL, " +
                //CA
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_QUANTITY + " TEXT NOT NULL, " +
                //MG
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_QUANTITY + " TEXT NOT NULL, " +
                //K
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_QUANTITY + " TEXT NOT NULL, " +
                //FE
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_QUANTITY + " TEXT NOT NULL, " +
                //ZN
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_QUANTITY + " TEXT NOT NULL, " +
                //P
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_QUANTITY + " TEXT NOT NULL, " +
                //VITARAE
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_QUANTITY + " TEXT NOT NULL, " +
                //VITC
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_QUANTITY + " TEXT NOT NULL, " +
                //THIA
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_QUANTITY + " TEXT NOT NULL, " +
                //RIBF
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_QUANTITY + " TEXT NOT NULL, " +
                //NIA
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_QUANTITY + " TEXT NOT NULL, " +
                //VITB6A
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_QUANTITY + " TEXT NOT NULL, " +
                //FOLDFE
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_UNIT + " TEXT NOT NULL, " +
                MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_QUANTITY + " TEXT NOT NULL, " +

                MealContract.MealEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +" );";
        db.execSQL(SQL_CREATE_MEALLIST_TABLE);
        db.execSQL(SQL_CREATE_MEAL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + MealContract.MealPlannerEntry.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + MealContract.MealEntry.TABLE_NAME);
        onCreate(db);
    }
}
