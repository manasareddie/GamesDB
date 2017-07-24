package com.example.thegamesdb2;

import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by Sandeep on 2/20/2017.
 */

public class GamesDisplay {
    ImageView iv;
    String gamedetails;

    public GamesDisplay(ImageView iv, String gamedetails) {
        this.iv = iv;
        this.gamedetails = gamedetails;
    }

    @Override
    public String toString() {
        return "GamesDisplay{" +
                "hello...."+
                '}';
    }
}
