/*
 * Copyright (c) 2018.
 * Developed By : Muriel Mary Jacob
 * Developed On : 11/27/18 11:11 AM
 */

package rkjc.example.com.newsapphw2;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface NewsItemDao {

    @Insert
    void insert(List<NewsItem> items);

    @Query("DELETE FROM news_item")
    void clearAll();

    @Query("SELECT * FROM news_item")
    LiveData<List<NewsItem>> loadAllNewsItems();

    @Query("SELECT COUNT(*) FROM news_item")
    int getNumberOfRows();
}
