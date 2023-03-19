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

import com.example.delifood.Fragments.FragmentAdapter;
import com.example.delifood.databinding.FavouriteFragmentBinding;
import com.google.android.material.tabs.TabLayout;

public class FavouriteFragment extends Fragment {

    private FavouriteFragmentBinding binding;

    TabLayout tabLayout;
    ViewPager2 viewPager2;
    FragmentAdapter fragmentAdapter;
    private FragmentActivity myContext;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FavouriteFragmentBinding.inflate(inflater, container, false);

        tabLayout = binding.tabLayout;
        viewPager2 = binding.viewPager;

        tabLayout.addTab(tabLayout.newTab().setText("Featured"));
        tabLayout.addTab(tabLayout.newTab().setText("Popular"));
        tabLayout.addTab(tabLayout.newTab().setText("New"));

        FragmentManager fm = getActivity().getSupportFragmentManager();

        fragmentAdapter = new FragmentAdapter(fm,getLifecycle());

        viewPager2.setAdapter(fragmentAdapter);

/*
        tabLayout.selectTab(tabLayout.newTab().setText("Featured"));
        tabLayout.selectTab(tabLayout.newTab().setText("Popular"));
        tabLayout.selectTab(tabLayout.newTab().setText("New"));
*/

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager2.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });


        return binding.getRoot();
    }


}


