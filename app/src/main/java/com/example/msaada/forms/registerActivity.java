package com.example.msaada.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.msaada.helpers.Model;
import com.example.msaada.R;
import com.example.msaada.helpers.User;
import com.example.msaada.api.AbstractAPIListener;
import com.example.msaada.contributor.accountActivity;

public class registerActivity extends AppCompatActivity {
Button register;
EditText phone,username,email,password;
TextView error;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //hide status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //get inputs
        phone = findViewById(R.id.phone);
        username= findViewById(R.id.username);
        email = findViewById(R.id.email);
        password= findViewById(R.id.password);
        error = findViewById(R.id.error);

        register = findViewById(R.id.register);

        register.setOnClickListener(V->{
            String phonee = phone.getText().toString();
            String usernamee = username.getText().toString();
            String emaill = email.getText().toString();
            String passwordd = password.getText().toString();

            if(usernamee.length()  > 1){
                final ProgressDialog progressDialog = new ProgressDialog(registerActivity.this);
                progressDialog.setMessage("Registering ...");
                progressDialog.show();
                //final Model model = Model.getInstance(requireActivity().getApplication());
                final Model model = Model.getInstance(registerActivity.this.getApplication());
                model.register(usernamee,emaill,phonee,passwordd, new AbstractAPIListener() {
                    @Override
                    public void onRegister(User user){
                        model.setUser(user);
                        if ("user has registered successfully.".equals(user.getSuccess())) {
                            Intent intent = new Intent(registerActivity.this, accountActivity.class);
                            intent.putExtra("phone",phonee);
                            intent.putExtra("username",usernamee);
                            startActivity(intent);
                            } else {
                            Toast.makeText(registerActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();


                    }
                });


            }
            else if((usernamee.length() & password.length()) == 0 ){
                Toast.makeText(registerActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();


            }

        });

    }
    public void login(View view) {
        Intent intent = new Intent(registerActivity.this,loginActivity.class);
        startActivity(intent);
    }
}