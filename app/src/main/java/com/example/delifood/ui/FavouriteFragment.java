package com.example.delifood.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.delifood.databinding.FavouriteFragmentBinding;
import com.google.android.material.tabs.TabLayout;

public class FavouriteFragment extends Fragment {

    private FavouriteFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FavouriteFragmentBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }


}


