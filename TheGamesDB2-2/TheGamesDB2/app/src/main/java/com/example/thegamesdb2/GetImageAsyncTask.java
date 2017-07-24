package com.example.thegamesdb2;

/**
 * Created by Sandeep on 2/20/2017.
 */

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class GetImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
    GamesDetails activity;
    ProgressBar progressBar;

    public GetImageAsyncTask(GamesDetails activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {

        Button b2= (Button) activity.findViewById(R.id.similargames_button);
        Button b3= (Button) activity.findViewById(R.id.finish_button);

        progressBar= (ProgressBar) activity.findViewById(R.id.imagepb);
        progressBar.setVisibility(View.VISIBLE);

        b2.setEnabled(false);
        b3.setEnabled(false);
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        BufferedReader reader = null;
        InputStream in = null;
        try {
            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");//connection.setRequestMethod("POST");//
            in = connection.getInputStream();
            // connection.getInputStream();//for xml parsing
            Bitmap image = BitmapFactory.decodeStream(connection.getInputStream());
            return image;
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e) {

            }
        }
        return null;
    }

    static public  interface IImage{
        public void onGetImage(Bitmap al);
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        Button b2= (Button) activity.findViewById(R.id.similargames_button);
        Button b3= (Button) activity.findViewById(R.id.finish_button);

        progressBar= (ProgressBar) activity.findViewById(R.id.imagepb);
        progressBar.setVisibility(View.INVISIBLE);
        b3.setEnabled(true);
        b2.setEnabled(true);
        activity.onGetImage(bitmap);
    }


}
