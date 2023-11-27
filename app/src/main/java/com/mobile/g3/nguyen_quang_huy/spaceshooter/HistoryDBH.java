package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class HistoryDBH {
    public static List<Score> getAllHistory(Context context){
        DBHelper dbHelper = new DBHelper(context);
        MemberDBH memberDBH = new MemberDBH();
        List<Score> scores = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM history ORDER BY score DESC", null);

        while (cursor.moveToNext()) {
            String playerName = memberDBH.getNameByID(context, cursor.getInt(3));
            int playerScore = cursor.getInt(2);
            String timestamp = cursor.getString(1);
            scores.add(new Score(playerName, playerScore, timestamp));
        }

        cursor.close();
        db.close();

        return scores;
    }

    public static boolean insert(Context context, int score){
        SharedPreferences sp = context.getSharedPreferences("Member", Context.MODE_PRIVATE);
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("idMember", sp.getInt("ID", -1));
        values.put("score", score);
        long row = db.insert("history", null, values);
        return (row > 0);
    }

    public static List<Score> getHistoryByID(Context context, int id){
        SharedPreferences sp = context.getSharedPreferences("Member", Context.MODE_PRIVATE);
        DBHelper dbHelper = new DBHelper(context);
        MemberDBH memberDBH = new MemberDBH();
        List<Score> scores = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM history WHERE idMember = " + id + " ORDER BY datetime DESC", null);

        while (cursor.moveToNext()) {
            String playerName = sp.getString("Name", null);
            int playerScore = cursor.getInt(2);
            String timestamp = cursor.getString(1);
            scores.add(new Score(playerName, playerScore, timestamp));
        }

        cursor.close();
        db.close();

        return scores;
    }
}
