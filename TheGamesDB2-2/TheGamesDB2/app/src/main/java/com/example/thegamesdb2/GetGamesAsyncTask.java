package com.example.thegamesdb2;

/**
 * Created by Sandeep on 2/20/2017.
 */

import android.app.ProgressDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.Button;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Sandeep on 2/17/2017.
 */

public class GetGamesAsyncTask extends AsyncTask<String, Void, ArrayList<Games>> {
    MainActivity activity;
    ProgressDialog pd;

    public GetGamesAsyncTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        pd= new ProgressDialog(activity);
        pd.show();
        pd.setCancelable(false);
        super.onPreExecute();
    }

    @Override
    protected ArrayList<Games> doInBackground(String... params) {
        try {
            URL url= new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode= connection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream= connection.getInputStream();
                return GamesUtil.parseGames(inputStream);

            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;

    }
    @Override

    protected void onPostExecute(ArrayList<Games> games) {
        // pd= new ProgressDialog(activity);
       pd.dismiss();
        activity.onGetGame(games);
        super.onPostExecute(games);
    }

    static public  interface IGame{
        public void onGetGame(ArrayList<Games> al);
    }

}
