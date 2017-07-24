package com.example.thegamesdb2;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * Created by Sandeep on 2/20/2017.
 */
public class GamesListUtil {
    static ArrayList<GamesList> parseGames(InputStream in) throws XmlPullParserException, IOException, ParseException {
        XmlPullParser parser= XmlPullParserFactory.newInstance().newPullParser();
        parser.setInput(in,"UTF-8");
        GamesList game = new GamesList();
        ArrayList<GamesList> gamesArrayList= new ArrayList<>();
        int event=parser.getEventType();
        int count=0, counter=0,i=0;
        String[] id = new String[10];

        while(event != XmlPullParser.END_DOCUMENT)
        {
            if(parser.getName()!= null)
                switch (event)
                {
                    case  XmlPullParser.START_TAG:
                        if(parser.getName().equals("baseImgUrl")) {
                            game.setBaseUrl(parser.nextText().trim());
                        }
                        if (parser.getName().equals("GameTitle")) {
                            game.setTitle(parser.nextText().trim());
                        }
                        if (parser.getName().equals("Overview")) {
                            game.setOverview(parser.nextText().trim());
                        }
                        if (parser.getName().equals("genre"))
                        {
                            game.setGenre(parser.nextText().trim());
                        }
                        if (parser.getName().equals("Publisher"))
                        {
                            game.setPublisher(parser.nextText().trim());
                        }
                        if(parser.getName().equals("SimilarCount"))
                        {
                            counter= Integer.parseInt(parser.nextText().trim());
                            game.setCounter(counter);
                        }
                        if(parser.getName().equals("id") && i<=counter)
                        {
                            id[i]=parser.nextText().trim();
                            i++;
                        }
                        if(parser.getName().equals("Youtube"))
                        {
                            game.setYoutubeLink(parser.nextText().trim());
                        }
                        if ( count==0 && parser.getName().equals("original") || parser.getName().equals("boxart"))
                        {
                            game.setImageurl(parser.nextText().trim());
                            count=1;
                        }
                        if(parser.getName().equals("clearlogo"))
                        {
                            game.setLogo(parser.nextText().trim());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("Data")) {
                            game.setId(id);
                            gamesArrayList.add(game);
                        }
                    default:
                        break;
                }
            event= parser.next();
        }
        return  gamesArrayList;

    }
}
