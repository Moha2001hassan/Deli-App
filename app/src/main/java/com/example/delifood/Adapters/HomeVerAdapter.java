package com.example.delifood.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.Helper.ManagementCart;
import com.example.delifood.Models.FoodModel;
import com.example.delifood.Models.HomeVerModel;
import com.example.delifood.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder>{

    BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;

    private FoodModel object;
    private int numberOrder = 1;
    private ManagementCart managementCart;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HomeVerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HomeVerAdapter.ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_vertical_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HomeVerAdapter.ViewHolder holder, int position) {
        managementCart = new ManagementCart(this.context);

        final int mImage = list.get(position).getImage();
        final String mName = list.get(position).getName();
        final String mTiming = list.get(position).getTime();
        final String mRating = list.get(position).getRating();
        final String mPrice = list.get(position).getPrice();

        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTime());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog = new BottomSheetDialog(context,R.style.BottomSheetTheme);
                View sheetView = LayoutInflater.from(context)
                        .inflate(R.layout.bottom_sheet_layout,null);

                ImageView bottomImg = sheetView.findViewById(R.id.bottom_img);
                TextView bottomName = sheetView.findViewById(R.id.bottom_name);
                TextView bottomTime = sheetView.findViewById(R.id.bottom_time);
                TextView bottomPrice = sheetView.findViewById(R.id.bottom_price);
                TextView bottomRating = sheetView.findViewById(R.id.bottom_rating);

                bottomName.setText(mName);
                bottomPrice.setText(mPrice);
                bottomRating.setText(mRating);
                bottomTime.setText(mTiming);
                bottomImg.setImageResource(mImage);

                bottomSheetDialog.setContentView(sheetView);
                bottomSheetDialog.show();

                object = new FoodModel(
                        mImage,mName,mTiming,mRating,Double.parseDouble(mPrice),numberOrder) ;
                sheetView.findViewById(R.id.add_to_cart).setOnClickListener(
                        new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        object.setNumberInCart(numberOrder);
                        managementCart.insertFood(object);
                        bottomSheetDialog.dismiss();
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name,timing,rating,price;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_image);
            name = itemView.findViewById(R.id.name);
            timing = itemView.findViewById(R.id.time);
            price = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
        }
    }
}
