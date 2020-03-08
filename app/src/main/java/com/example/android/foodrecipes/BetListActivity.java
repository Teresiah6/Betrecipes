package com.example.android.foodrecipes;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.foodrecipes.Util.Constants;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BetListActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ProgressBar mLoadingProgress;
    private RecyclerView betsRv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bet_list);
        mLoadingProgress = (ProgressBar) findViewById(R.id.progressBarLoading);
        betsRv = (RecyclerView) findViewById(R.id.betsRV);
        LinearLayoutManager betsLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.VERTICAL, false);
        betsRv.setLayoutManager(betsLayoutManager);

        try {
            //arsenal is what we want to find
            URL betUrl = Constants.buildUrl("arsenal");

            String jsonResult = Constants.getJson(betUrl);
            //inorder to call the async task we need to instantiate the class and call execute method
            new BetsQueryTask().execute(betUrl);


        } catch (Exception e) {
            Log.d("error", e.getMessage());

        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bet_list_menu, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        return true;



    }

    @Override
    public boolean onQueryTextSubmit(String query) {
     try {
         URL betUrl = Constants.buildUrl(query);
         //execute the search of this URL
         new BetsQueryTask().execute(betUrl);

     }
     catch (Exception e){
         Log.d("Error", e.getMessage());


        }
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    // for doing in background tasks
    public class BetsQueryTask extends AsyncTask<URL, Void, String> {

        @Override
        protected String doInBackground(URL... urls) {
            URL searchURL = urls[0]; // first member of the array of urls
            //string containing result
            String result = null; //at the beginning it will be null
            try {
                result = Constants.getJson(searchURL);
            } catch (IOException e) {
                Log.e("Error", e.getMessage());

            }
            return result;

        }

        @Override
        protected void onPostExecute(String result) {
            // write results in our textview
           // TextView resultTv = (TextView) findViewById(R.id.response_tv);
            TextView textViewError = (TextView) findViewById(R.id.textViewError);
            mLoadingProgress.setVisibility(View.INVISIBLE);
            if (result == null) {
                betsRv.setVisibility(View.INVISIBLE);
                textViewError.setVisibility(View.VISIBLE);
            } else {
                betsRv.setVisibility(View.VISIBLE);
                textViewError.setVisibility(View.INVISIBLE);
            }

            ArrayList<Bet> bets = Constants.getBetsfromJson(result);
            //create an empty string as a container for the  results
            String resultString ="";
//            for (Bet bet: bets){
//                resultString = resultString + bet.key + "\n" +
//                        bet.group + "\n" + bet.group + "\n" + "\n" + bet.title + "\n\n";
//            }


            BetsAdapter adapter = new BetsAdapter(bets);
            betsRv.setAdapter(adapter);

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingProgress.setVisibility(View.VISIBLE);
        }
    }


}