package com.teespire.uselessweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.teespire.elements.SearchElement;
import com.teespire.elements.WebExtractorDelegate;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


public class SearchActivity extends Activity implements WebExtractorDelegate {

    private SearchListAdapter searchListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        Bundle intentBundle = getIntent().getExtras();
        boolean isWwoSelected = intentBundle.getBoolean("isWwoSelected", true);
        Log.d("SearchActivity", "isWwoSelected=" + isWwoSelected);

        searchListAdapter = new SearchListAdapter();
        searchListAdapter.setSearchElementArrayList(new ArrayList<SearchElement>());

        ListView listViewSearchResults = (ListView)findViewById(R.id.listViewSearchResults);
        listViewSearchResults.setAdapter(searchListAdapter);

        listViewSearchResults.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SearchElement searchElement = ((SearchListAdapter)adapterView.getAdapter())
                        .getSearchElementArrayList().get(i);
                Log.d("SearchActivity", "Item Clicked=" + searchElement.getCityName()
                + ", Latitude=" + searchElement.getLatitude());

                Intent resultIntent = new Intent();
                resultIntent.putExtra("cityName", searchElement.getCityName());
                resultIntent.putExtra("latitude", searchElement.getLatitude());
                resultIntent.putExtra("longitude", searchElement.getLongitude());

                setResult(RESULT_OK, resultIntent);
                finish();

            }
        });

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
                webExtractor.execute("http://api.worldweatheronline.com/free/v2/search.ashx?query="+s+"&num_of_results=10&format=json&key=e0714ce00363fc7935e7921fc378a");

                return false;
            }
        });


    }

    @Override
    public void WebExtractor(WebExtractor webExtractor, String jsonString) {
        //Log.d("SearchActivity", "" + jsonString);

        try{
            JSONObject jsonObjectResponse = new JSONObject(jsonString);

            JSONObject jsonObject = jsonObjectResponse.getJSONObject("search_api");
            /*String value = jsonObject
                    .getJSONArray("result")
                    .getJSONObject(0)
                    .getJSONArray("areaName")
                    .getJSONObject(0)
                    .getString("value");*/
            JSONArray jsonArrayResult = jsonObject.getJSONArray("result");

            ArrayList<SearchElement> elementArrayList = new ArrayList<>();

            for(int i=0; i < jsonArrayResult.length(); i++){
                JSONObject tempJsonObject = jsonArrayResult.getJSONObject(i);
                SearchElement newSearchElement = new SearchElement();
                newSearchElement.setCityName(tempJsonObject
                        .getJSONArray("areaName")
                        .getJSONObject(0)
                        .getString("value"));

                newSearchElement.setLatitude(tempJsonObject.getString("latitude"));
                newSearchElement.setLongitude(tempJsonObject.getString("longitude"));

                elementArrayList.add(newSearchElement);
            }

            Log.d("SearchActivity", "ArrayListSize=" + elementArrayList.size());
            //Log.d("SearchActivity",value);

            searchListAdapter.setSearchElementArrayList(elementArrayList);
            searchListAdapter.notifyDataSetChanged();

        }
        catch (Exception e){

        }


    }
}
