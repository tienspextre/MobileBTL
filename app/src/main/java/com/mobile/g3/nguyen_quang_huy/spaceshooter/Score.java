package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Score {
    private String playerName;
    private int score;
    private String timestamp; // Using String to represent the TIMESTAMP as a formatted date string

    // Date format used for formatting and parsing timestamps
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public Score(String playerName, int score, String timestamp) {
        this.playerName = playerName;
        this.score = score;
        this.timestamp = timestamp;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getTimestamp() {
        return timestamp;
    }

    // Method to convert timestamp string to Date object
    public Date getTimestampAsDate() throws ParseException {
        return dateFormat.parse(timestamp);
    }


    // Method to convert Date object to timestamp string
    public String formatDateToString(Date date) {
        return dateFormat.format(date);
    }
}
