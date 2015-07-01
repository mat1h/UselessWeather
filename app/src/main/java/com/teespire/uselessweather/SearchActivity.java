package com.teespire.uselessweather;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import com.teespire.elements.WebExtractorDelegate;


public class SearchActivity extends Activity implements WebExtractorDelegate {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle intentBundle = getIntent().getExtras();
        boolean isWwoSelected = intentBundle.getBoolean("isWwoSelected", true);
        Log.d("SearchActivity", "isWwoSelected=" + isWwoSelected);

        final SearchActivity thisActivity = this;

        SearchView searchViewCity = (SearchView)findViewById(R.id.searchViewCity);
        searchViewCity.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d("OnQuerryTextSubmit",s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d("OnQuerryTextChange",s);

                WebExtractor webExtractor = new WebExtractor();
                webExtractor.setDelegate(thisActivity);
                webExtractor.execute("http://api.worldweatheronline.com/free/v2/search.ashx?query=London&num_of_results=3&format=json&key=e0208883c64ca68042427a61c822f");

                return false;
            }
        });


    }

    @Override
    public void WebExtractor(WebExtractor webExtractor, String jsonString) {
        Log.d("SearchActivity", jsonString);
    }
}
