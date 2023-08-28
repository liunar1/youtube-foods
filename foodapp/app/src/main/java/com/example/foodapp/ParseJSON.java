package com.example.foodapp;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ParseJSON {
    public JSONObject readJSONString(Context context, String jsonString) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(jsonString);
            // Now you can work with the jsonObject
            String value1 = jsonObject.getString("key1");
            String value2 = jsonObject.getString("key2");

            System.out.println("Value 1: " + value1);
            System.out.println("Value 2: " + value2);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public String readJSONFile(Context context, String filePath) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }

        return content.toString();
    }

    public void editJSON(Context context, JSONObject jsonObject, String file) throws IOException {
        FileOutputStream fileOutputStream = context.openFileOutput(file, Context.MODE_PRIVATE);
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
        outputStreamWriter.write(jsonObject.toString());
        outputStreamWriter.close();
    }

//    public void appendToJsonFile(Context context, JSONObject newData, String fileName) throws IOException, JSONException {
//        JSONObject existingData = readJSON(context, fileName);
//        mergeJSON(existingData, newData);
//        editJSON(context, existingData, fileName);
//    }

//    private static void mergeJSON(JSONObject target, JSONObject source) throws JSONException {
//        for (String key : source.keySet()) {
//            target.put(key, source.get(key));
//        }
//    }
}
