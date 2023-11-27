package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class StartUp extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.startup);
        SharedPreferences sp = this.getSharedPreferences("Member", MODE_PRIVATE);
        TextView name = findViewById(R.id.name);
        name.setText("Hello " + sp.getString("Name", null));
    }

    public void startGame(View v){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
    public void leaderboardActivity(View v){
        startActivity(new Intent(this, LeaderboardActivity.class));
    }
    public void logout(View v){
        SharedPreferences sp = this.getSharedPreferences("Member", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
        Intent intent = new Intent(StartUp.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void historyActivity(View v){
        startActivity(new Intent(this, HistoryActivity.class));
    }
}
