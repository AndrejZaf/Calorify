package com.example.calorify.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.calorify.R;
import com.example.calorify.Repository.MealContract;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {
    private static final String TAG = MealAdapter.class.getSimpleName();
    private Cursor mCursor;
    private Context mContext;
    public MealAdapter(Context context, Cursor cursor){
        this.mContext = context;
        this.mCursor = cursor;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.meal_list_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position))
            return;

        String name = mCursor.getString(mCursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_NAME));
        String calories = mCursor.getString(mCursor.getColumnIndex(MealContract.MealPlannerEntry.COLUMN_MEAL_ANALYSIS));
        long id = mCursor.getLong(mCursor.getColumnIndex(MealContract.MealPlannerEntry._ID));
        holder.meal_name.setText(name);
        holder.meal_calories.setText(calories);
        holder.itemView.setTag(id);
    }

    public void swapCursor(Cursor newCursor){
        if(mCursor != null) mCursor.close();
        mCursor = newCursor;

        if(newCursor != null){
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder {
        TextView meal_name;
        TextView meal_calories;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            meal_name = (TextView) itemView.findViewById(R.id.meal_name);
            meal_calories = (TextView) itemView.findViewById(R.id.meal_calories);
        }
    }
}
