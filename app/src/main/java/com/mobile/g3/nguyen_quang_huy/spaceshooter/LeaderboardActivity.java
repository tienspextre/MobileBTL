package com.mobile.g3.nguyen_quang_huy.spaceshooter;


import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

public class LeaderboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistoryDBH historyDBH;
    private TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        tableLayout = findViewById(R.id.tableLayout);

        // Load scores from the database
        List<Score> scores = historyDBH.getAllHistory(LeaderboardActivity.this);

        // Add rows to the table
        for (Score score : scores) {
            try {
                addTableRow(score.getPlayerName(), String.valueOf(score.getScore()), score.getTimestampAsDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void addTableRow(String playerName, String score, Date date) {
        TableRow row = new TableRow(this);

        TextView nameTextView = new TextView(this);
        nameTextView.setText(playerName);
        nameTextView.setPadding(8, 8, 8, 8);
        nameTextView.setGravity(Gravity.CENTER);
        row.addView(nameTextView);

        TextView dateTextView = new TextView(this);
        System.out.println(date);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+7"));
        dateTextView.setText(sdf.format(date));
        dateTextView.setPadding(8, 8, 8, 8);
        dateTextView.setGravity(Gravity.CENTER);
        row.addView(dateTextView);

        TextView scoreTextView = new TextView(this);
        scoreTextView.setText(score);
        scoreTextView.setPadding(8, 8, 8, 8);
        scoreTextView.setGravity(Gravity.CENTER);
        row.addView(scoreTextView);

        tableLayout.addView(row);
    }
}

