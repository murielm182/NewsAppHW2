/*
 * Copyright (c) 2018.
 * Developed By : Muriel Mary Jacob
 * Developed On : 11/28/18 12:33 AM
 */

package rkjc.example.com.newsapphw2;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NewsItemRepository {
    private static final String TAG = "NewsItemRepository";
    private NewsItemDao mNewsItemDao;
    private LiveData<List<NewsItem>> mAllNewsItems;
    private NetworkUtils networkUtils = new NetworkUtils();
    private Boolean serverStatus;

    NewsItemRepository(Application app) {
        NewsRoomDatabase db = NewsRoomDatabase.getDatabase(app);
        mNewsItemDao = db.newsItemDao();
        mAllNewsItems = mNewsItemDao.loadAllNewsItems();
        serverStatus = Boolean.FALSE;
    }

    public Boolean getServerStatus() {
        return serverStatus;
    }

    LiveData<List<NewsItem>> getAllNewsItems() {
        return mAllNewsItems;
    }

    public void insert() {
        Log.d(TAG, "insert: Inside insert");
        new insertAsyncTask(mNewsItemDao).execute();
    }

    public void getAllNews() {
        new extractNewsAsyncTask(mNewsItemDao).execute();
    }

    private class insertAsyncTask extends AsyncTask<String, Void, Void> {
        private NewsItemDao mAsyncTaskDao;

        insertAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(String... params) {
            //NetworkUtils.getResponseFromHttpUrl(networkUtils.getNewsApiURL())
            try {
                Log.d(TAG, "doInBackground: About to clear all records");
                Log.d(TAG, "doInBackground: Calling API to refresh Data");
                ArrayList<NewsItem> newsItems = JsonUtils.parseNews(NetworkUtils.getResponseFromHttpUrl(networkUtils.getNewsApiURL()));
                Log.d(TAG, "doInBackground: API response Successful About to clear all records");
                Log.d(TAG, "doInBackground: Count of news_items ---before clearing--->>>>>" + mNewsItemDao.getNumberOfRows());
                mNewsItemDao.clearAll();
                Log.d(TAG, "doInBackground: Count of news_items ---after clearing--->>>>>" + mNewsItemDao.getNumberOfRows());
                mNewsItemDao.insert(newsItems);
                Log.d(TAG, "doInBackground: Count of news_items ---after inserting--->>>>>" + mNewsItemDao.getNumberOfRows());
            } catch (IOException e) {
                serverStatus = Boolean.FALSE;
                Log.e(TAG, "doInBackground: MMJ - Check IO Exception Raised  ", e);
                e.printStackTrace();
            }
            return null;
        }
    }

    private class extractNewsAsyncTask extends AsyncTask<String, Void, Void> {
        private NewsItemDao mAsyncTaskDao;

        extractNewsAsyncTask(NewsItemDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(String... params) {
            mAllNewsItems = mAsyncTaskDao.loadAllNewsItems();
            return null;
        }
    }

}
