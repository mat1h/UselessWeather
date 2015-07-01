package com.teespire.uselessweather;

import android.os.AsyncTask;
import android.util.Log;

import com.teespire.elements.WebExtractorDelegate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Sohaib Basit on 01/07/15.
 */
public class WebExtractor extends AsyncTask<String, Void, String> {
    public void setDelegate(WebExtractorDelegate delegate) {
        this.delegate = delegate;
    }

    private WebExtractorDelegate delegate;

    @Override
    protected String doInBackground(String... params) {
        InputStream inputStream = null;
        try{
            String url = params[0];
            URL connectionUrl = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) connectionUrl.openConnection();
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);

            httpURLConnection.connect();

            int response = httpURLConnection.getResponseCode();
            Log.d("WebExtractor", "responseCode=" + response);
            inputStream = httpURLConnection.getInputStream();

            String responseString = convertStreamToString(inputStream);

            return responseString;
        }
        catch (Exception e){

        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("WebExtractor", "responseJson=" + s);

        if(delegate != null){
            delegate.WebExtractor(this, s);
        }

        super.onPostExecute(s);
    }

    private String convertStreamToString(InputStream is)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
