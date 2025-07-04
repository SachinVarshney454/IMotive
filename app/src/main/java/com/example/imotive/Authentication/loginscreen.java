package com.example.imotive.Authentication;

import static android.content.ContentValues.TAG;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imotive.MainActivity;
import com.example.imotive.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginscreen extends AppCompatActivity {
    EditText email;
    EditText password;
    FirebaseAuth mauth;
    FirebaseFirestore db;
    Button login; Button signup;
    ImageButton show;Boolean showcond=false;
    String em; String pass;
    ProgressBar progress;
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.loginscreen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mauth =FirebaseAuth.getInstance();
        loading=findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        login=findViewById(R.id.login);
        progress=findViewById(R.id.progress);
        signup=findViewById(R.id.signup);
        ImageButton show= findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showcond) {
                    // Hide password
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    show.setImageResource(R.drawable.eye);
                } else {

                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    show.setImageResource(R.drawable.eye);
                }
                showcond = !showcond;
                password.setSelection(password.getText().length());

            }
        });

        db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
//        if(user!=null){
//            Intent intent = new Intent(logicscreen.this, MainActivity.class);
//            startActivity(intent);
//            finish();
//        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 em =email.getText().toString();
                 pass =password.getText().toString();
                 if(em.equals(null)&&pass.equals(null)){
                     Toast.makeText(loginscreen.this, "Please Enter Email And Password", Toast.LENGTH_SHORT).show();
                 }
                else  if(em==null){
                     Toast.makeText(loginscreen.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                 }
               else  if(em==null){
                    Toast.makeText(loginscreen.this, "Please Enter Password", Toast.LENGTH_SHORT).show();
                }
                if(em!=null&&pass!=null){
                    loading.setVisibility(VISIBLE);
                    login.setVisibility(GONE);
//                    progress.setVisibility(View.VISIBLE);
                    signin(em,pass);

                }



            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(loginscreen.this, signup.class);
                startActivity(intent);



            }
        });



    }
    public void signin(String email,String password){
        mauth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent= new Intent(loginscreen.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mauth.getCurrentUser();

                        } else {
                            loading.setVisibility(GONE);
                            login.setVisibility(VISIBLE);
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(loginscreen.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }



}