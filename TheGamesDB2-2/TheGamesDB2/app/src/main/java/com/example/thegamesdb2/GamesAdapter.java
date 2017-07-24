package com.example.thegamesdb2;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 2/20/2017.
 */

public class GamesAdapter extends ArrayAdapter<Games> implements GameAsync.IGameList,GetImageAsyncTask.IImage {
    List<Games> mData;
    Context mContext;
    int mResource;
    ImageView iv;

    public GamesAdapter(Context context, int resource, List<Games> objects) {
        super(context, resource, objects);
        this.mContext=context;
        this.mData= objects;
        this.mResource= resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView== null)
        {
            LayoutInflater inflater= (LayoutInflater) mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(mResource,parent,false);
        }
        Games game= mData.get(position);

        new GameAsync(GamesAdapter.this).execute("http://thegamesdb.net/api/GetGame.php?id="+game.getId()+".png");

        iv=(ImageView) convertView.findViewById(R.id.imageView2);

        TextView tv= (TextView) convertView.findViewById(R.id.textView);
        tv.setText(game.getTitle()+"    released in  :"+game.getReleaseDate()+"     platform  :"+game.getPlatform());


        return convertView;

    }

    @Override
    public void onGetGameList(ArrayList<GamesList> al) {

    }

    @Override
    public void onGetImage(Bitmap al) {
        iv.setImageBitmap(al);
    }
}
