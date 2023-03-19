package com.example.delifood.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.Adapters.DailyMealAdapter;
import com.example.delifood.Models.DailyMealModel;
import com.example.delifood.R;
import com.example.delifood.databinding.DailyMealFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment {

    private DailyMealFragmentBinding binding;

    //RecyclerView recyclerView;
    List<DailyMealModel> dailyMealModels;
    DailyMealAdapter dailyMealAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = DailyMealFragmentBinding.inflate(inflater, container, false);

        dailyMealModels = new ArrayList<>();

        dailyMealModels.add(new DailyMealModel(R.drawable.breakfast,
                "Breakfast","15% Off", "Description Description","breakfast"));
        dailyMealModels.add(new DailyMealModel(R.drawable.lunch,
                "Lunch","33% Off", "Description Description","lunch"));
        dailyMealModels.add(new DailyMealModel(R.drawable.dinner,
                "Dinner","40% Off", "Description Description","dinner"));
        dailyMealModels.add(new DailyMealModel(R.drawable.sweets,
                "Sweets","50% Off", "Description Description","sweets"));
        dailyMealModels.add(new DailyMealModel(R.drawable.coffe,
                "Coffee","38% Off", "Description Description","coffee"));


        dailyMealAdapter = new DailyMealAdapter(binding.getRoot().getContext(),dailyMealModels);
        binding.dailyMealRv.setAdapter(dailyMealAdapter);
        binding.dailyMealRv.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        dailyMealAdapter.notifyDataSetChanged();


        return binding.getRoot();
    }



}