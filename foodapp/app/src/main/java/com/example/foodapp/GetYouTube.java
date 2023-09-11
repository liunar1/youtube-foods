package com.example.foodapp;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GetYouTube {
    private Context context;
    private YouTubeData youTubeData;
    private OnApiCallCompleteListener callback;

    public GetYouTube(Context context, YouTubeData youTubeData, OnApiCallCompleteListener callback) {
        this.context = context;
        this.youTubeData = youTubeData;
        this.callback = callback;
    }

    public void execute() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executorService.execute(new Runnable() {
            @Override
            public void run() {
                String responseString = "";

                String purduePAL3 = "100.69.243.39"; // IPv4 address for dorm
//                purduePAL3 = "10.186.155.209";
                purduePAL3 = "100.69.247.38";
                String port = "5000";

                try {
                    URL url = new URL("http://" + purduePAL3 + ":" + port + "/fetchfood");
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
                    } else {
                        responseString = "HTTP Response Code: " + responseCode;
                    }

                } catch (Exception e) {
                    Log.e("API Call", "Error: " + e.getMessage());
                }

                final String finalResponse = responseString;

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(context, "Good job " + finalResponse, Toast.LENGTH_SHORT).show();
                        youTubeData.setJsonContent(finalResponse);
                        callback.onApiCallComplete("final response " + finalResponse);
//                        System.out.println("final response " + finalResponse);
                    }
                });
            }
        });

        executorService.shutdown();
    }
}
