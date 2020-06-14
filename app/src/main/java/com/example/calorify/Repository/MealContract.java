package com.example.calorify.Repository;

import android.provider.BaseColumns;

public class MealContract {
    public static final class MealPlannerEntry implements BaseColumns {
        public static final String TABLE_NAME = "mealPlanner";
        public static final String COLUMN_MEAL_NAME = "mealName";
        public static final String COLUMN_INGREDIENTS = "mealIngredients";
        public static final String COLUMN_MEAL_ANALYSIS = "mealCalories";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN = "mealCaloriesProtein";
        public static final String COLUMN_MEAL_ANALYSIS_FAT = "mealCaloriesFat";
        public static final String COLUMN_MEAL_ANALYSIS_SUGAR = "mealCaloriesSugar";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
