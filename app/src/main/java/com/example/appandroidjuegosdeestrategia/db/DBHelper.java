package com.example.appandroidjuegosdeestrategia.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "users.db";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "CREATE TABLE users (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "username TEXT UNIQUE NOT NULL, " +
                        "password_hash TEXT NOT NULL, " +
                        "salt TEXT NOT NULL, " +
                        "created_at INTEGER NOT NULL)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    // Insertar usuario
    public boolean insertUser(String username, String passwordHash, String salt, long createdAt) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("username", username);
        values.put("password_hash", passwordHash);
        values.put("salt", salt);
        values.put("created_at", createdAt);

        long result = db.insert("users", null, values);
        return result != -1;
    }

    // Obtener usuario por username
    public Cursor getUser(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM users WHERE username = ?", new String[]{username});
    }
}