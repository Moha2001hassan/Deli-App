package com.example.delifood.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.delifood.Adapters.CartAdapter;
import com.example.delifood.Helper.ManagementCart;
import com.example.delifood.Interface.ChangeNumberItemsListener;
import com.example.delifood.R;
import com.example.delifood.databinding.FragmentCart1Binding;


public class MyCartFragment extends Fragment {

    private FragmentCart1Binding binding;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private Double tax;

    public MyCartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCart1Binding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        managementCart = new ManagementCart(binding.getRoot().getContext());

        initList();
        calculateCard();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(binding.getRoot().getContext(),
                        "The Order is Done...", Toast.LENGTH_SHORT).show();

                

            }
        });

    }

    private void initList() {

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(binding.getRoot()
                .getContext(),LinearLayoutManager.VERTICAL,false);
        binding.view.setLayoutManager(linearLayoutManager);

        adapter = new CartAdapter(managementCart.getListCart(), binding.getRoot()
                .getContext(), new ChangeNumberItemsListener() {
            @Override
            public void change() {
                calculateCard();
            }
        });
        binding.view.setAdapter(adapter);


        if(managementCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.parent.setVisibility(View.GONE);
        }else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.parent.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCard() {

        Double percentTax=0.02;
        Double delivery=10.0;
        tax = Math.round((managementCart.getTotalFee() * percentTax) * 100.0) / 100.0;
        Double total = Math.round((managementCart.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        Double itemTotal = Math.round(managementCart.getTotalFee() * 100.0)/100.0;

        binding.totalFeeTxt.setText("$" + itemTotal);
        binding.taxTxt.setText("$" + tax);
        binding.deliveryTxt.setText("$" + delivery);
        binding.totalTxt.setText("$" + total);
    }
}