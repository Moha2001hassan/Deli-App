package com.example.delifood.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.API.Recipe;
//import com.example.delifood.Activities.DetailedDailyMealActivity;
import com.example.delifood.Activities.Recipe_Activity;
import com.example.delifood.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DailyMealAdapter extends RecyclerView.Adapter<DailyMealAdapter.MyViewHolder>{

    private Context mContext;
    private List<Recipe> mData;

    public DailyMealAdapter(Context context, List<Recipe> list) {
        this.mContext = context;
        this.mData = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_recipe,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.tv_recipe_title.setText(mData.get(position).getTitle());
        holder.tv_amount_of_dishes.setText(Integer.toString(mData.get(position).getAmountOfDishes()) );
        holder.tv_ready_in_mins.setText( Integer.toString(mData.get(position).getReadyInMins()) );
        if (mData.get(position).getThumbnail().isEmpty()) {
            holder.img_recipe_thumbnail.setImageResource(R.drawable.nopicture);
        } else{
            Picasso.get().load(mData.get(position).getThumbnail()).into(holder.img_recipe_thumbnail);
        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, Recipe_Activity.class);
                intent.putExtra("id",mData.get(position).getId());
                intent.putExtra("title",mData.get(position).getTitle());
                intent.putExtra("img",mData.get(position).getThumbnail());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_recipe_title,tv_amount_of_dishes,tv_ready_in_mins;
        ImageView img_recipe_thumbnail;
        CardView cardView ;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv_recipe_title = (TextView) itemView.findViewById(R.id.recipe_title_id) ;
            img_recipe_thumbnail = (ImageView) itemView.findViewById(R.id.recipe_img_id);
            tv_amount_of_dishes = (TextView) itemView.findViewById(R.id.servingTvLeft);
            tv_ready_in_mins = (TextView) itemView.findViewById(R.id.readyInTvRight);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}
