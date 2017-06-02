package com.example.android.newsapp;

/**
 * Created by Mayank on 19-03-2017.
 */

public class News {


    private String mSection;
    private String mTitle;
    private String mUrl;

    public News(String Section, String Title, String Url) {
        mSection = Section;
        mTitle = Title;
        mUrl = Url;
    }

    public String getSection() {
        return mSection;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getUrl() {
        return mUrl;
    }

}
