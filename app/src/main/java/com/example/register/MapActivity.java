package com.example.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MapActivity extends AppCompatActivity {
    Button logout;
    private DrawerLayout drawer;
    private FirebaseAuth firebaseAuth;
    Button btnRegister, btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        logout = findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //showOnClick();
                SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember", "false");
                editor.apply();
                finish();
            }
        });

    }

    public void showOnClick(){
        btnSignIn = findViewById(R.id.emailField);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            btnSignIn.setText(firebaseUser.getEmail());
        }else {
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
    }
}