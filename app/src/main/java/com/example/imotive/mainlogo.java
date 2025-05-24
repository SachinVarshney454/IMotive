package com.example.imotive;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.imotive.Authentication.loginscreen;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class mainlogo extends AppCompatActivity {
    Handler handler;
    FirebaseFirestore db;
    FirebaseAuth auth;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mainlogo);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        db = FirebaseFirestore.getInstance();
//        Toast.makeText(this, "ustreteer", Toast.LENGTH_SHORT).show();
//        if (user != null) {
////             Toast.makeText(this, "user", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(mainlogo.this, MainActivity.class);
//            startActivity(intent);
//
//
//        } else {
            startActivity(new Intent(mainlogo.this, loginscreen.class));
//             Toast.makeText(this, "nouser", Toast.LENGTH_SHORT).show();
//            finish();
//        }

    }

}