package com.example.msaada.forms;

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
import com.example.msaada.mainktActivity;

public class feedbackActivity extends AppCompatActivity {
    EditText feedback;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        submit = findViewById(R.id.submit);
        feedback = findViewById(R.id.feedback);

        submit.setOnClickListener(v->{
            String feedbackk = feedback.getText().toString();
            if(feedbackk.length() != 0){
                final ProgressDialog progressDialog = new ProgressDialog(feedbackActivity.this);
                progressDialog.setMessage("Submitting your feedback ...");
                progressDialog.show();
                final Model model = Model.getInstance(feedbackActivity.this.getApplication());
                model.postfeedback(feedbackk, new AbstractAPIListener() {
                    @Override
                    public void onPostfeedback(User user) {
                        model.setUser(user);
                        if ("Success".equals(user.getError())) {
                            Toast.makeText(feedbackActivity.this, "Thanks for your feedback", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(feedbackActivity.this, mainktActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(feedbackActivity.this, user.getError(), Toast.LENGTH_SHORT).show();
                        }
                        progressDialog.dismiss();
                    }


                });
            }else {
                Toast.makeText(feedbackActivity.this,"Type how you feel about this app",Toast.LENGTH_SHORT).show();
            }
        });
    }
}