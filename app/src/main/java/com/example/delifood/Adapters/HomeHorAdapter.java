package com.example.delifood.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.Models.HomeHorModel;
import com.example.delifood.Models.HomeVerModel;
import com.example.delifood.R;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    UpdateVerticalRec updateVerticalRec;
    Activity activity;
    ArrayList<HomeHorModel> list;

    boolean check = true;
    boolean select = true;
    int row_index = -1;

    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity,
                          ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_horizontal_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) { //error_________

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.textView.setText(list.get(position).getName());

        if(check) {

            ArrayList<HomeVerModel> pizza = addPizza();

            updateVerticalRec.callBack(position, pizza);
            check = false;
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                row_index = position;
                notifyDataSetChanged();

                if(position == 0){

                    ArrayList<HomeVerModel> pizza = addPizza();
                    updateVerticalRec.callBack(position,pizza);
                }
                else if (position == 1){

                    ArrayList<HomeVerModel> burger = addBurger();
                    updateVerticalRec.callBack(position,burger);
                }
                else if(position == 2) {

                    ArrayList<HomeVerModel> frise = addFrise();
                    updateVerticalRec.callBack(position,frise);
                }
                else if(position == 3) {

                    ArrayList<HomeVerModel> icecream = addIceCream();
                    updateVerticalRec.callBack(position,icecream);
                }
                else if(position == 4) {

                    ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich1,
                            "Sandwich 1","10:00 - 23:00","2.9","35"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich2,
                            "Sandwich 2","10:00 - 23:00","3.9","45"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich3,
                            "Sandwich 3","10:00 - 23:00","4.9","65"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich4,
                            "Sandwich 4","10:00 - 23:00","4.2","34"));

                    updateVerticalRec.callBack(position,homeVerModels);

                }
            }
        });

        if (select){
            if(position == 0){
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
                select = false;
            }
        } else {
            if(row_index == position){
                holder.cardView.setBackgroundResource(R.drawable.change_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);
            }
        }

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.hor_image);
            textView = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }

    public ArrayList<HomeVerModel> addPizza(){
        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
        homeVerModels.add(new HomeVerModel(R.drawable.pizza1,
                "Pepperoni Pizza", "30 min", "3.9", "35"));
        homeVerModels.add(new HomeVerModel(R.drawable.pizza2,
                "Meat Pizza", "40 min", "4.5", "30"));
        homeVerModels.add(new HomeVerModel(R.drawable.pizza3,
                "Margherita Pizza", "35 min", "4.9", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.pizza4,
                "Buffalo Pizza", "34 min", "4.1", "55"));
        homeVerModels.add(new HomeVerModel(R.drawable.pizza1,
                "Pepperoni Pizza", "30 min", "3.3", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.pizza2,
                "Meat Pizza", "40 min", "4.3", "40"));
        return homeVerModels;
    }

    public ArrayList<HomeVerModel> addBurger(){
        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
        homeVerModels.add(new HomeVerModel(R.drawable.burger2,
                "Turkey burger", "30 min", "3.9", "35"));
        homeVerModels.add(new HomeVerModel(R.drawable.burger4,
                "Portobello burger", "40 min", "4.5", "30"));
        homeVerModels.add(new HomeVerModel(R.drawable.burger2,
                "Veggie burger", "35 min", "4.9", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.burger4,
                "Salmon burger", "34 min", "4.1", "55"));
        return homeVerModels;
    }

    public ArrayList<HomeVerModel> addFrise(){
        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
        homeVerModels.add(new HomeVerModel(R.drawable.fries1,
                "Baked Fries", "30 min", "3.9", "35"));
        homeVerModels.add(new HomeVerModel(R.drawable.fries2,
                "Poutine Fries", "40 min", "4.5", "30"));
        homeVerModels.add(new HomeVerModel(R.drawable.fries3,
                "Sweet Potato", "35 min", "4.9", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.fries4,
                "Sautéed French", "34 min", "4.1", "55"));
        homeVerModels.add(new HomeVerModel(R.drawable.fries1,
                "Waffle Fries", "30 min", "3.3", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.fries2,
                "Steak Fries", "40 min", "4.3", "40"));
        return homeVerModels;
    }

    public ArrayList<HomeVerModel> addIceCream(){
        ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
        homeVerModels.add(new HomeVerModel(R.drawable.icecream4,
                "Frozen Yogurt", "30 min", "3.9", "35"));
        homeVerModels.add(new HomeVerModel(R.drawable.icecream2,
                "Soft Serve", "40 min", "4.5", "30"));
        homeVerModels.add(new HomeVerModel(R.drawable.icecream3,
                "Rolled IceCream", "35 min", "4.9", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.icecream4,
                "Kulfi", "34 min", "4.1", "55"));
        homeVerModels.add(new HomeVerModel(R.drawable.icecream1,
                "American ice cream", "30 min", "3.3", "45"));
        homeVerModels.add(new HomeVerModel(R.drawable.icecream2,
                "Frozen Yogurt", "40 min", "4.3", "40"));
        return homeVerModels;
    }


}
