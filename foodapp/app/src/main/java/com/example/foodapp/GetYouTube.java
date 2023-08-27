package com.example.foodapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetYouTube extends AsyncTask<Void, Void, String> {

    private Context context;

    public GetYouTube(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String responseString = "";

        String purduePAL3 = "100.69.241.65"; // IPv4 address
        String port = "5000";

        try {
            URL url = new URL("http://" + purduePAL3 + ":" + port + "/bro");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        responseString += line;
                    }
                }
            }
            else {
                responseString = "HTTP Response Code: " + responseCode;
            }

        }
        catch (Exception e) {
            Log.e("API Call", "Error: " + e.getMessage());
        }

        return responseString;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Toast.makeText(context, "Good job " + result, Toast.LENGTH_SHORT).show();
    }
}
