package com.example.android.foodrecipes.Util;

import android.net.Uri;
import android.util.Log;

import com.example.android.foodrecipes.Bet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Constants {
    private Constants() {
    }



    // contains part of the URL that is not going to change
    public static final String BASE_URL = "https://api.the-odds-api.com/v3/sports";

    public static final String QUERY_PARAMETER_KEY = "apiKey";

//    public static final String KEY = "key";
    public static  final String SPORT = "soccer";
    public static final String REGION = "uk";

    public static final String API_KEY = "b06668474acf7d52f3d7bd3046b82b7f";

    //add query for the title
   public static URL buildUrl(String title) {
        //String fullUrl = BASE_URL + "?q =" + title;
        //best practices is to use a URI builder not a string

        URL url = null;
        //uri.parse converts a string to a uri
        //build upon constructs a new builder
        //build method creates the URI
        Uri uri = Uri.parse(BASE_URL).buildUpon()
                //.appendQueryParameter(QUERY_PARAMETER_KEY,title)
                .appendQueryParameter(QUERY_PARAMETER_KEY, API_KEY)
                .appendQueryParameter(SPORT, REGION)
                .build();
        try {
            //   url = new URL(fullUrl);
            //convert the Uri to Url
            url = new URL(uri.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return url;

    }

    // method to connect to the api
    public static String getJson(URL url) throws IOException {
        // establish a connection to the URL that was passed
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        //inputstream allows us to read any data

        try {
            InputStream stream = connection.getInputStream();
            //use scanner to convert String into a stream
            Scanner scanner = new Scanner(stream);
            // \\A means we want to read everything and is a pattern / irregular expression
            scanner.useDelimiter("\\A");
            // lets see if we have data by calling hasNext on scanner true if it has data
            boolean hasData = scanner.hasNext();
            if (hasData) {
                return scanner.next();

            } else {
                return null;
            }

        } catch (Exception e) {
            Log.d("Error", e.toString());
            return null;
        }
//finally closes the connection
        finally {
            connection.disconnect();
        }

    }
    public static ArrayList<Bet> getBetsfromJson(String json){
        final String ID ="id";
        final String KEY = "key";
        //final boolean ACTIVE = true;
        final String GROUP ="group";
         final String DETAIL ="detail";
         final String TITLE = "title";
         // constant for the items items is the name of the array of bets
        final String ITEMS = "items";

        ArrayList<Bet> bets = new ArrayList<Bet>();
        try{
            JSONObject jsonBets = new JSONObject(json);
            JSONArray arrayBets = jsonBets.getJSONArray(ITEMS);
            //find number of bets sent
            int numberOfBets = arrayBets.length();
            //loop through the JSONArray
            for (int i=0; i<numberOfBets; i++) {
                //great a single JSON object
                JSONObject betJSON = arrayBets.getJSONObject(i);
                Bet bet = new Bet (betJSON.getString(ID),betJSON.getString(KEY),
                        betJSON.getString(GROUP),
                        betJSON.getString(TITLE)) ;
                bets.add(bet);


            }
        }
        catch (JSONException e){
            e.printStackTrace();

        }
        return bets;
    }


}
