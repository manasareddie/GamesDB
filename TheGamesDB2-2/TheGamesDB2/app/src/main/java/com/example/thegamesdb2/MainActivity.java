package com.example.thegamesdb2;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GetGamesAsyncTask.IGame,GetGamesListAsyncTask.IGameList{
    EditText et;
    ArrayList<Games> gamesArrayList;
    ArrayList<GamesList> gamesListArrayList;
    static String MY_KEY="gameslistkey";
    static String MY_KEY2="gamekey";
    ListView listView;
    String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamesArrayList= new ArrayList<>();
        gamesArrayList=null;
        gamesListArrayList= new ArrayList<>();
        listView= (ListView) findViewById(R.id.scrollview);
        et= (EditText) findViewById(R.id.key_editText);

        findViewById(R.id.search_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConnectivityManager cm =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();

                if(netInfo != null && netInfo.isConnected())
                {

                    Log.d("demo", "http://thegamesdb.net/api/GetGamesList.php?name=" + String.valueOf(et.getText().toString()));
                    url = "http://thegamesdb.net/api/GetGamesList.php?name=" + String.valueOf(et.getText().toString()).toLowerCase();
                    if (Patterns.WEB_URL.matcher(url).matches()) {
                        new GetGamesAsyncTask(MainActivity.this).execute("http://thegamesdb.net/api/GetGamesList.php?name=" + et.getText().toString());
                        }
                    else
                       Toast.makeText(getApplicationContext(), "enter valid game", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"please check internet connection",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onGetGame(ArrayList<Games> al) {
        Log.d("demo","hello"+al.get(0).toString());
        gamesArrayList=al;
        GamesAdapter adapter=new GamesAdapter(this, R.layout.row_item_layout,gamesArrayList);
        listView.setAdapter(adapter);
        adapter.setNotifyOnChange(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("demo",""+position+"value is"+gamesArrayList.get(position).toString());
                ConnectivityManager cm =
                        (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo netInfo = cm.getActiveNetworkInfo();

                if(netInfo != null && netInfo.isConnected()) {
                    if (position >= 0) {
                        Log.d("demo", "http://thegamesdb.net/api/GetGame.php?id=" + gamesArrayList.get(position).getId());
                       new GetGamesListAsyncTask(MainActivity.this).execute("http://thegamesdb.net/api/GetGame.php?id=" + gamesArrayList.get(position).getId());
                    } else
                        Toast.makeText(getApplicationContext(), "please select an option", Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(),"please check internet connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onGetGameList(ArrayList<GamesList> al) {
        gamesListArrayList=al;
        Intent i= new Intent(MainActivity.this,GamesDetails.class);
        i.putExtra(MY_KEY,(Serializable) al);
        i.putExtra(MY_KEY2,(Serializable)gamesArrayList);
        startActivity(i);

    }
}
