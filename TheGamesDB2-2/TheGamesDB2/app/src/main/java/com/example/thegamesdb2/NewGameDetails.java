package com.example.thegamesdb2;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class NewGameDetails extends AppCompatActivity implements IAsyncTask.IImage{
ArrayList<GamesList> gl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_details);
        Games games= (Games) getIntent().getExtras().getSerializable(SimilarGames.MY_KEY);
        gl= (ArrayList<GamesList>) getIntent().getExtras().getSerializable(SimilarGames.MY_KEY2);
        Log.d("demo","inside new ");
        TextView titile= (TextView) findViewById(R.id.textView2);
        TextView genre= (TextView) findViewById(R.id.textView5);
        TextView pub= (TextView) findViewById(R.id.textView7);
        titile.setText(games.getTitle());
        LinearLayout ll= (LinearLayout) findViewById(R.id.overvw);
        TextView tt= new TextView(this);
        Log.d("demo","after new");
        for (GamesList gll:gl      ) {
            if(gll.getId().equals(games.getId()))
            {
                Log.d("demo",""+gll.toString());
                tt.setText(gll.getOverview());
                genre.setText(gll.getGenre());
                pub.setText(gll.getPublisher());
                new IAsyncTask(NewGameDetails.this).execute(gll.getBaseUrl()+gll.getImageurl());
            }
        }
        ll.addView(tt);
        findViewById(R.id.fsh_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public void onGetImage(Bitmap al) {
        ImageView iv= (ImageView) findViewById(R.id.imageView3);
       iv.setImageBitmap(al);
    }
}
