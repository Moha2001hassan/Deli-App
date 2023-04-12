package com.example.delifood.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.delifood.Helper.ManagementCart;
import com.example.delifood.Interface.ChangeNumberItemsListener;
import com.example.delifood.Models.CartModel;
import com.example.delifood.Models.FoodModel;
import com.example.delifood.R;

import java.util.ArrayList;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    ArrayList<FoodModel> listFoodSelected;
    private ManagementCart managementCart;
    ChangeNumberItemsListener changeNumberItemsListener;

    public CartAdapter(ArrayList<FoodModel> categoryDomains, Context context,
                           ChangeNumberItemsListener changeNumberItemsListener) {
        this.listFoodSelected = categoryDomains;
        managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CartAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.viewholder_cart,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {

        holder.title.setText(listFoodSelected.get(position).getName());
        holder.feeEachItem.setText("$"+listFoodSelected.get(position).getPrice());
        holder.totalEachItem.setText("$"+ Math.round(listFoodSelected.get(position).getNumberInCart()
                * listFoodSelected.get(position).getPrice()));
        holder.num.setText(String.valueOf(listFoodSelected.get(position).getNumberInCart()));

        holder.pic.setImageResource(listFoodSelected.get(position).getImage());
//        int drawableResourceId = holder.itemView.getContext().getResources()
//                .getIdentifier(listFoodSelected.get(position).getPic(),
//                        "drawable",holder.itemView.getContext().getPackageName());
//
//        Glide.with(holder.itemView.getContext())
//                .load(drawableResourceId).into(holder.pic);

        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberFood(listFoodSelected, holder.getAdapterPosition(), new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(listFoodSelected, holder.getAdapterPosition(), new ChangeNumberItemsListener() {
                    @Override
                    public void change() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.change();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFoodSelected.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, feeEachItem;
        ImageView pic,plusItem,minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titleTxt);
            pic = itemView.findViewById(R.id.pic);
            feeEachItem = itemView.findViewById(R.id.feeEachItem);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
            num = itemView.findViewById(R.id.numberItemsTxt);
        }
    }
}
