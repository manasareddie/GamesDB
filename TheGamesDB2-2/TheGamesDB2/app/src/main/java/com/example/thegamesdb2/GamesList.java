package com.example.thegamesdb2;

/**
 * Created by Sandeep on 2/20/2017.
 */

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Arrays;

public class GamesList implements Serializable {
    String baseUrl;
    String imageurl;
    int counter;
    String[] id;
    String title;
    String overview;
    String genre;
    String publisher;
    String youtubeLink;
    String logo;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap;

    public String getYoutubeLink() {
        return youtubeLink;
    }

    public void setYoutubeLink(String youtubeLink) {
        this.youtubeLink = youtubeLink;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "GameList{" +
                "baseUrl='" + baseUrl + '\'' +
                ", imageurl='" + imageurl + '\'' +
                ", counter=" + counter +
                ", id=" + Arrays.toString(id) +
                ", title='" + title + '\'' +
                ", overview='" + overview + '\'' +
                ", genre='" + genre + '\'' +
                ", publisher='" + publisher + '\'' +
                ", youtubeLink='" + youtubeLink + '\'' +
                '}';
    }
}
