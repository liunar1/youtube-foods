package com.example.foodapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ParseJSON {
    public JSONObject readJSONString(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(jsonString);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String readJSONFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        return content.toString();
    }

    public void editJSONFile(Context context, JSONObject jsonObject, String file) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();
    }

    public String getImageURL(String jsonString) throws IOException, JSONException {
        JSONObject jsonObject = readJSONString(jsonString);
        JSONObject videoSnippet;
        try {
            videoSnippet = jsonObject.getJSONObject("snippet");
        } catch (JSONException e) {
            JSONArray items = jsonObject.getJSONArray("items");
            JSONObject firstItem = (JSONObject) items.get(0);
            videoSnippet = firstItem.getJSONObject("snippet");
        }
        JSONObject videoThumbnail = videoSnippet.getJSONObject("thumbnails");
        JSONObject imageHD = videoThumbnail.getJSONObject("high");
        String imageURL = imageHD.getString("url");
        return imageURL;
    }
}
