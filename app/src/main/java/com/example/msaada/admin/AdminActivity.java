package com.example.msaada.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.msaada.R;
import com.example.msaada.contributor.addcontributionActivity;
import com.example.msaada.admin.deleteActivity;
import com.example.msaada.admin.verifyActivity;

public class AdminActivity extends AppCompatActivity {
Button add,verify,delete,transactions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        add = findViewById(R.id.add);
        verify = findViewById(R.id.verify);
        delete = findViewById(R.id.delete);
        transactions = findViewById(R.id.transactions);

        //go to addcontribution activity when add contribution button is clicked
        add.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, addcontributionActivity.class);
            startActivity(intent);
        });
        //go to transactions activity when view transactions button is clicked
        transactions.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, com.example.msaada.admin.transactions.class);
            startActivity(intent);
        });

        //go to verify contribution activity when verify contribution button is clicked
        verify.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, verifyActivity.class);
            startActivity(intent);
        });

        //go to delete contribution activity when delete contribution button is clicked
        delete.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, deleteActivity.class);
            startActivity(intent);
        });
    }
}