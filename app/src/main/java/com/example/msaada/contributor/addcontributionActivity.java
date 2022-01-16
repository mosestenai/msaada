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

public class addcontributionActivity extends AppCompatActivity {
    Button submit;
    EditText heading,description,username1,username2,phone1,phone2,amount,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontribution);

        //get all inputs
        heading = findViewById(R.id.heading);
        description = findViewById(R.id.description);
        username1 = findViewById(R.id.username1);
        username2 = findViewById(R.id.username2);
        phone1 = findViewById(R.id.phone1);
        phone2 = findViewById(R.id.phone2);
        amount = findViewById(R.id.amount);
        name = findViewById(R.id.name);

        submit = findViewById(R.id.submit);

        Intent test = getIntent();
        String username = test.getStringExtra("username");

        name.setText(username);

        submit.setOnClickListener(v->{
            //convert inputs into strings
            String namee = name.getText().toString();
            String headingg = heading.getText().toString();
            String descriptionn = description.getText().toString();
            String usernamee1 = username1.getText().toString();
            String usernamee2 = username2.getText().toString();
            String phonee1 = phone1.getText().toString();
            String phonee2 = phone2.getText().toString();
            String amountt = amount.getText().toString();
            if(headingg.length()  > 1){
                //displaying progress bar
                final ProgressDialog progressDialog = new ProgressDialog(addcontributionActivity.this);
                progressDialog.setMessage("Registering ...");
                progressDialog.show();
                //final Model model = Model.getInstance(requireActivity().getApplication());
                final Model model = Model.getInstance(addcontributionActivity.this.getApplication());
                model.addcontribution(headingg,descriptionn,usernamee1,usernamee2,phonee1,phonee2,amountt,namee, new AbstractAPIListener() {
                    @Override
                    public void onAddcontribution(User user) {
                        model.setUser(user);
                        if ("fundraiser created successfully.".equals(user.getSuccess())) {
                            Toast.makeText(addcontributionActivity.this, "Success.Your request is awaiting verification", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(addcontributionActivity.this,com.example.msaada.contributionsActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(addcontributionActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


            });
            }
            else if((headingg.length() & descriptionn.length()) == 0 ){
                Toast.makeText(addcontributionActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();


            }

        });
        }}