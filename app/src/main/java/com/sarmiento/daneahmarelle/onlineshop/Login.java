package com.sarmiento.daneahmarelle.onlineshop;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener{

    private Button btnSignIn;
    private TextView tvRegister;

    FirebaseAuth mAuth;
    private EditText et_email;
    private EditText et_pass;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        tvRegister = (TextView) findViewById(R.id.tvRegister);
        et_email = (EditText) findViewById(R.id.et_email);
        et_pass = (EditText) findViewById(R.id.et_pass);

        progressBar = (ProgressBar) findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.tvRegister).setOnClickListener(this);
        findViewById(R.id.btnSignIn).setOnClickListener(this);

//        btnSignIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                userLogin();
//
//            }
//
//        });
//
//        tvRegister.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Login.this, Register.class);
//                startActivity(intent);
//            }
//        });

    }

    private void userLogin() {
        String email = et_email.getText().toString().trim();
        String password = et_pass.getText().toString().trim();

            if (email.isEmpty()) {
                et_email.setError("Email is required.");
                et_email.requestFocus();
                return;
            }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Please enter a valid email.");
            et_email.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            et_pass.setError("Password is required.");
            et_pass.requestFocus();
            return;
        }

        if (password.length() < 6) {
            et_pass.setError("Minimum length of password should be 6.");
            et_pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password).  addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    finish();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvRegister:
                finish();
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.btnSignIn:
                userLogin();
                break;
        }
    }

}

