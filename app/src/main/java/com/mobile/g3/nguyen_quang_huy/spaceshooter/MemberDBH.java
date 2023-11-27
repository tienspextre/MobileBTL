package com.mobile.g3.nguyen_quang_huy.spaceshooter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class MemberDBH {
    public static String getNameByID(Context context, int id){
        DBHelper dbHelper = new DBHelper(context);
        String name = "";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT name FROM member WHERE id = " + id, null);
        if (cursor.moveToNext()) {
            name = cursor.getString(0);
        }
        cursor.close();
        db.close();
        return name;
    }

    public static Member checkLogin(Context context, Member member){
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM member WHERE username = '" + member.getUsername() + "' AND password = '" + member.getPassword() + "'", null);
        if (cursor.moveToFirst()) {
            member.setId(cursor.getInt(0));
            member.setName(cursor.getString(1));
            member.setMusic(cursor.getInt(4));
            member.setSoueff(cursor.getInt(5));
            member.setSkin(cursor.getInt(6));
            cursor.close();
            db.close();
        } else member = null;
        cursor.close();
        db.close();
        return member;
    }
}
