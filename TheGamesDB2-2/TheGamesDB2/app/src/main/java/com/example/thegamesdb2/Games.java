package com.example.thegamesdb2;
import android.graphics.Bitmap;

import java.io.Serializable;
/**
 * Created by Sandeep on 2/20/2017.
 */

public class Games implements Serializable{
    String id, title, releaseDate, platform;

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    Bitmap bitmap;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlatform() {
        return platform;
    }




    public void setPlatform(String platform) {
        this.platform = platform;
    }
    @Override
    public String toString() {
        return "Games{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releaseDate='" + releaseDate + '\'' +
                ", platform='" + platform + '\'' +
                '}';
    }
}
