package com.example.thegamesdb2;

/**
 * Created by Sandeep on 2/20/2017.
 */

import android.app.ProgressDialog;
import android.os.AsyncTask;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
public class GetGamesListAsyncTask extends AsyncTask<String, Void, ArrayList<GamesList>> {
    MainActivity activity;
    ProgressDialog pd;

    public GetGamesListAsyncTask(MainActivity activity) {
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
    protected ArrayList<GamesList> doInBackground(String... params) {
        try {
            URL url= new URL(params[0]);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int statusCode= connection.getResponseCode();
            if(statusCode == HttpURLConnection.HTTP_OK){
                InputStream inputStream= connection.getInputStream();
                return GamesListUtil.parseGames(inputStream);

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

    protected void onPostExecute(ArrayList<GamesList> games) {
        pd.dismiss();
        activity.onGetGameList(games);
        super.onPostExecute(games);
    }

    static public  interface IGameList{
        public void onGetGameList(ArrayList<GamesList> al);
    }

}
