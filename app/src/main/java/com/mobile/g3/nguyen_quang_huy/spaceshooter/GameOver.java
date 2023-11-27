package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class GameOver extends AppCompatActivity {
    TextView tvPoints;
    private HistoryDBH historyDBH;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);

        int points = getIntent().getExtras().getInt("score");
        tvPoints = findViewById(R.id.tvPoints);
        tvPoints.setText("" + points);
        historyDBH.insert(GameOver.this, points);
    }

    public void restart(View v){
        Intent intent = new Intent(GameOver.this, StartUp.class);
        startActivity(intent);
        finish();
    }

    public void exit(View v){
        finish();
    }

}
