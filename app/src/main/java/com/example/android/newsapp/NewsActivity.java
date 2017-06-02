package com.example.android.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Loader;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    public static final String LOG_TAG = NewsActivity.class.getName();

    private static final String URL_REQUEST = "https://content.guardianapis.com/search?api-key=5594e44d-1093-4556-baa3-4ae51a4bad7c";

    private NewsAdapter mAdapter;

    private static final int NEWS_LOADER_ID = 1;

    private TextView mEmptyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView newsList = (ListView) findViewById(R.id.list);
        mEmptyText = (TextView) findViewById(R.id.empty_view);
        newsList.setEmptyView(mEmptyText);

        mAdapter = new NewsAdapter(this, new ArrayList<News>());
        newsList.setAdapter(mAdapter);
        newsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News current = mAdapter.getItem(position);

                Uri newsUri = Uri.parse(current.getUrl());
                Intent webIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(webIntent);
            }
        });

        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID, null, this);
        } else {
            View loading = findViewById(R.id.loading_indicator);
            loading.setVisibility(View.GONE);

            mEmptyText.setText(R.string.no_connection);
        }

    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, URL_REQUEST);
    }

    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> news) {
        View loading = findViewById(R.id.loading_indicator);
        loading.setVisibility(View.GONE);

        mEmptyText.setText(R.string.no_news);
        mAdapter.clear();

        if (news != null && !news.isEmpty()) {
            mAdapter.addAll(news);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        mAdapter.clear();
    }
}
