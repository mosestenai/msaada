package com.example.msaada.admin;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.example.msaada.R;
import com.example.msaada.recyclerviews.recycletransactions;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import java.util.List;


public class transactions extends AppCompatActivity{

    private List<Anime> lstAnime;
    private RecyclerView recyclerVieww;


    public String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);



        url = "https://msaadaproject.herokuapp.com/api/pending/transactions";



        lstAnime = new ArrayList<>();
        recyclerVieww = findViewById(R.id.recyclerviewid);
        jsonrequest();





    }

    private void jsonrequest() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching transactions...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        String JSON_URL = url;

        try {



            Response.Listener<JSONObject> successListener = response -> {
                try {
                    JSONArray list = response.getJSONArray("response");

                    for (int i = 0; i < list.length(); i++) {

                    try {
                        Anime anime = Anime.gettransactions(list.getJSONObject(i));
                        anime.setPhone(anime.getPhone());
                        anime.setAmount(anime.getAmount());
                        anime.setId(anime.getId());
                        anime.setDate(anime.getDate());
                        progressDialog.dismiss();
                        lstAnime.add(anime);

                    } catch (JSONException e) {
                        Toast.makeText(transactions.this, "No transactions made", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();


                    }

                }
                setuprecycleview(lstAnime);
            }
                catch (JSONException e) {
                    e.printStackTrace();
                }

            };
            Response.ErrorListener errorListener = error -> {
                if (error instanceof NoConnectionError) {
                    setContentView(R.layout.error);
                    Toast.makeText(transactions.this, "No internet access", Toast.LENGTH_LONG).show();
                } else {
                    setContentView(R.layout.error);
                    Toast.makeText(transactions.this, "No response,Make sure you have a strong internet connection", Toast.LENGTH_LONG).show();
                }
                progressDialog.dismiss();
            };
            JsonObjectRequest request = new JsonObjectRequest(JSON_URL, successListener, errorListener);
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(request);



        } catch (Exception e) {
            Toast.makeText(transactions.this, "json exception", Toast.LENGTH_LONG).show();
        }

    }

    private void setuprecycleview(List<Anime> lstAnime) {
        recycletransactions myadapter = new recycletransactions(this, lstAnime);
        recyclerVieww.setLayoutManager(new LinearLayoutManager(this));


        recyclerVieww.setAdapter(myadapter);
    }



}
