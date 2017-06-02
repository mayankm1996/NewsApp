package com.example.android.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mayank on 19-03-2017.
 */

public class NewsAdapter extends ArrayAdapter<News> {
    public NewsAdapter(Context context, List<News> news) {
        super(context, 0, news);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_news, parent, false);

        }
        News currentNews = getItem(position);
        TextView sectiontext = (TextView) listItemView.findViewById(R.id.section);
        TextView titleText = (TextView) listItemView.findViewById(R.id.title);
        sectiontext.setText(currentNews.getSection());
        titleText.setText(currentNews.getTitle());
        return listItemView;
    }

}
