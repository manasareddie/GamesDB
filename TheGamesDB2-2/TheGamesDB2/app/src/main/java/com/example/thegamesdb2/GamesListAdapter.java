package com.example.thegamesdb2;

import android.support.annotation.NonNull;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Sandeep on 2/20/2017.
 */

public class GamesListAdapter extends ArrayAdapter<Games> {
        List<Games> mData;
        Context mContext;
        int mResource;

public GamesListAdapter(Context context, int resource, List<Games> objects) {
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

        TextView tv= (TextView) convertView.findViewById(R.id.gamedetails_textview);
        tv.setText(game.getTitle()+"    released in   :"+game.getReleaseDate()+"       platform   :"+game.getPlatform());

        return convertView;

        }
        }
