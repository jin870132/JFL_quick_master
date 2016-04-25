package com.jinfulin.quick_master.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;


import java.io.Closeable;

/**
 * 数据库缓存键值对
 *
 * @author wyz
 */
public class DatabaseCache implements Cache {

    private static final String CACHE_DB_NAME = "secretlisa_cache.db";

    private static final int CACHE_DB_VERSION = 1;

    private static final String CREATE_SQL = "CREATE TABLE cache_table (cache_key TEXT PRIMARY KEY ,v_int INTEGER ,v_text TEXT, v_long INTEGER, v_bool INTEGER)";

    private static final String TABLE_NAME = "cache_table";

    private static final String KEY = "cache_key";

    private static DatabaseCache sInstance;

    private SQLiteDatabase mSqliteDb;

    private DatabaseCache(Context context) {
        mSqliteDb = new CacheDBHelper(context).getWritableDatabase();
    }

    public static DatabaseCache getInstance(Context context) {
        if (sInstance == null) {
            synchronized (DatabaseCache.class) {
                if (sInstance == null)
                    sInstance = new DatabaseCache(context.getApplicationContext());
            }
        }
        return sInstance;
    }

    private class CacheDBHelper extends SQLiteOpenHelper {

        public CacheDBHelper(Context context) {
            super(context, CACHE_DB_NAME, null, CACHE_DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_SQL);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }

    }

    @Override
    public void putBoolean(String key, boolean value) {
        if (null == key)
            throw new IllegalArgumentException(
                    "the argument key can not be null");
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put("v_bool", value);
        mSqliteDb.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void putInt(String key, int value) {
        if (null == key)
            throw new IllegalArgumentException(
                    "the argument key can not be null");
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put("v_int", value);
        mSqliteDb.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void putLong(String key, long value) {
        if (null == key)
            throw new IllegalArgumentException(
                    "the argument key can not be null");
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put("v_long", value);
        mSqliteDb.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void putString(String key, String value) {
        if (null == key)
            throw new IllegalArgumentException(
                    "the argument key can not be null");
        ContentValues values = new ContentValues();
        values.put(KEY, key);
        values.put("v_text", value);
        mSqliteDb.insertWithOnConflict(TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public boolean getBoolean(String key, boolean defaultValue) {
        Cursor cursor = null;
        boolean value = defaultValue;
        try {
            cursor = mSqliteDb.rawQuery("SELECT v_bool FROM cache_table WHERE cache_key = ?",
                    new String[]{key});
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                int v = cursor.getInt(0);
                if (v > 0) {
                    value = true;
                } else {
                    value = false;
                }
            }
        } finally {
            closeSilently(cursor);
        }
        return value;
    }

    @Override
    public int getInt(String key, int defaultValue) {
        Cursor cursor = null;
        int value = defaultValue;
        try {
            cursor = mSqliteDb.rawQuery("SELECT v_int FROM cache_table WHERE cache_key = ?",
                    new String[]{key});
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                value = cursor.getInt(0);
            }
        } finally {
            closeSilently(cursor);
        }
        return value;
    }

    @Override
    public long getLong(String key, long defaultValue) {
        Cursor cursor = null;
        long value = defaultValue;
        try {
            cursor = mSqliteDb.rawQuery("SELECT v_long FROM cache_table WHERE cache_key = ?",
                    new String[]{key});
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                value = cursor.getLong(0);
            }
        } finally {
            closeSilently(cursor);
        }
        return value;
    }

    @Override
    public String getString(String key, String defaultValue) {
        Cursor cursor = null;
        String value = defaultValue;
        try {
            cursor = mSqliteDb.rawQuery("SELECT v_text FROM cache_table WHERE cache_key = ?",
                    new String[]{key});
            if (cursor.getCount() > 0 && cursor.moveToFirst()) {
                value = cursor.getString(0);
            }
        } finally {
            closeSilently(cursor);
        }
        return value;
    }

    @Override
    public void removeKey(String key) {
        mSqliteDb
                .delete("cache_table", "cache_key = ? ", new String[]{key});
    }

    @Override
    public void removeKeys(String[] keys) {
        mSqliteDb.execSQL("DELETE FROM cache_table WHERE cache_key in (' " + TextUtils.join("', '", keys) + " ');");
    }

    @Override
    public void clearAll() {
        mSqliteDb.execSQL("DELETE FROM cache_table");
    }

    /**
     * 关闭流
     *
     * @param closeable
     */
    public void closeSilently(Closeable closeable) {
        if (closeable == null)
            return;
        try {
            closeable.close();
        } catch (Exception ignored) {

        }
    }
}
