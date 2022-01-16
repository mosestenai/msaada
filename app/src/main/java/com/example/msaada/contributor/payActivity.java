package com.example.msaada.contributor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msaada.helpers.Model;
import com.example.msaada.R;
import com.example.msaada.helpers.User;
import com.example.msaada.api.AbstractAPIListener;

public class payActivity extends AppCompatActivity {

    EditText phone,amount;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        phone = findViewById(R.id.phone);
        amount = findViewById(R.id.amount);


        Intent test = getIntent();
        String id = test.getStringExtra("id");

        submit = findViewById(R.id.submit);

        submit.setOnClickListener(v->{
            String phonee = phone.getText().toString();
            String amountt = amount.getText().toString();


            if((phonee.length() < 1) | (amountt.length() < 1)){
                Toast.makeText(this,"Fill all fields",Toast.LENGTH_LONG).show();
            }else{
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Processing payment...");
                progressDialog.show();
                //final Model model = Model.getInstance(requireActivity().getApplication());
                final Model model = Model.getInstance(payActivity.this.getApplication());
                model.contribute(phonee, amountt,id, new AbstractAPIListener() {
                    @Override
                    public void onContribute(User user) {
                        model.setUser(user);
                        if ("200".equals(user.getResponceStatusCode())) {
                            Toast.makeText(payActivity.this, "Complete payment by entering your pin on the STK push", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(payActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });
            }

        });



    }
}