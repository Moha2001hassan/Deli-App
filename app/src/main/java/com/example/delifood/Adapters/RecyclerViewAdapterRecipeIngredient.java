/* Assignment: 3
Campus: Ashdod
Authors:
Eliran Naduyev 312089105
Maria Garber
*/

package com.example.delifood.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.API.Ingredient;
import com.example.delifood.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterRecipeIngredient extends RecyclerView.Adapter<RecyclerViewAdapterRecipeIngredient.MyViewHolder> {
    private Context mContext;
    private List<Ingredient> mData;
    public static List<String> ingredientsList;

    public RecyclerViewAdapterRecipeIngredient(Context mContext, List<Ingredient> mData) {
        this.mContext = mContext;
        this.mData = mData;
        ingredientsList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.item_ingredient, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        Picasso.get().load(mData.get(position).getThumbnail()).into(holder.img_ingredient_thumbnail);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView img_ingredient_thumbnail;
        public MyViewHolder(View itemView) {
            super(itemView);
            img_ingredient_thumbnail = itemView.findViewById(R.id.recipe_ingredient_img);
        }
    }

}
