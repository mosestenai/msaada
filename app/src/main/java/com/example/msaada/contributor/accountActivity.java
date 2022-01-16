package com.example.msaada.contributor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.example.msaada.R;
import com.example.msaada.contributor.addcontributionActivity;
import com.example.msaada.contributionsActivity;

public class accountActivity extends AppCompatActivity {
Button contribute,add,view;
TextView user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        contribute = findViewById(R.id.contribute);
        add = findViewById(R.id.add);
        view = findViewById(R.id.view);
        user = findViewById(R.id.username);

        Intent test = getIntent();
        String username = test.getStringExtra("username");

        String word = "Welcome " + username ;

        user.setText(word);

        contribute.setOnClickListener(V->{
            Intent intent = new Intent(accountActivity.this, contributionsActivity.class);
            startActivity(intent);
        });

        view.setOnClickListener(V->{
            Intent intent = new Intent(accountActivity.this,contributionsActivity.class);
            startActivity(intent);
        });

        add.setOnClickListener(v->{
            Intent intent = new Intent(accountActivity.this, addcontributionActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
        });
    }
}