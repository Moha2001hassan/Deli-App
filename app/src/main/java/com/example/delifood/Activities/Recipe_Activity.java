/* Assignment: 3
Campus: Ashdod
Authors:
Eliran Naduyev 312089105
Maria Garber
*/

package com.example.delifood.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.delifood.API.Ingredient;
import com.example.delifood.Adapters.RecyclerViewAdapterRecipeIngredient;
import com.example.delifood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
//import com.google.firebase.auth.FirebaseAuth;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Recipe_Activity extends AppCompatActivity {

    private TextView title, ready_in, servings, healthy, instructions;
    private ImageView img;
    //private FirebaseAuth mAuth;
    private JSONArray ingredientsArr;
    private List<Ingredient> ingredientsLst = new ArrayList<Ingredient>();
    private RecyclerView myrv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_);

        final Intent intent = getIntent();
        final String recipeId = Objects.requireNonNull(intent.getExtras()).getString("id");
        //mAuth = FirebaseAuth.getInstance();
        //final String uid = mAuth.getCurrentUser().getUid();
        //mRootRef = FirebaseDatabase.getInstance().getReference().child(uid).child(recipeId);

        img = findViewById(R.id.recipe_img);
        title = findViewById(R.id.recipe_title);
        ready_in = findViewById(R.id.recipe_ready_in);
        servings = findViewById(R.id.recipe_servings);
        healthy = findViewById(R.id.recipe_healthy);
        instructions = findViewById(R.id.recipe_instructions);

        getRecipeData(recipeId);

        myrv = findViewById(R.id.recipe_ingredients_rv);
        myrv.setLayoutManager(new LinearLayoutManager(this,
                RecyclerView.HORIZONTAL,false));
    }

    private void getRecipeData(final String recipeId) {
        String URL = " https://api.spoonacular.com/recipes/" + recipeId + "/information?apiKey=4e42133b220146308cb7b07bea2bdd5d";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
            Request.Method.GET, URL, null,
            new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    try {
                        try {
                            Picasso.get().load((String) response.get("image")).into(img);
                        }
                        catch (Exception e){
                            img.setImageResource(R.drawable.nopicture);
                        }
                        title.setText((String) response.get("title"));
                        ready_in.setText(Integer.toString((Integer) response.get("readyInMinutes")));
                        servings.setText(Integer.toString((Integer) response.get("servings")));

                        if ((boolean) response.get("veryHealthy")) {
                            healthy.setText("Healthy");
                        }
                        try{
                            if(response.get("instructions").equals("")){
                                throw new Exception("No Instructions");
                            }
                            else
                                instructions.setText(Html.fromHtml((String) response.get("instructions")));
                        }
                        catch(Exception e){
                            String msg= "Unfortunately, the recipe you were looking for not found, to view the original recipe click on the link below:" + "<a href="+response.get("spoonacularSourceUrl")+">"+response.get("spoonacularSourceUrl")+"</a>";
                            instructions.setMovementMethod(LinkMovementMethod.getInstance());
                            instructions.setText(Html.fromHtml(msg));
                        }
                        ingredientsArr = (JSONArray) response.get("extendedIngredients");
                        for (int i = 0; i < ingredientsArr.length(); i++) {
                            JSONObject jsonObject1;
                            jsonObject1 = ingredientsArr.getJSONObject(i);
                            ingredientsLst.add(new Ingredient(jsonObject1.optString("originalString"),
                                    jsonObject1.optString("image")));
                        }
                        RecyclerViewAdapterRecipeIngredient myAdapter = new
                                RecyclerViewAdapterRecipeIngredient(getApplicationContext(), ingredientsLst);
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
                }
            }
        );
        requestQueue.add(jsonObjectRequest);
    }
}