package com.example.thegamesdb2;

/**
 * Created by Sandeep on 2/20/2017.
 */

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sandeep on 2/17/2017.
 */

public class GamesUtil {

    static ArrayList<Games> parseGames(InputStream in) throws XmlPullParserException, IOException, ParseException {
        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(in,"UTF-8");
        Games game=null;
        ArrayList<Games> gamesArrayList= new ArrayList<>();
        int event=parser.getEventType();
        Date d= null;

        while(event != XmlPullParser.END_DOCUMENT)
        {

            switch (event)
            {
                case  XmlPullParser.START_TAG:
                    if(parser.getName().equals("Game")) {
                        game = new Games();
                    }
                    if(game!=null) {
                        if (parser.getName().equals("id")) {
                            game.setId(parser.nextText().trim());
                        }
                    }
                    if(game!=null) {
                        if (parser.getName().equals("GameTitle")) {
                            game.setTitle(parser.nextText().trim());
                        }
                    }
                    if(game!=null) {
                        if (parser.getName().equals("ReleaseDate")) {
                            SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");

                            try {
                                d = format.parse(parser.nextText().trim());
                                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                                String newDate = sdf.format(d);
                                game.setReleaseDate(newDate);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if(game!=null)
                    {
                        if (parser.getName().equals("Platform"))
                            game.setPlatform(parser.nextText().trim());

                    }
                    break;
                case XmlPullParser.END_TAG:
                    if(parser.getName().equals("Game"))
                        gamesArrayList.add(game);
                default:
                    break;
            }
            event= parser.next();
        }
        Log.d("demo",""+gamesArrayList.size());

        return  gamesArrayList;

    }
}
