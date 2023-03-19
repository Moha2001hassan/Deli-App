package com.example.delifood.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.delifood.Adapters.DetailedDailyAdapter;
import com.example.delifood.Models.DetailedDailyModel;
import com.example.delifood.R;

import java.util.ArrayList;
import java.util.List;

public class DetailedDailyMealActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetailedDailyModel> detailedDailyModelList;
    DetailedDailyAdapter dailyAdapter;
    //ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_daily_meal);

        String type = getIntent().getStringExtra("type");

        //imageView = findViewById(R.id.detailed_img);
        recyclerView = findViewById(R.id.detailed_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        detailedDailyModelList = new ArrayList<>();
        dailyAdapter = new DetailedDailyAdapter(detailedDailyModelList);
        recyclerView.setAdapter(dailyAdapter);

        if(type != null && type.equalsIgnoreCase("breakfast")){
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav1,
                    "Breakfast 1","Description","4.4","40","10:00 - 09:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav2,
                    "Breakfast 2","Description","4.4","40","10:00 - 09:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.fav3,
                    "Breakfast 3","Description","4.4","40","10:00 - 09:00"));
            dailyAdapter.notifyDataSetChanged();
        }

        if(type != null && type.equalsIgnoreCase("sweets")){
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s1,
                    "Sweet 1","Description","4.4","40","10:00 - 09:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s2,
                    "Sweet 2","Description","4.4","40","10:00 - 09:00"));
            detailedDailyModelList.add(new DetailedDailyModel(R.drawable.s3,
                    "Sweet 3","Description","4.4","40","10:00 - 09:00"));
            dailyAdapter.notifyDataSetChanged();
        }

    }
}





