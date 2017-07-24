package com.example.thegamesdb2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class GamesDetails extends AppCompatActivity implements GetImageAsyncTask.IImage {
    TextView title, genre, publisher;
    LinearLayout vertical;
    ArrayList<GamesList> al;
    ArrayList<Games> al2;
    int counter=0;
    String[] id1,id2;
    String t, youtubeLink;
    static String MY_KEY="games";
    static String MY_KEY2="idlist";
    static String MY_KEY3="title";
    static String MY_KE="list";
    static String MY_KEY4="youtube";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games_details);
        Log.d("demo","in next intent");
        title= (TextView) findViewById(R.id.title_textview);
        genre= (TextView) findViewById(R.id.Genre_textview2);
        publisher= (TextView) findViewById(R.id.pub_textview);
        vertical= (LinearLayout) findViewById(R.id.vertical);
        id1= new String[10];
        TextView tv= new TextView(this);

        al= (ArrayList<GamesList>) getIntent().getExtras().getSerializable(MainActivity.MY_KEY);
        al2= (ArrayList<Games>) getIntent().getExtras().getSerializable(MainActivity.MY_KEY2);
        for (GamesList gl:al ) {
            t = gl.getTitle();
            counter = gl.getCounter();
            id1 = gl.getId();
            id2 = new String[counter];
            for (int i = 1; i <= counter; i++) {
                id2[i - 1] = id1[i];

            }

            title.setText(gl.getTitle());
            genre.setText(gl.getGenre());
            tv.setText(gl.getOverview());
            publisher.setText(gl.getPublisher());
            // vertical.removeAllViews();
            vertical.addView(tv);
            Log.d("demo", gl.toString());
            new GetImageAsyncTask(GamesDetails.this).execute(gl.getBaseUrl()+gl.getImageurl());
        }
        findViewById(R.id.finish_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        findViewById(R.id.similargames_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(GamesDetails.this, SimilarGames.class);
                i.putExtra(MY_KEY,(Serializable)al2);
                i.putExtra(MY_KE,(Serializable)al);
                i.putExtra(MY_KEY2,id2);
                i.putExtra(MY_KEY3,t);
                startActivity(i);
            }
        });

    }

    @Override
    public void onGetImage(Bitmap al) {
        ImageView iv= (ImageView) findViewById(R.id.imageView);
        iv.setImageBitmap(al);

    }
}
