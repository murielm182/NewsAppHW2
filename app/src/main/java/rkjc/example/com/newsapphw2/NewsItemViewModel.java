/*
 * Copyright (c) 2018.
 * Developed By : Muriel Mary Jacob
 * Developed On : 11/27/18 12:18 PM
 */

package rkjc.example.com.newsapphw2;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {
    private static final String TAG = "NewsItemViewModel";

    private NewsItemRepository mRepository;
    private LiveData<List<NewsItem>> mAllNewsItems;

    public NewsItemViewModel(@NonNull Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllNewsItems = mRepository.getAllNewsItems();
    }

    LiveData<List<NewsItem>> getAllNewItems() {
        return mAllNewsItems;
    }

    public void insert() {
        Log.d(TAG, "insert: calling NewsItemRepository.insert()");
        mRepository.insert();
    }

    public Boolean getServerStatus() {
        return mRepository.getServerStatus();
    }
   /* private final LiveData<List<NewsItem>> newsItemList;
    private NewsRoomDatabase newsRoomDatabase;

    public NewsItemViewModel(Application application) {
        super(application); //for AndroidViewModel
        Log.d(TAG, "NewsItemViewModel: Constructor");
        //newsRoomDatabase = NewsRoomDatabase.getDatabase(this.getApplication());
        newsRoomDatabase = NewsRoomDatabase.getDatabase(this.getApplication());
        newsItemList = newsRoomDatabase.newsItemDao().loadAllNewsItems();
    }

    public LiveData<List<NewsItem>> getNewsItemsList() {
        Log.d(TAG, "getNewsItemsList: Inside");
        return newsItemList;
    }

    public void deleteItem(NewsItem borrowModel) {
        new deleteAsyncTask(newsRoomDatabase).execute(borrowModel);
    }

    private static class deleteAsyncTask extends AsyncTask<NewsItem, Void, Void> {
        private NewsRoomDatabase db;

        deleteAsyncTask(NewsRoomDatabase newsRoomDatabase) {
            db = newsRoomDatabase;
        }

        @Override
        protected Void doInBackground(final NewsItem... params) {
            db.newsItemDao().clearAll();
            return null;
        }

    }*/
}
