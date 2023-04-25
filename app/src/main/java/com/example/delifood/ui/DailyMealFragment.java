package com.example.delifood.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delifood.API.Recipe;
import com.example.delifood.Adapters.DailyMealAdapter;
import com.example.delifood.R;
//import com.example.delifood.databinding.DailyMealFragmentBinding;
//import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DailyMealFragment extends Fragment implements View.OnClickListener {

    private List<Recipe> lstRecipe = new ArrayList<>();
    private List<Recipe> searchRecipe;
    private JSONArray testArr;

    private ImageButton searchBtn;
    private Button breakfastBtn,lunchBtn,dinnerBtn;
    private TextView searchTv, emptyView;
    private RecyclerView myrv;
    private ProgressBar progressBar;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View RootView = inflater.inflate(R.layout.fragment_recipe, container, false);

        progressBar = RootView.findViewById(R.id.progressbar);
        emptyView= RootView.findViewById(R.id.empty_view2);
        myrv = RootView.findViewById(R.id.recyclerview);
        searchTv = RootView.findViewById(R.id.home_search_et);
        searchBtn = RootView.findViewById(R.id.home_search_btn);
        breakfastBtn= RootView.findViewById(R.id.home_breakfast_filter);
        lunchBtn= RootView.findViewById(R.id.home_lunch_filter);
        dinnerBtn= RootView.findViewById(R.id.home_dinner_filter);

        myrv.setLayoutManager(new LinearLayoutManager(getActivity(),
                RecyclerView.VERTICAL,false));
        getRandomRecipes();

        breakfastBtn.setOnClickListener(this);
        lunchBtn.setOnClickListener(this);
        dinnerBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        searchTv.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    if(!v.getText().toString().equals("")) {
                        emptyView.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        myrv.setAlpha(0);
                        searchRecipe(v.getText().toString());
                    }
                    else
                        Toast.makeText(getContext(), "Type something...", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        return RootView;
    }

    private void searchRecipe(String search) {
        searchRecipe = new ArrayList<Recipe>();
        String URL="https://api.spoonacular.com/recipes/search?query=" + search +
                "&number=30&instructionsRequired=true&apiKey=4e42133b220146308cb7b07bea2bdd5d";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    testArr = (JSONArray) response.get("results");

                    for (int i = 0; i < testArr.length(); i++) {
                        JSONObject jsonObject1;
                        jsonObject1 = testArr.getJSONObject(i);
                        searchRecipe.add(new Recipe(jsonObject1.optString("id"),
                                jsonObject1.optString("title"),
                                "https://spoonacular.com/recipeImages/"+ jsonObject1.optString("image"),
                                Integer.parseInt(jsonObject1.optString("servings")),
                                Integer.parseInt(jsonObject1.optString("readyInMinutes"))
                        ));
                    }
                    progressBar.setVisibility(View.GONE);
                    if(searchRecipe.isEmpty()){
                        myrv.setAlpha(0);
                        emptyView.setVisibility(View.VISIBLE);
                    }
                    else{
                        emptyView.setVisibility(View.GONE);
                        DailyMealAdapter myAdapter = new DailyMealAdapter(getContext(), searchRecipe);
                        myrv.setAdapter(myAdapter);
                        myrv.setItemAnimator(new DefaultItemAnimator());
                        myrv.setAlpha(1);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("the res is error:", error.toString());
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void getRandomRecipes() {
        String URL = " https://api.spoonacular.com/recipes/random?number=30&instructionsRequired=true&apiKey=4e42133b220146308cb7b07bea2bdd5d";
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        testArr = (JSONArray) response.get("recipes");
                        for (int i = 0; i < testArr.length(); i++) {
                            JSONObject jsonObject1;
                            jsonObject1 = testArr.getJSONObject(i);
                            lstRecipe.add(new Recipe(jsonObject1.optString("id"),
                                    jsonObject1.optString("title"),
                                    jsonObject1.optString("image"),
                                    Integer.parseInt(jsonObject1.optString("servings")),
                                    Integer.parseInt(jsonObject1.optString("readyInMinutes"))
                            ));
                        }
                        progressBar.setVisibility(View.GONE);
                        DailyMealAdapter myAdapter = new DailyMealAdapter(getContext(), lstRecipe);
                        myrv.setAdapter(myAdapter);
                        myrv.setItemAnimator(new DefaultItemAnimator());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.i("the res is error:", error.toString());
                    progressBar.setVisibility(View.GONE);
//                    myrv.setAlpha(0);
                    emptyView.setVisibility(View.VISIBLE);
                }
            }
        );
        requestQueue.add(jsonObjectRequest);
    }


    @Override
    public void onClick(View v) {

        if(v==breakfastBtn){
            searchRecipe("breakfast");
        }
        else if(v==lunchBtn){
            searchRecipe("lunch");
        }
        else if(v==dinnerBtn){
            searchRecipe("dinner");
        }
        else if(v==searchBtn){
            try {
                InputMethodManager imm = (InputMethodManager)getActivity()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            } catch (Exception e) {
                //----
            }
            if(!searchTv.getText().toString().toString().equals("")) {
                progressBar.setVisibility(View.VISIBLE);
                myrv.setAlpha(0);
                searchRecipe(searchTv.getText().toString());
            }
            else
                Toast.makeText(getContext(), "Type something...", Toast.LENGTH_LONG).show();
        }
    }
}