/*
 * Copyright (c) 2018.
 * Developed By : Muriel Mary Jacob
 * Developed On : 11/27/18 11:54 AM
 */

package rkjc.example.com.newsapphw2;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.util.Log;

@Database(entities = {NewsItem.class}, version = 1, exportSchema = false)
public abstract class NewsRoomDatabase extends RoomDatabase {
    private static final String TAG = "NewsRoomDatabase";
    private static NewsRoomDatabase INSTANCE;

    public static NewsRoomDatabase getDatabase(Context context) {
        Log.d(TAG, "getDatabase: Inside");
        if (INSTANCE == null) {
            synchronized (NewsRoomDatabase.class) {
                Log.d(TAG, "getDatabase: Creating new INSTANCE");
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), NewsRoomDatabase.class, "news_db").build();
            }
        }
        Log.d(TAG, "getDatabase: Leaving");
        return INSTANCE;
    }

    public abstract NewsItemDao newsItemDao();

}
