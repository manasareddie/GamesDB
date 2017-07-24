package com.example.thegamesdb2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class SimilarGames extends AppCompatActivity {
    ArrayList<Games> arrayList;
    ArrayList<Games> arrayList2;
    ArrayList<GamesList> arrayList3;
    ArrayList<GamesList> arrayList4;
    String title;
    String[] ids;
    TextView tv;
    LinearLayout ll;
    static  String MY_KEY="game object";
    static  String MY_KEY2="game";
    Games game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_similar_games);
        arrayList2 = new ArrayList<>();
        arrayList = (ArrayList<Games>) getIntent().getExtras().getSerializable(GamesDetails.MY_KEY);
        arrayList3 = (ArrayList<GamesList>) getIntent().getExtras().getSerializable(GamesDetails.MY_KE);
        title = String.valueOf(getIntent().getExtras().get(GamesDetails.MY_KEY3));
        ids = (String[]) getIntent().getExtras().get(GamesDetails.MY_KEY2);
        ListView listView = (ListView) findViewById(R.id.scoll_view2);
        tv = (TextView) findViewById(R.id.similar_textview);
        ll = (LinearLayout) findViewById(R.id.vertical);

        tv.setText("Simailar games to:" + title);
        for (String id : ids) {
            for (Games g : arrayList) {
                if (g.getId().equals(id)) {
                    arrayList2.add(g);
                }
            }
            for (GamesList gl : arrayList3) {
                if (gl.getId().equals(id)) {
                    arrayList4.add(gl);
                }

            }
        }
        GamesListAdapter adapter = new GamesListAdapter(this, R.layout.row_item_layout2, arrayList2);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               Log.d("demo", "" + position + arrayList2.get(position).getId());
               Intent i = new Intent(SimilarGames.this, NewGameDetails.class);
               i.putExtra(MY_KEY, (Serializable) arrayList2.get(position));
               i.putExtra(MY_KEY2, (Serializable) arrayList4);
               startActivity(i);
           }
       });

        findViewById(R.id.finish_btton2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}