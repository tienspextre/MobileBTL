package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    MemberDBH memberDBH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences sp=getSharedPreferences("Member", MODE_PRIVATE);
        TextView loginTitle = findViewById(R.id.loginTitle);
        Button loginButton = findViewById(R.id.loginButton);
        EditText userNameField = findViewById(R.id.usernameInput);
        EditText passWordField = findViewById(R.id.passwordInput);
        if (sp.getInt("ID", -1) != -1){
            Intent intent = new Intent(LoginActivity.this, StartUp.class);
            startActivity(intent);
            finish();
        }

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Member member = new Member();
                member.setUsername(userNameField.getText().toString());
                member.setPassword(passWordField.getText().toString());
//                System.out.println(member.getUsername() + member.getPassword());

                Member member2 = memberDBH.checkLogin(LoginActivity.this, member);
                if (member2 != null){
                    Intent intent = new Intent(LoginActivity.this, StartUp.class);
                    SharedPreferences.Editor Ed=sp.edit();
                    Ed.putInt("ID", member2.getId());
                    Ed.putString("Name", member2.getName());
                    Ed.commit();
                    startActivity(intent);
                    finish();
                }
                else {
                    View rootView = findViewById(android.R.id.content);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(rootView.getWindowToken(), 0);
                    Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
