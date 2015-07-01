package com.teespire.uselessweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;


public class MainActivity extends Activity {

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
        startActivity(searchActivityIntent);
    }

}
