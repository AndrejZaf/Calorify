package com.example.calorify.Repository;

import android.provider.BaseColumns;

public class MealContract {
    public static final class MealPlannerEntry implements BaseColumns {
        public static final String TABLE_NAME = "mealPlanner";
        public static final String COLUMN_MEAL_NAME = "mealName";
        public static final String COLUMN_INGREDIENTS = "mealIngredients";
        public static final String COLUMN_MEAL_ANALYSIS = "mealCalories";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN = "mealCaloriesProtein";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT = "mealCaloriesProteinUnit";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY = "mealCaloriesProteinQuantity";
        public static final String COLUMN_MEAL_ANALYSIS_FAT = "mealCaloriesFat";
        public static final String COLUMN_MEAL_ANALYSIS_FAT_UNIT = "mealCaloriesFatUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FAT_QUANTITY = "mealCaloriesFatQuantity_QUANTITY";
        public static final String COLUMN_MEAL_ANALYSIS_CARBS = "mealCaloriesCarbs";
        public static final String COLUMN_MEAL_ANALYSIS_CARBS_UNIT = "mealCaloriesCarbsUnit";
        public static final String COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY = "mealCaloriesCarbsQuantity";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

    public static final class MealEntry implements BaseColumns{
        public static final String TABLE_NAME = "meal";
        public static final String COLUMN_MEAL_NAME = "mealName";
        public static final String COLUMN_INGREDIENTS = "mealIngredients";
        public static final String COLUMN_MEAL_ANALYSIS = "mealCalories";
        //PROCRNT
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN = "mealCaloriesProtein";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT = "mealCaloriesProteinUnit";
        public static final String COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY = "mealCaloriesProteinQuantity";
        //FAT
        public static final String COLUMN_MEAL_ANALYSIS_FAT = "mealCaloriesFat";
        public static final String COLUMN_MEAL_ANALYSIS_FAT_UNIT = "mealCaloriesFatUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FAT_QUANTITY = "mealCaloriesFatQuantity_QUANTITY";
        //CHOCDF
        public static final String COLUMN_MEAL_ANALYSIS_CARBS = "mealCaloriesCarbs";
        public static final String COLUMN_MEAL_ANALYSIS_CARBS_UNIT = "mealCaloriesCarbsUnit";
        public static final String COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY = "mealCaloriesCarbsQuantity";
        //FASAT
        public static final String COLUMN_MEAL_ANALYSIS_FASAT = "mealCaloriesFasat";
        public static final String COLUMN_MEAL_ANALYSIS_FASAT_UNIT = "mealCaloriesFasatUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FASAT_QUANTITY = "mealCaloriesFasatQuantity";
        //FAPU
        public static final String COLUMN_MEAL_ANALYSIS_FAPU = "mealCaloriesFapu";
        public static final String COLUMN_MEAL_ANALYSIS_FAPU_UNIT = "mealCaloriesFapuUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FAPU_QUANTITY = "mealCaloriesFapuQuantity";
        //FIBTG
        public static final String COLUMN_MEAL_ANALYSIS_FIBTG = "mealCaloriesFibtg";
        public static final String COLUMN_MEAL_ANALYSIS_FIBTG_UNIT = "mealCaloriesFibtgUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FIBTG_QUANTITY = "mealCaloriesFibtgQuantity";
        //CHOLE
        public static final String COLUMN_MEAL_ANALYSIS_CHOLE = "mealCaloriesChole";
        public static final String COLUMN_MEAL_ANALYSIS_CHOLE_UNIT = "mealCaloriesCholeUnit";
        public static final String COLUMN_MEAL_ANALYSIS_CHOLE_QUANTITY = "mealCaloriesCholeQuantity";
        //NA
        public static final String COLUMN_MEAL_ANALYSIS_NA = "mealCaloriesNa";
        public static final String COLUMN_MEAL_ANALYSIS_NA_UNIT = "mealCaloriesNaUnit";
        public static final String COLUMN_MEAL_ANALYSIS_NA_QUANTITY = "mealCaloriesNaQuantity";
        //CA
        public static final String COLUMN_MEAL_ANALYSIS_CA = "mealCaloriesCa";
        public static final String COLUMN_MEAL_ANALYSIS_CA_UNIT = "mealCaloriesCaUnit";
        public static final String COLUMN_MEAL_ANALYSIS_CA_QUANTITY = "mealCaloriesCaQuantity";
        //MG
        public static final String COLUMN_MEAL_ANALYSIS_MG = "mealCaloriesMg";
        public static final String COLUMN_MEAL_ANALYSIS_MG_UNIT = "mealCaloriesMgUnit";
        public static final String COLUMN_MEAL_ANALYSIS_MG_QUANTITY = "mealCaloriesMgQuantity";
        //K
        public static final String COLUMN_MEAL_ANALYSIS_K = "mealCaloriesK";
        public static final String COLUMN_MEAL_ANALYSIS_K_UNIT = "mealCaloriesKUnit";
        public static final String COLUMN_MEAL_ANALYSIS_K_QUANTITY = "mealCaloriesKQuantity";
        //FE
        public static final String COLUMN_MEAL_ANALYSIS_FE = "mealCaloriesFe";
        public static final String COLUMN_MEAL_ANALYSIS_FE_UNIT = "mealCaloriesFeUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FE_QUANTITY = "mealCaloriesFeQuantity";
        //ZN
        public static final String COLUMN_MEAL_ANALYSIS_ZN = "mealCaloriesZn";
        public static final String COLUMN_MEAL_ANALYSIS_ZN_UNIT = "mealCaloriesZnUnit";
        public static final String COLUMN_MEAL_ANALYSIS_ZN_QUANTITY = "mealCaloriesZnQuantity";
        //P
        public static final String COLUMN_MEAL_ANALYSIS_P = "mealCaloriesP";
        public static final String COLUMN_MEAL_ANALYSIS_P_UNIT = "mealCaloriesPUnit";
        public static final String COLUMN_MEAL_ANALYSIS_P_QUANTITY = "mealCaloriesPQuantity";
        //VITA_RAE
        public static final String COLUMN_MEAL_ANALYSIS_VITARAE = "mealCaloriesVitaRae";
        public static final String COLUMN_MEAL_ANALYSIS_VITARAE_UNIT = "mealCaloriesVitaRaeUnit";
        public static final String COLUMN_MEAL_ANALYSIS_VITARAE_QUANTITY = "mealCaloriesVitaRaeQuantity";
        //VITC
        public static final String COLUMN_MEAL_ANALYSIS_VITC = "mealCaloriesVitc";
        public static final String COLUMN_MEAL_ANALYSIS_VITC_UNIT = "mealCaloriesVitcUnit";
        public static final String COLUMN_MEAL_ANALYSIS_VITC_QUANTITY = "mealCaloriesVitcQuantity";
        //THIA
        public static final String COLUMN_MEAL_ANALYSIS_THIA = "mealCaloriesThia";
        public static final String COLUMN_MEAL_ANALYSIS_THIA_UNIT = "mealCaloriesThiaUnit";
        public static final String COLUMN_MEAL_ANALYSIS_THIA_QUANTITY = "mealCaloriesThiaQuantity";
        //RIBF
        public static final String COLUMN_MEAL_ANALYSIS_RIBF = "mealCaloriesRibf";
        public static final String COLUMN_MEAL_ANALYSIS_RIBF_UNIT = "mealCaloriesRibfUnit";
        public static final String COLUMN_MEAL_ANALYSIS_RIBF_QUANTITY = "mealCaloriesRibfQuantity";
        //NIA
        public static final String COLUMN_MEAL_ANALYSIS_NIA = "mealCaloriesNia";
        public static final String COLUMN_MEAL_ANALYSIS_NIA_UNIT = "mealCaloriesNiaUnit";
        public static final String COLUMN_MEAL_ANALYSIS_NIA_QUANTITY = "mealCaloriesNiaQuantity";
        //VITB6A
        public static final String COLUMN_MEAL_ANALYSIS_VITB6A = "mealCaloriesVitb6a";
        public static final String COLUMN_MEAL_ANALYSIS_VITB6A_UNIT = "mealCaloriesVitb6aUnit";
        public static final String COLUMN_MEAL_ANALYSIS_VITB6A_QUANTITY = "mealCaloriesVitb6aQuantity";
        //FOLDFE
        public static final String COLUMN_MEAL_ANALYSIS_FOLDFE = "mealCaloriesFoldfe";
        public static final String COLUMN_MEAL_ANALYSIS_FOLDFE_UNIT = "mealCaloriesFoldfeUnit";
        public static final String COLUMN_MEAL_ANALYSIS_FOLDFE_QUANTITY = "mealCaloriesFoldfeQuantity";

        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
