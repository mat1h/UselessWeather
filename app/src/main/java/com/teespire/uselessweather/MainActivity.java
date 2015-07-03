package com.teespire.uselessweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;

import com.teespire.elements.SearchElement;


public class MainActivity extends Activity {

    private String localWeatherBaseURL = "http://api.worldweatheronline.com/free/v2/weather.ashx?q=-14.9333%2C-74.6667&format=json&num_of_days=5&key=e0714ce00363fc7935e7921fc378a";

    private boolean isWwoSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RadioButton radioButtonWorlWeatherOnline
                = (RadioButton)findViewById(R.id.radioButtonWorldWeather);

        final RadioButton radioButtonYahooWeather
                = (RadioButton) findViewById(R.id.radioButtonYahooWeather);

        radioButtonWorlWeatherOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "R1 Clicked!");
                radioButtonYahooWeather.setChecked(false);
                isWwoSelected = true;
            }
        });

        radioButtonYahooWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("MainActivity", "R2 clicked");
                radioButtonWorlWeatherOnline.setChecked(false);
                isWwoSelected = false;
            }
        });
    }

    public void tap_search(View sender){
        Log.d("MainActivity", "Search button has been tapped!");
        //Button senderButton = (Button) sender;
        //senderButton.setText("Search Done");

        Intent searchActivityIntent = new Intent(this, SearchActivity.class);
        searchActivityIntent.putExtra("isWwoSelected", isWwoSelected);
        startActivityForResult(searchActivityIntent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            if(requestCode == 100){
                SearchElement searchElement = new SearchElement();
                searchElement.setCityName(data.getStringExtra("cityName"));
                searchElement.setLatitude(data.getStringExtra("latitude"));
                searchElement.setLongitude(data.getStringExtra("longitude"));

                Log.d("MainActivity", "Activity Result Item=" + searchElement.getCityName()
                        + ", Latitude=" + searchElement.getLatitude());
            }
        }
    }
}
