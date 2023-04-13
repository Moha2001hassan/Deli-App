package com.example.delifood.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.delifood.Adapters.HomeHorAdapter;
import com.example.delifood.Adapters.HomeVerAdapter;
import com.example.delifood.Adapters.UpdateVerticalRec;
import com.example.delifood.Helper.ManagementCart;
import com.example.delifood.Models.FoodModel;
import com.example.delifood.Models.HomeHorModel;
import com.example.delifood.Models.HomeVerModel;
import com.example.delifood.R;
import com.example.delifood.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements UpdateVerticalRec {

    private FragmentHomeBinding binding;
    ArrayList<HomeHorModel> homeHorModelList;
    HomeHorAdapter homeHorAdapter;
    ArrayList<HomeVerModel> homeVerModelList;
    HomeVerAdapter homeVerAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        horizontalRV();
        verticalRV();

        return binding.getRoot();
    }

    public void horizontalRV(){
        homeHorModelList = new ArrayList<>();

        homeHorModelList.add(new HomeHorModel(R.drawable.pizza,"Pizza"));
        homeHorModelList.add(new HomeHorModel(R.drawable.hamburger,"HumBurger"));
        homeHorModelList.add(new HomeHorModel(R.drawable.fried_potatoes,"Fries"));
        homeHorModelList.add(new HomeHorModel(R.drawable.ice_cream,"Ice Cream"));
        homeHorModelList.add(new HomeHorModel(R.drawable.sandwich,"Sandwich"));

        homeHorAdapter = new HomeHorAdapter(this,getActivity(),homeHorModelList);

        binding.homeHorRv.setAdapter(homeHorAdapter);
        binding.homeHorRv.setHasFixedSize(true);
        binding.homeHorRv.setNestedScrollingEnabled(false);
        binding.homeHorRv.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext(), RecyclerView.HORIZONTAL,false));
    }

    public void verticalRV(){
        homeVerModelList = new ArrayList<>();
        homeVerAdapter = new HomeVerAdapter(getActivity(),homeVerModelList);

        binding.homeVerRv.setAdapter(homeVerAdapter);
        binding.homeVerRv.setLayoutManager(new LinearLayoutManager(
                binding.getRoot().getContext(), RecyclerView.VERTICAL,false));
    }

    @Override
    public void callBack(int position, ArrayList<HomeVerModel> list) {

        homeVerAdapter = new HomeVerAdapter(getContext(),list);
        homeVerAdapter.notifyDataSetChanged();
        binding.homeVerRv.setAdapter(homeVerAdapter);
    }
}






