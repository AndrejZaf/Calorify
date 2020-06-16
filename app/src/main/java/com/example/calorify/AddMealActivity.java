package com.example.calorify;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.calorify.Model.NutritionAnalysis;
import com.example.calorify.Repository.MealContract;
import com.example.calorify.Repository.MealDbHelper;
import com.example.calorify.RetrofitData.EdamamClient;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;

public class AddMealActivity extends AppCompatActivity{
//    private EditText mMealName;
//    private EditText mIngredients;
//    private Button mButtonCalculation;
//    private Button mAddButton;
//    private Button mSaveButton;
//    public NutritionAnalysis resultAnalysis;
//    private SQLiteDatabase mDb;
//
//    private final String api_id = "1a1e1904";
//    private final String api_key = "607aa715a3e0a0479f1736694f98d190";
//    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);
//        mButtonCalculation = findViewById(R.id.calculateMealButton);
//        mMealName = findViewById(R.id.mealName);
//        mIngredients = findViewById(R.id.mealIngredients);
//        mAddButton = findViewById(R.id.buttonAddMealPlan);
//        mSaveButton = findViewById(R.id.buttonSaveMeal);
//
//        MealDbHelper dbHelper = new MealDbHelper(context);
//        mDb = dbHelper.getWritableDatabase();
//
//
//        mButtonCalculation.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EdamamAsyncTask task = new EdamamAsyncTask();
//                task.execute(api_id, api_key, mIngredients.getText().toString());
//            }
//        });
//
//        mSaveButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addMeal(mSaveButton);
//                Toast.makeText(AddMealActivity.this, "Meal has been saved", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mAddButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Context context = AddMealActivity.this;
//                Class destinationActivity = MainActivity.class;
//                Intent intent = new Intent(context, destinationActivity);
//                intent.putExtra("nutrition", resultAnalysis);
//                addToMealList(mAddButton);
//                startActivity(intent);
//            }
//        });

    }
//
//    class EdamamAsyncTask extends AsyncTask<String, Integer, NutritionAnalysis> {
//
//        @Override
//        protected NutritionAnalysis doInBackground(String... strings) {
//            String[] array = strings[2].split("\n");
//            HashMap<String, String[]> params = new HashMap<>();
//            params.put("ingr", array);
//            String strRequestBody = new Gson().toJson(params);
//
//            final RequestBody requestBody = RequestBody.create(MediaType.
//                    parse("application/json"),strRequestBody);
//            final Call<NutritionAnalysis> call = EdamamClient.getService().getCalculation(strings[0],strings[1],requestBody);
//            Log.d("Url: ", call.request().url().toString());
//            try{
//                return call.execute().body();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            // Implement a progress bar for the loading animation
//        }
//
//        @Override
//        protected void onPostExecute(NutritionAnalysis nutritionAnalysis) {
////        super.onPostExecute(nutritionAnalysis);
//            resultAnalysis = nutritionAnalysis;
//            Toast.makeText(getApplicationContext(), resultAnalysis.getCalories().toString(), Toast.LENGTH_SHORT).show();
//        }
//
//    }
//
//    public void addMeal(View view){
//        if(mMealName.getText().length() == 0 || mIngredients.getText().length() == 0){
//            return;
//        }
//        String mealName = mMealName.getText().toString();
//        String ingredients = mIngredients.getText().toString();
//        String calories = resultAnalysis.getCalories().toString();
//        //fat
//        String fat = resultAnalysis.getTotalNutrients().getFAT().getLabel();
//        String fatUnit = resultAnalysis.getTotalNutrients().getFAT().getUnit();
//        String fatQuantity = resultAnalysis.getTotalNutrients().getFAT().getQuantity().toString();
//        //protein
//        String protein = resultAnalysis.getTotalNutrients().getPROCNT().getLabel();
//        String proteinUnit = resultAnalysis.getTotalNutrients().getPROCNT().getUnit();
//        String proteinQuantity = resultAnalysis.getTotalNutrients().getPROCNT().getQuantity().toString();
//        //carbs
//        String carbs = resultAnalysis.getTotalNutrients().getCHOCDF().getLabel();
//        String carbsUnit = resultAnalysis.getTotalNutrients().getCHOCDF().getUnit();
//        String carbsQuantity = resultAnalysis.getTotalNutrients().getCHOCDF().getQuantity().toString();
//        //fasat
//        String fasat = resultAnalysis.getTotalNutrients().getFASAT().getLabel();
//        String fasatUnit = resultAnalysis.getTotalNutrients().getFASAT().getUnit();
//        String fasatQuantity = resultAnalysis.getTotalNutrients().getFASAT().getQuantity().toString();
//        //fapu
//        String fapu = resultAnalysis.getTotalNutrients().getFAPU().getLabel();
//        String fapuUnit = resultAnalysis.getTotalNutrients().getFAPU().getUnit();
//        String fapuQuantity = resultAnalysis.getTotalNutrients().getFAPU().getQuantity().toString();
//        //fibtg
//        String fibtg = resultAnalysis.getTotalNutrients().getFIBTG().getLabel();
//        String fibtgUnit = resultAnalysis.getTotalNutrients().getFIBTG().getUnit();
//        String fibtgQuantity = resultAnalysis.getTotalNutrients().getFIBTG().getQuantity().toString();
//        //Chole
//        String chole = resultAnalysis.getTotalNutrients().getCHOLE().getLabel();
//        String choleUnit = resultAnalysis.getTotalNutrients().getCHOLE().getUnit();
//        String choleQuantity = resultAnalysis.getTotalNutrients().getCHOLE().getQuantity().toString();
//        //Na
//        String na = resultAnalysis.getTotalNutrients().getNA().getLabel();
//        String naUnit = resultAnalysis.getTotalNutrients().getNA().getUnit();
//        String naQuantity = resultAnalysis.getTotalNutrients().getNA().getQuantity().toString();
//        //Ca
//        String ca = resultAnalysis.getTotalNutrients().getCA().getLabel();
//        String caUnit = resultAnalysis.getTotalNutrients().getCA().getUnit();
//        String caQuantity = resultAnalysis.getTotalNutrients().getCA().getQuantity().toString();
//        //MG
//        String mg = resultAnalysis.getTotalNutrients().getMG().getLabel();
//        String mgUnit = resultAnalysis.getTotalNutrients().getMG().getUnit();
//        String mgQuantity = resultAnalysis.getTotalNutrients().getMG().getQuantity().toString();
//        //k
//        String k = resultAnalysis.getTotalNutrients().getK().getLabel();
//        String kUnit = resultAnalysis.getTotalNutrients().getK().getUnit();
//        String kQuantity = resultAnalysis.getTotalNutrients().getK().getQuantity().toString();
//        //Vitarae
//        String vitarae = resultAnalysis.getTotalNutrients().getVITARAE().getLabel();
//        String vitaraeUnit = resultAnalysis.getTotalNutrients().getVITARAE().getUnit();
//        String vitaraeQuantity = resultAnalysis.getTotalNutrients().getVITARAE().getQuantity().toString();
//        //Vitc
//        String vitc = resultAnalysis.getTotalNutrients().getVITC().getLabel();
//        String vitcUnit = resultAnalysis.getTotalNutrients().getVITC().getUnit();
//        String vitcQuantity = resultAnalysis.getTotalNutrients().getVITC().getQuantity().toString();
//        //Thia
//        String thia = resultAnalysis.getTotalNutrients().getTHIA().getLabel();
//        String thiaUnit = resultAnalysis.getTotalNutrients().getTHIA().getUnit();
//        String thiaQuantity = resultAnalysis.getTotalNutrients().getTHIA().getQuantity().toString();
//        //Ribf
//        String ribf = resultAnalysis.getTotalNutrients().getRIBF().getLabel();
//        String ribfUnit = resultAnalysis.getTotalNutrients().getRIBF().getUnit();
//        String ribfQuantity = resultAnalysis.getTotalNutrients().getRIBF().getQuantity().toString();
//        //Nia
//        String nia = resultAnalysis.getTotalNutrients().getNIA().getLabel();
//        String niaUnit = resultAnalysis.getTotalNutrients().getNIA().getUnit();
//        String niaQuantity = resultAnalysis.getTotalNutrients().getNIA().getQuantity().toString();
//        //Vit6ba
//        String vit6ba = resultAnalysis.getTotalNutrients().getVITB6A().getLabel();
//        String vit6baUnit = resultAnalysis.getTotalNutrients().getVITB6A().getUnit();
//        String vit6baQuantity = resultAnalysis.getTotalNutrients().getVITB6A().getQuantity().toString();
//        //Foldfe
//        String foldfe = resultAnalysis.getTotalNutrients().getFOLDFE().getLabel();
//        String foldfeUnit = resultAnalysis.getTotalNutrients().getFOLDFE().getUnit();
//        String foldfeQuantity = resultAnalysis.getTotalNutrients().getFOLDFE().getQuantity().toString();
//        //Fe
//        String fe = resultAnalysis.getTotalNutrients().getFE().getLabel();
//        String feUnit = resultAnalysis.getTotalNutrients().getFE().getUnit();
//        String feQuantity = resultAnalysis.getTotalNutrients().getFE().getQuantity().toString();
//        String zn = resultAnalysis.getTotalNutrients().getZN().getLabel();
//        String znUnit = resultAnalysis.getTotalNutrients().getZN().getUnit();
//        String znQuantity = resultAnalysis.getTotalNutrients().getZN().getQuantity().toString();
//
//        String p = resultAnalysis.getTotalNutrients().getP().getLabel();
//        String pUnit = resultAnalysis.getTotalNutrients().getP().getUnit();
//        String pQuantity = resultAnalysis.getTotalNutrients().getP().getQuantity().toString();
//
//        addMeal(mealName, ingredients, calories, fat, fatUnit, fatQuantity, protein, proteinUnit, proteinQuantity, carbs, carbsUnit, carbsQuantity,
//                fasat, fasatUnit, fasatQuantity, fapu, fapuUnit, fapuQuantity, fibtg, fibtgUnit, fibtgQuantity, chole, choleUnit, choleQuantity, na, naUnit, naQuantity,
//                ca, caUnit, caQuantity, mg, mgUnit, mgQuantity, k, kUnit, kQuantity, vitarae, vitaraeUnit, vitaraeQuantity, vitc, vitcUnit, vitcQuantity, thia, thiaUnit, thiaQuantity,
//                ribf, ribfUnit, ribfQuantity, nia,niaUnit,niaQuantity, vit6ba, vit6baUnit, vit6baQuantity, foldfe, foldfeUnit, foldfeQuantity, zn, znUnit, znQuantity, fe, feUnit, feQuantity, p, pUnit, pQuantity);
//    }
//
//    public void addToMealList(View view){
//        if(mMealName.getText().length() == 0 || mIngredients.getText().length() == 0){
//            return;
//        }
//        String mealName = mMealName.getText().toString();
//        String ingredients = mIngredients.getText().toString();
//        String calories = resultAnalysis.getCalories().toString();
//        String fat = resultAnalysis.getTotalNutrients().getFAT().getLabel();
//        String fatUnit = resultAnalysis.getTotalNutrients().getFAT().getUnit();
//        String fatQuantity = resultAnalysis.getTotalNutrients().getFAT().getQuantity().toString();
//        String protein = resultAnalysis.getTotalNutrients().getPROCNT().getLabel();
//        String proteinUnit = resultAnalysis.getTotalNutrients().getPROCNT().getUnit();
//        String proteinQuantity = resultAnalysis.getTotalNutrients().getPROCNT().getQuantity().toString();
//        String carbs = resultAnalysis.getTotalNutrients().getCHOCDF().getLabel();
//        String carbsUnit = resultAnalysis.getTotalNutrients().getCHOCDF().getUnit();
//        String carbsQuantity = resultAnalysis.getTotalNutrients().getCHOCDF().getQuantity().toString();
//        addMeal(mealName, ingredients, calories, fat, fatUnit, fatQuantity, protein, proteinUnit, proteinQuantity, carbs, carbsUnit, carbsQuantity);
//
//        mMealName.getText().clear();
//        mIngredients.getText().clear();
//    }
//
//    private long addMeal(String name, String ingredients, String calories, String fat, String fatUnit, String fatQuantity, String protein, String proteinUnit, String proteinQuantity, String carbs, String carbsUnit, String carbsQuantity){
//        ContentValues cv = new ContentValues();
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_NAME, name);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_INGREDIENTS, ingredients);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS, calories);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT, fat);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT, fatUnit);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY, fatQuantity);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN, protein);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT, proteinUnit);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY, proteinQuantity);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS, carbs);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT, carbsUnit);
//        cv.put(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY, carbsQuantity);
//        return mDb.insert(MealContract.MealPlannerEntry.TABLE_NAME, null, cv);
//    }
//
//
//    private long addMeal(String name, String ingredients, String calories,
//                         String fat, String fatUnit, String fatQuantity,
//                         String protein, String proteinUnit, String proteinQuantity,
//                         String carbs, String carbsUnit, String carbsQuantity,
//                         String fasat, String fasatUnit, String fasatQuantity,
//                         String fapu, String fapuUnit, String fapuQuantity,
//                         String fitbg, String fitbgUnit, String fitbgQuantity,
//                         String chole, String choleUnit, String choleQuantity,
//                         String na, String naUnit, String naQuantity,
//                         String calcium, String calciumUnit, String calciumQuantity,
//                         String mg, String mgUnit, String mgQuantity,
//                         String potassiumK, String potassiumKUnit, String potassiumKQuantity,
//                         String vitarae, String vitaraeUnit, String vitaraeQuantity,
//                         String vitc, String vitcUnit, String vitcQuantity,
//                         String thia, String thiaUnit, String thiaQuantity,
//                         String ribf, String ribfUnit, String ribfQuantity,
//                         String nia, String niaUnit, String niaQuantity,
//                         String vit6ba, String vit6baUnit, String vit6baQuantity,
//                         String foldfe, String foldfeUnit, String foldfeQuantity,
//                         String zn, String znUnit, String znQuantity,
//                         String fe, String feUnit, String feQuantity,
//                         String p, String pUnit, String pQuantity
//    ){
//        ContentValues cv = new ContentValues();
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_NAME, name);
//        cv.put(MealContract.MealEntry.COLUMN_INGREDIENTS, ingredients);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS, calories);
//        //Protein
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN, protein);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT, proteinUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY, proteinQuantity);
//        //Fat
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT, fat);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT, fatUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY, fatQuantity);
//        //Carbs
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS, carbs);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT, carbsUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY, carbsQuantity);
//        //Fasat
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT, fasat);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_UNIT, fasatUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_QUANTITY, fasatQuantity);
//        //Fapu
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU, fapu);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_UNIT, fapuUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_QUANTITY, fapuQuantity);
//        //Fibtg
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG, fitbg);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_UNIT, fitbgUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_QUANTITY, fitbgQuantity);
//        //Chole
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE, chole);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_UNIT, choleUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_QUANTITY, choleQuantity);
//        //NA
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA, na);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_UNIT, naUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_QUANTITY, naQuantity);
//        //Calcium
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA, calcium);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_UNIT, calciumUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_QUANTITY, calciumQuantity);
//        //Mg
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG, mg);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_UNIT, mgUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_QUANTITY, mgQuantity);
//        //Potassium
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K, potassiumK);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_UNIT, potassiumKUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_QUANTITY, potassiumKQuantity);
//        //Vitarae
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE, vitarae);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_UNIT, vitaraeUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_QUANTITY, vitaraeQuantity);
//        //Vitc
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC, vitc);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_UNIT, vitcUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_QUANTITY, vitcQuantity);
//        //Thia
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA, thia);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_UNIT, thiaUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_QUANTITY, thiaQuantity);
//        //Ribf
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF, ribf);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_UNIT, ribfUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_QUANTITY, ribfQuantity);
//        //Nia
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA, nia);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_UNIT, niaUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_QUANTITY, niaQuantity);
//        //Vit6ba
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A, vit6ba);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_UNIT, vit6baUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_QUANTITY, vit6baQuantity);
//        //Foldfe
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE, foldfe);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_UNIT, foldfeUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_QUANTITY, foldfeQuantity);
//        //Fe
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE, fe);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_UNIT, feUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_QUANTITY, feQuantity);
//        //Zn
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN, zn);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_UNIT, znUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_QUANTITY, znQuantity);
//        //P
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P, p);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_UNIT, pUnit);
//        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_QUANTITY, pQuantity);
//        return mDb.insert(MealContract.MealEntry.TABLE_NAME, null, cv);
//    }
}
