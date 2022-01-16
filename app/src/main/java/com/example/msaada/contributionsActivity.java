package com.example.msaada;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.msaada.helpers.Anime;
import com.example.msaada.recyclerviews.recyclecontributions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class contributionsActivity extends AppCompatActivity {
    private List<Anime> lstAnime;
    private RecyclerView recyclerView;
    Button search;
    EditText name;
    public String username;

    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contributions);
        //get name to be searched
        name = findViewById(R.id.name);
        String searchv = name.getText().toString();


        url = "https://msaadaproject.herokuapp.com/api/verified/contribution";

        search = findViewById(R.id.search);

        lstAnime = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerviewid);
        jsonrequest();



//get results when search button is clivked
        search.setOnClickListener(v -> {

            lstAnime = new ArrayList<>();
            recyclerView = findViewById(R.id.recyclerviewid);
            jsonrequest();
        });



    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching contributions..");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = url;

        try {



            Response.Listener<JSONObject> successListener = response -> {

                try {
                    JSONArray list = response.getJSONArray("response");

                for (int i = 0; i < list.length(); i++) {

                    try {
                        Anime anime = Anime.contributions(list.getJSONObject(i));
                        anime.setTitle(anime.getTitle());
                        anime.setDescription(anime.getDescription());
                        anime.setAmount(anime.getAmount());
                        anime.setId(anime.getId());
                        anime.setRaised(anime.getRaised());

                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(contributionsActivity.this,"No contribution found with the name provided",Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.error);
                    Toast.makeText(this, "No response,Make sure you have a strong internet connection", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };
            JsonObjectRequest request = new JsonObjectRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);


        } catch (Exception e) {
            Toast.makeText(this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    //add data to be recycled on view
    private void setuprecycleview(List<Anime> lstAnime) {
        recyclecontributions myadapter = new recyclecontributions(this, lstAnime);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myadapter);
    }
}
