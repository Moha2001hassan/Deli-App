package com.example.delifood.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.delifood.Models.FoodModel;
import com.example.delifood.Interface.ChangeNumberItemsListener;

import java.util.ArrayList;

public class ManagementCart {
    private Context context;
    private com.example.delifood.Helper.TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new com.example.delifood.Helper.TinyDB(context);
    }

    public void insertFood(FoodModel item){
        ArrayList<FoodModel> listFood = getListCart();
        boolean existAlready = false;

        int n=0;
        for (int i = 0;i < listFood.size();i++) {
            if(listFood.get(i).getName().equals(item.getName())){
                existAlready = true;
                n=i;
                break;
            }
        }

        if(existAlready){
            listFood.get(n).setNumberInCart(item.getNumberInCart());
        }else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList",listFood);
        Toast.makeText(context, "Added to your Cart", Toast.LENGTH_SHORT).show();

    }

    public ArrayList<FoodModel> getListCart() {
        return tinyDB.getListObject("CardList");
    }

    public void minusNumberFood(ArrayList<FoodModel> listFood, int position,
                                ChangeNumberItemsListener changeNumberItemsListener){

        if(listFood.get(position).getNumberInCart() == 1){
            listFood.remove(position);
        }else {
            listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() -1);
        }

        tinyDB.putListObject("CardList",listFood);
        changeNumberItemsListener.change();
    }

    public void plusNumberFood(ArrayList<FoodModel> listFood, int position,
                               ChangeNumberItemsListener changeNumberItemsListener){

        listFood.get(position).setNumberInCart(listFood.get(position).getNumberInCart() +1);
        tinyDB.putListObject("CardList",listFood);
        changeNumberItemsListener.change();
    }

    public Double getTotalFee(){
        ArrayList<FoodModel> foodList2 = getListCart();

        double fee = 0;
        for (int i =0; i < foodList2.size();i++){
            fee = fee + (foodList2.get(i).getPrice() * foodList2.get(i).getNumberInCart());
        }
        return fee;
    }
}







