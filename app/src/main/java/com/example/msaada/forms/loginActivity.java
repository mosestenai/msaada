package com.example.msaada.forms;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.msaada.helpers.Model;
import com.example.msaada.R;
import com.example.msaada.helpers.User;
import com.example.msaada.admin.AdminActivity;
import com.example.msaada.api.AbstractAPIListener;
import com.example.msaada.contributor.accountActivity;
import com.example.msaada.forms.registerActivity;

public class loginActivity extends AppCompatActivity {
    Button login;
    EditText password,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //hide status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        //get inputs
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);



        login.setOnClickListener(v -> {

            String emaill = email.getText().toString();
            String passwordd =  password.getText().toString();
            if(emaill.length()   > 0 && passwordd.length() > 0) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                if (emaill.equals("admin@msaada.com")) {
                    Intent intent = new Intent(loginActivity.this, AdminActivity.class);
                    startActivity(intent);
                } else{
                    //final Model model = Model.getInstance(requireActivity().getApplication());
                    final Model model = Model.getInstance(loginActivity.this.getApplication());
                model.login(emaill, passwordd, new AbstractAPIListener() {
                    @Override
                    public void onLogin(User user) {
                        model.setUser(user);
                        switch (user.getToken()) {
                            case "other": {
                                Intent intent = new Intent(loginActivity.this, accountActivity.class);
                                intent.putExtra("username",user.getUsername());
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            case "admin": {
                                Intent intent = new Intent(loginActivity.this, AdminActivity.class);
                                startActivity(intent);
                                progressDialog.dismiss();
                                break;
                            }
                            default:
                                Toast.makeText(loginActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                break;
                        }


                    }
                });
            }

            }

            else if((emaill.length() & password.length()) == 0 ){
                Toast.makeText(loginActivity.this, "Fill all fields", Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void signup(View view) {
       Intent intent = new Intent(loginActivity.this, registerActivity.class);
       startActivity(intent);
    }
}