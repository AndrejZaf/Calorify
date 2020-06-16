package com.example.calorify;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class AddMealFragment extends Fragment {
    private EditText mMealName;
    private EditText mIngredients;
    private Button mButtonCalculation;
    private Button mAddButton;
    private Button mSaveButton;
    public NutritionAnalysis resultAnalysis;
    private SQLiteDatabase mDb;
    private ProgressBar mProgressBar;
    private TextView mCalorieResult;
    private TextView mCalorieLabel;
    private TextView mProteinResult;
    private TextView mProteinUnit;
    private TextView mProteinLabel;
    private TextView mCarbsLabel;
    private TextView mCarbsUnit;
    private TextView mCarbsResult;
    private TextView mFatLabel;
    private TextView mFatUnit;
    private TextView mFatResult;
    private TextView fasatResult;
    private TextView fasatUnit;
    private TextView fasatLabel;
    private TextView fapuResult;
    private TextView fapuUnit;
    private TextView fapuLabel;
    private TextView sugarResult;
    private TextView sugarUnit;
    private TextView sugarLabel;
    private TextView fibtgResult;
    private TextView fibtgLabel;
    private TextView fibtgUnit;
    private TextView naResult;
    private TextView naUnit;
    private TextView naLabel;
    private TextView caResult;
    private TextView caUnit;
    private TextView caLabel;
    private TextView mgLabel;
    private TextView mgResult;
    private TextView mgUnit;
    private TextView kResult;
    private TextView kUnit;
    private TextView kLabel;
    private TextView feLabel;
    private TextView feUnit;
    private TextView feResult;
    private TextView znLabel;
    private TextView znUnit;
    private TextView znResult;
    private TextView pLabel;
    private TextView pUnit;
    private TextView pResult;
    private TextView vitaraeLabel;
    private TextView vitaraeResult;
    private TextView vitaraeUnit;
    private TextView vitcLabel;
    private TextView vitcUnit;
    private TextView vitcResult;
    private TextView thiaLabel;
    private TextView thiaUnit;
    private TextView thiaResult;
    private TextView vitb6aLabel;
    private TextView vitb6aResult;
    private TextView vitb6aUnit;
    private TextView choleLabel;
    private TextView choleUnit;
    private TextView choleResult;
    private TextView ribfLabel;
    private TextView ribfResult;
    private TextView ribfUnit;
    private GridLayout gridLayout;
    Integer value;
    private final String api_id = "1a1e1904";
    private final String api_key = "607aa715a3e0a0479f1736694f98d190";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_meal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();
        mButtonCalculation = view.findViewById(R.id.calculateMealButton);
        mMealName = view.findViewById(R.id.mealName);
        mIngredients = view.findViewById(R.id.mealIngredients);
        mAddButton = view.findViewById(R.id.buttonAddMealPlan);
        mSaveButton = view.findViewById(R.id.buttonSaveMeal);
        mProgressBar = view.findViewById(R.id.analysisProgressBar);

        //TextViews for results
        //Calories
        mCalorieResult = view.findViewById(R.id.caloriesResult);
        mCalorieLabel = view.findViewById(R.id.calorieLabel);
        //Proteins
        mProteinLabel = view.findViewById(R.id.proteinLabel);
        mProteinResult = view.findViewById(R.id.proteinResult);
        mProteinUnit = view.findViewById(R.id.proteinUnit);
        //Carbs
        mCarbsUnit = view.findViewById(R.id.carbsUnit);
        mCarbsLabel = view.findViewById(R.id.carbsLabel);
        mCarbsResult = view.findViewById(R.id.carbsResult);
        //Fat
        mFatResult = view.findViewById(R.id.fatResult);
        mFatLabel = view.findViewById(R.id.fatLabel);
        mFatUnit = view.findViewById(R.id.fatUnit);
        //Fasat
        fasatLabel = view.findViewById(R.id.fasatLabel);
        fasatUnit = view.findViewById(R.id.fasatUnit);
        fasatResult = view.findViewById(R.id.fasatResult);
        //Fapu
        fapuLabel = view.findViewById(R.id.fapuLabel);
        fapuUnit = view.findViewById(R.id.fapuUnit);
        fapuResult = view.findViewById(R.id.fapuResult);
        //Sugar
        sugarLabel = view.findViewById(R.id.sugarLabel);
        sugarUnit = view.findViewById(R.id.sugarUnit);
        sugarResult = view.findViewById(R.id.sugarResult);
        //Fibtg
        fibtgLabel = view.findViewById(R.id.fibtgLabel);
        fibtgResult = view.findViewById(R.id.fibtgResult);
        fibtgUnit = view.findViewById(R.id.fibtgUnit);
        //na
        naLabel = view.findViewById(R.id.naLabel);
        naUnit = view.findViewById(R.id.naUnit);
        naResult = view.findViewById(R.id.naResult);
        //ca
        caLabel = view.findViewById(R.id.caLabel);
        caUnit = view.findViewById(R.id.caUnit);
        caResult = view.findViewById(R.id.caResult);
        //mg
        mgLabel = view.findViewById(R.id.mgLabel);
        mgUnit = view.findViewById(R.id.mgUnit);
        mgResult = view.findViewById(R.id.mgResult);
        //k
        kLabel = view.findViewById(R.id.kLabel);
        kUnit = view.findViewById(R.id.kUnit);
        kResult = view.findViewById(R.id.kResult);
        //fe
        feLabel = view.findViewById(R.id.feLabel);
        feUnit = view.findViewById(R.id.feUnit);
        feResult = view.findViewById(R.id.feResult);
        //zn
        znLabel = view.findViewById(R.id.znLabel);
        znUnit = view.findViewById(R.id.znUnit);
        znResult = view.findViewById(R.id.znResult);
        //p
        pLabel = view.findViewById(R.id.pLabel);
        pUnit = view.findViewById(R.id.pUnit);
        pResult = view.findViewById(R.id.pResult);
        //vitarae
        vitaraeLabel = view.findViewById(R.id.vitaraeLabel);
        vitaraeUnit = view.findViewById(R.id.vitaraeUnit);
        vitaraeResult = view.findViewById(R.id.vitaraeResult);
        //vitc
        vitcLabel = view.findViewById(R.id.vitcLabel);
        vitcUnit = view.findViewById(R.id.vitcUnit);
        vitcResult = view.findViewById(R.id.vitcResult);
        //thia
        thiaLabel = view.findViewById(R.id.thiaLabel);
        thiaUnit = view.findViewById(R.id.thiaUnit);
        thiaResult = view.findViewById(R.id.thiaResult);
        //vitb6a
        vitb6aLabel = view.findViewById(R.id.vitb6aLabel);
        vitb6aUnit = view.findViewById(R.id.vitb6aUnit);
        vitb6aResult = view.findViewById(R.id.vitb6aResult);
        //Chole
        choleLabel = view.findViewById(R.id.choleLabel);
        choleUnit = view.findViewById(R.id.choleUnit);
        choleResult = view.findViewById(R.id.choleResult);
        //Ribf
        ribfLabel = view.findViewById(R.id.ribfLabel);
        ribfResult = view.findViewById(R.id.ribfResult);
        ribfUnit = view.findViewById(R.id.ribfUnit);

        gridLayout = view.findViewById(R.id.gridAnalysis);

        MealDbHelper dbHelper = new MealDbHelper(getActivity());
        mDb = dbHelper.getWritableDatabase();


        mButtonCalculation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EdamamAsyncTask task = new EdamamAsyncTask();
                task.execute(api_id, api_key, mIngredients.getText().toString());
            }
        });

        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addMeal(mSaveButton);
                Toast.makeText(getActivity(), "Meal has been saved", Toast.LENGTH_SHORT).show();
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToMealList(mAddButton);
                Toast.makeText(getActivity(), "Meal has been added to Todays' planner", Toast.LENGTH_SHORT).show();
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("nutrition", resultAnalysis);
//                Fragment fragment = new HomeFragment();
//                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragment.setArguments(bundle);
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.commit();
//                intent.putExtra("nutrition", resultAnalysis);
//                startActivity(intent);
            }
        });

    }

    class EdamamAsyncTask extends AsyncTask<String, Integer, NutritionAnalysis> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Implement a progress bar for the loading animation
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected NutritionAnalysis doInBackground(String... strings) {
            String[] array = strings[2].split("\n");
            HashMap<String, String[]> params = new HashMap<>();
            params.put("ingr", array);
            String strRequestBody = new Gson().toJson(params);

            final RequestBody requestBody = RequestBody.create(MediaType.
                    parse("application/json"),strRequestBody);
            final Call<NutritionAnalysis> call = EdamamClient.getService().getCalculation(strings[0],strings[1],requestBody);
            Log.d("Url: ", call.request().url().toString());
            try{
                return call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(NutritionAnalysis nutritionAnalysis) {
//        super.onPostExecute(nutritionAnalysis);
            mProgressBar.setVisibility(View.GONE);
            gridLayout.setVisibility(View.VISIBLE);
            mCalorieLabel.setVisibility(View.VISIBLE);
            mCalorieResult.setVisibility(View.VISIBLE);
            resultAnalysis = nutritionAnalysis;
            //Calories
            mCalorieResult.setText(resultAnalysis.getCalories().toString());
            mCalorieLabel.setText("Calories");
            //Protein
            mProteinUnit.setText(resultAnalysis.getTotalNutrients().getPROCNT().getUnit());
            mProteinLabel.setText(resultAnalysis.getTotalNutrients().getPROCNT().getLabel());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getPROCNT().getQuantity());
            mProteinResult.setText(value.toString());
            //Carbs
            mCarbsUnit.setText(resultAnalysis.getTotalNutrients().getCHOCDF().getUnit());
            mCarbsLabel.setText(resultAnalysis.getTotalNutrients().getCHOCDF().getLabel());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getCHOCDF().getQuantity());
            mCarbsResult.setText(value.toString());
            //Fat
            mFatLabel.setText(resultAnalysis.getTotalNutrients().getFAT().getLabel());
            mFatUnit.setText(resultAnalysis.getTotalNutrients().getFAT().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getFAT().getQuantity());
            mFatResult.setText(value.toString());
            //fasat
            fasatLabel.setText(resultAnalysis.getTotalNutrients().getFASAT().getLabel());
            fasatUnit.setText(resultAnalysis.getTotalNutrients().getFASAT().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getFASAT().getQuantity());
            fasatResult.setText(value.toString());
            //fapu
            fapuLabel.setText(resultAnalysis.getTotalNutrients().getFAPU().getLabel());
            fapuUnit.setText(resultAnalysis.getTotalNutrients().getFAPU().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getFAPU().getQuantity());
            fasatResult.setText(value.toString());
            //sugar
            sugarLabel.setText(resultAnalysis.getTotalNutrients().getSUGAR().getLabel());
            sugarUnit.setText(resultAnalysis.getTotalNutrients().getSUGAR().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getSUGAR().getQuantity());
            sugarResult.setText(value.toString());
            //fibtg
            fibtgLabel.setText(resultAnalysis.getTotalNutrients().getFIBTG().getLabel());
            fibtgUnit.setText(resultAnalysis.getTotalNutrients().getFIBTG().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getFIBTG().getQuantity());
            fibtgResult.setText(value.toString());
            //na
            naLabel.setText(resultAnalysis.getTotalNutrients().getNA().getLabel());
            naUnit.setText(resultAnalysis.getTotalNutrients().getNA().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getNA().getQuantity());
            naResult.setText(value.toString());
            //ca
            caLabel.setText(resultAnalysis.getTotalNutrients().getCA().getLabel());
            caUnit.setText(resultAnalysis.getTotalNutrients().getCA().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getCA().getQuantity());
            caResult.setText(value.toString());
            //mg
            mgLabel.setText(resultAnalysis.getTotalNutrients().getMG().getLabel());
            mgUnit.setText(resultAnalysis.getTotalNutrients().getMG().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getMG().getQuantity());
            mgResult.setText(value.toString());
            //k
            kLabel.setText(resultAnalysis.getTotalNutrients().getK().getLabel());
            kUnit.setText(resultAnalysis.getTotalNutrients().getK().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getK().getQuantity());
            kResult.setText(value.toString());
            //fe
            feLabel.setText(resultAnalysis.getTotalNutrients().getFE().getLabel());
            feUnit.setText(resultAnalysis.getTotalNutrients().getFE().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getFE().getQuantity());
            feResult.setText(value.toString());
            //zn
            znLabel.setText(resultAnalysis.getTotalNutrients().getZN().getLabel());
            znUnit.setText(resultAnalysis.getTotalNutrients().getZN().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getZN().getQuantity());
            znResult.setText(value.toString());
            //p
            pLabel.setText(resultAnalysis.getTotalNutrients().getP().getLabel());
            pUnit.setText(resultAnalysis.getTotalNutrients().getP().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getP().getQuantity());
            pResult.setText(value.toString());
            //vitarae
            vitaraeLabel.setText(resultAnalysis.getTotalNutrients().getVITARAE().getLabel());
            vitaraeUnit.setText(resultAnalysis.getTotalNutrients().getVITARAE().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getVITARAE().getQuantity());
            vitaraeResult.setText(value.toString());
            //vitc
            vitcLabel.setText(resultAnalysis.getTotalNutrients().getVITC().getLabel());
            vitcUnit.setText(resultAnalysis.getTotalNutrients().getVITC().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getVITC().getQuantity());
            vitcResult.setText(value.toString());
            //thia
            thiaLabel.setText(resultAnalysis.getTotalNutrients().getTHIA().getLabel());
            thiaUnit.setText(resultAnalysis.getTotalNutrients().getTHIA().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getTHIA().getQuantity());
            thiaResult.setText(value.toString());
            //vitb6a
            vitb6aLabel.setText(resultAnalysis.getTotalNutrients().getVITB6A().getLabel());
            vitb6aUnit.setText(resultAnalysis.getTotalNutrients().getVITB6A().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getVITB6A().getQuantity());
            vitb6aResult.setText(value.toString());
            //chole
            choleLabel.setText(resultAnalysis.getTotalNutrients().getCHOLE().getLabel());
            choleUnit.setText(resultAnalysis.getTotalNutrients().getCHOLE().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getCHOLE().getQuantity());
            choleResult.setText(value.toString());
            //ribf
            ribfLabel.setText(resultAnalysis.getTotalNutrients().getRIBF().getLabel());
            ribfUnit.setText(resultAnalysis.getTotalNutrients().getRIBF().getUnit());
            value = (int)Math.round(resultAnalysis.getTotalNutrients().getRIBF().getQuantity());
            ribfResult.setText(value.toString());

            mAddButton.setVisibility(View.VISIBLE);
            mSaveButton.setVisibility(View.VISIBLE);

            Toast.makeText(getActivity(), "Calculation complete", Toast.LENGTH_SHORT).show();
        }

    }

    public void addMeal(View view){
        if(mMealName.getText().length() == 0 || mIngredients.getText().length() == 0){
            return;
        }
        String mealName = mMealName.getText().toString();
        String ingredients = mIngredients.getText().toString();
        String calories = resultAnalysis.getCalories().toString();
        //fat
        String fat = resultAnalysis.getTotalNutrients().getFAT().getLabel();
        String fatUnit = resultAnalysis.getTotalNutrients().getFAT().getUnit();
        String fatQuantity = resultAnalysis.getTotalNutrients().getFAT().getQuantity().toString();
        //protein
        String protein = resultAnalysis.getTotalNutrients().getPROCNT().getLabel();
        String proteinUnit = resultAnalysis.getTotalNutrients().getPROCNT().getUnit();
        String proteinQuantity = resultAnalysis.getTotalNutrients().getPROCNT().getQuantity().toString();
        //carbs
        String carbs = resultAnalysis.getTotalNutrients().getCHOCDF().getLabel();
        String carbsUnit = resultAnalysis.getTotalNutrients().getCHOCDF().getUnit();
        String carbsQuantity = resultAnalysis.getTotalNutrients().getCHOCDF().getQuantity().toString();
        //fasat
        String fasat = resultAnalysis.getTotalNutrients().getFASAT().getLabel();
        String fasatUnit = resultAnalysis.getTotalNutrients().getFASAT().getUnit();
        String fasatQuantity = resultAnalysis.getTotalNutrients().getFASAT().getQuantity().toString();
        //fapu
        String fapu = resultAnalysis.getTotalNutrients().getFAPU().getLabel();
        String fapuUnit = resultAnalysis.getTotalNutrients().getFAPU().getUnit();
        String fapuQuantity = resultAnalysis.getTotalNutrients().getFAPU().getQuantity().toString();
        //fibtg
        String fibtg = resultAnalysis.getTotalNutrients().getFIBTG().getLabel();
        String fibtgUnit = resultAnalysis.getTotalNutrients().getFIBTG().getUnit();
        String fibtgQuantity = resultAnalysis.getTotalNutrients().getFIBTG().getQuantity().toString();
        //Chole
        String chole = resultAnalysis.getTotalNutrients().getCHOLE().getLabel();
        String choleUnit = resultAnalysis.getTotalNutrients().getCHOLE().getUnit();
        String choleQuantity = resultAnalysis.getTotalNutrients().getCHOLE().getQuantity().toString();
        //Na
        String na = resultAnalysis.getTotalNutrients().getNA().getLabel();
        String naUnit = resultAnalysis.getTotalNutrients().getNA().getUnit();
        String naQuantity = resultAnalysis.getTotalNutrients().getNA().getQuantity().toString();
        //Ca
        String ca = resultAnalysis.getTotalNutrients().getCA().getLabel();
        String caUnit = resultAnalysis.getTotalNutrients().getCA().getUnit();
        String caQuantity = resultAnalysis.getTotalNutrients().getCA().getQuantity().toString();
        //MG
        String mg = resultAnalysis.getTotalNutrients().getMG().getLabel();
        String mgUnit = resultAnalysis.getTotalNutrients().getMG().getUnit();
        String mgQuantity = resultAnalysis.getTotalNutrients().getMG().getQuantity().toString();
        //k
        String k = resultAnalysis.getTotalNutrients().getK().getLabel();
        String kUnit = resultAnalysis.getTotalNutrients().getK().getUnit();
        String kQuantity = resultAnalysis.getTotalNutrients().getK().getQuantity().toString();
        //Vitarae
        String vitarae = resultAnalysis.getTotalNutrients().getVITARAE().getLabel();
        String vitaraeUnit = resultAnalysis.getTotalNutrients().getVITARAE().getUnit();
        String vitaraeQuantity = resultAnalysis.getTotalNutrients().getVITARAE().getQuantity().toString();
        //Vitc
        String vitc = resultAnalysis.getTotalNutrients().getVITC().getLabel();
        String vitcUnit = resultAnalysis.getTotalNutrients().getVITC().getUnit();
        String vitcQuantity = resultAnalysis.getTotalNutrients().getVITC().getQuantity().toString();
        //Thia
        String thia = resultAnalysis.getTotalNutrients().getTHIA().getLabel();
        String thiaUnit = resultAnalysis.getTotalNutrients().getTHIA().getUnit();
        String thiaQuantity = resultAnalysis.getTotalNutrients().getTHIA().getQuantity().toString();
        //Ribf
        String ribf = resultAnalysis.getTotalNutrients().getRIBF().getLabel();
        String ribfUnit = resultAnalysis.getTotalNutrients().getRIBF().getUnit();
        String ribfQuantity = resultAnalysis.getTotalNutrients().getRIBF().getQuantity().toString();
        //Nia
        String nia = resultAnalysis.getTotalNutrients().getNIA().getLabel();
        String niaUnit = resultAnalysis.getTotalNutrients().getNIA().getUnit();
        String niaQuantity = resultAnalysis.getTotalNutrients().getNIA().getQuantity().toString();
        //Vit6ba
        String vit6ba = resultAnalysis.getTotalNutrients().getVITB6A().getLabel();
        String vit6baUnit = resultAnalysis.getTotalNutrients().getVITB6A().getUnit();
        String vit6baQuantity = resultAnalysis.getTotalNutrients().getVITB6A().getQuantity().toString();
        //Foldfe
        String foldfe = resultAnalysis.getTotalNutrients().getFOLDFE().getLabel();
        String foldfeUnit = resultAnalysis.getTotalNutrients().getFOLDFE().getUnit();
        String foldfeQuantity = resultAnalysis.getTotalNutrients().getFOLDFE().getQuantity().toString();
        //Fe
        String fe = resultAnalysis.getTotalNutrients().getFE().getLabel();
        String feUnit = resultAnalysis.getTotalNutrients().getFE().getUnit();
        String feQuantity = resultAnalysis.getTotalNutrients().getFE().getQuantity().toString();
        String zn = resultAnalysis.getTotalNutrients().getZN().getLabel();
        String znUnit = resultAnalysis.getTotalNutrients().getZN().getUnit();
        String znQuantity = resultAnalysis.getTotalNutrients().getZN().getQuantity().toString();

        String p = resultAnalysis.getTotalNutrients().getP().getLabel();
        String pUnit = resultAnalysis.getTotalNutrients().getP().getUnit();
        String pQuantity = resultAnalysis.getTotalNutrients().getP().getQuantity().toString();

        addMeal(mealName, ingredients, calories, fat, fatUnit, fatQuantity, protein, proteinUnit, proteinQuantity, carbs, carbsUnit, carbsQuantity,
                fasat, fasatUnit, fasatQuantity, fapu, fapuUnit, fapuQuantity, fibtg, fibtgUnit, fibtgQuantity, chole, choleUnit, choleQuantity, na, naUnit, naQuantity,
                ca, caUnit, caQuantity, mg, mgUnit, mgQuantity, k, kUnit, kQuantity, vitarae, vitaraeUnit, vitaraeQuantity, vitc, vitcUnit, vitcQuantity, thia, thiaUnit, thiaQuantity,
                ribf, ribfUnit, ribfQuantity, nia,niaUnit,niaQuantity, vit6ba, vit6baUnit, vit6baQuantity, foldfe, foldfeUnit, foldfeQuantity, zn, znUnit, znQuantity, fe, feUnit, feQuantity, p, pUnit, pQuantity);
    }

    public void addToMealList(View view){
        if(mMealName.getText().length() == 0 || mIngredients.getText().length() == 0){
            return;
        }
        Log.d("addToMealList()", "entered");
        String mealName = mMealName.getText().toString();
        String ingredients = mIngredients.getText().toString();
        String calories = resultAnalysis.getCalories().toString();
        String fat = resultAnalysis.getTotalNutrients().getFAT().getLabel();
        String fatUnit = resultAnalysis.getTotalNutrients().getFAT().getUnit();
        String fatQuantity = resultAnalysis.getTotalNutrients().getFAT().getQuantity().toString();
        String protein = resultAnalysis.getTotalNutrients().getPROCNT().getLabel();
        String proteinUnit = resultAnalysis.getTotalNutrients().getPROCNT().getUnit();
        String proteinQuantity = resultAnalysis.getTotalNutrients().getPROCNT().getQuantity().toString();
        String carbs = resultAnalysis.getTotalNutrients().getCHOCDF().getLabel();
        String carbsUnit = resultAnalysis.getTotalNutrients().getCHOCDF().getUnit();
        String carbsQuantity = resultAnalysis.getTotalNutrients().getCHOCDF().getQuantity().toString();
        addMeal(mealName, ingredients, calories, fat, fatUnit, fatQuantity, protein, proteinUnit, proteinQuantity, carbs, carbsUnit, carbsQuantity);

        mMealName.getText().clear();
        mIngredients.getText().clear();
    }

    private long addMeal(String name, String ingredients, String calories, String fat, String fatUnit, String fatQuantity, String protein, String proteinUnit, String proteinQuantity, String carbs, String carbsUnit, String carbsQuantity){
        Log.d("addMeal(short)", "entered here as well");
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


    private long addMeal(String name, String ingredients, String calories,
                         String fat, String fatUnit, String fatQuantity,
                         String protein, String proteinUnit, String proteinQuantity,
                         String carbs, String carbsUnit, String carbsQuantity,
                         String fasat, String fasatUnit, String fasatQuantity,
                         String fapu, String fapuUnit, String fapuQuantity,
                         String fitbg, String fitbgUnit, String fitbgQuantity,
                         String chole, String choleUnit, String choleQuantity,
                         String na, String naUnit, String naQuantity,
                         String calcium, String calciumUnit, String calciumQuantity,
                         String mg, String mgUnit, String mgQuantity,
                         String potassiumK, String potassiumKUnit, String potassiumKQuantity,
                         String vitarae, String vitaraeUnit, String vitaraeQuantity,
                         String vitc, String vitcUnit, String vitcQuantity,
                         String thia, String thiaUnit, String thiaQuantity,
                         String ribf, String ribfUnit, String ribfQuantity,
                         String nia, String niaUnit, String niaQuantity,
                         String vit6ba, String vit6baUnit, String vit6baQuantity,
                         String foldfe, String foldfeUnit, String foldfeQuantity,
                         String zn, String znUnit, String znQuantity,
                         String fe, String feUnit, String feQuantity,
                         String p, String pUnit, String pQuantity
    ){
        ContentValues cv = new ContentValues();
        cv.put(MealContract.MealEntry.COLUMN_MEAL_NAME, name);
        cv.put(MealContract.MealEntry.COLUMN_INGREDIENTS, ingredients);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS, calories);
        //Protein
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN, protein);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_UNIT, proteinUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_PROTEIN_QUANTITY, proteinQuantity);
        //Fat
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT, fat);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_UNIT, fatUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAT_QUANTITY, fatQuantity);
        //Carbs
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS, carbs);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_UNIT, carbsUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CARBS_QUANTITY, carbsQuantity);
        //Fasat
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT, fasat);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_UNIT, fasatUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FASAT_QUANTITY, fasatQuantity);
        //Fapu
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU, fapu);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_UNIT, fapuUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FAPU_QUANTITY, fapuQuantity);
        //Fibtg
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG, fitbg);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_UNIT, fitbgUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FIBTG_QUANTITY, fitbgQuantity);
        //Chole
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE, chole);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_UNIT, choleUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CHOLE_QUANTITY, choleQuantity);
        //NA
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA, na);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_UNIT, naUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NA_QUANTITY, naQuantity);
        //Calcium
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA, calcium);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_UNIT, calciumUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_CA_QUANTITY, calciumQuantity);
        //Mg
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG, mg);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_UNIT, mgUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_MG_QUANTITY, mgQuantity);
        //Potassium
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K, potassiumK);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_UNIT, potassiumKUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_K_QUANTITY, potassiumKQuantity);
        //Vitarae
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE, vitarae);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_UNIT, vitaraeUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITARAE_QUANTITY, vitaraeQuantity);
        //Vitc
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC, vitc);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_UNIT, vitcUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITC_QUANTITY, vitcQuantity);
        //Thia
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA, thia);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_UNIT, thiaUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_THIA_QUANTITY, thiaQuantity);
        //Ribf
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF, ribf);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_UNIT, ribfUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_RIBF_QUANTITY, ribfQuantity);
        //Nia
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA, nia);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_UNIT, niaUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_NIA_QUANTITY, niaQuantity);
        //Vit6ba
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A, vit6ba);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_UNIT, vit6baUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_VITB6A_QUANTITY, vit6baQuantity);
        //Foldfe
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE, foldfe);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_UNIT, foldfeUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FOLDFE_QUANTITY, foldfeQuantity);
        //Fe
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE, fe);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_UNIT, feUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_FE_QUANTITY, feQuantity);
        //Zn
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN, zn);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_UNIT, znUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_ZN_QUANTITY, znQuantity);
        //P
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P, p);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_UNIT, pUnit);
        cv.put(MealContract.MealEntry.COLUMN_MEAL_ANALYSIS_P_QUANTITY, pQuantity);
        return mDb.insert(MealContract.MealEntry.TABLE_NAME, null, cv);
    }
    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}
