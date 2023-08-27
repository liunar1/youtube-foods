package com.example.foodapp;

import android.content.Context;

import org.json.JSONException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ParseJSON {
    public JSONObject readJSON(Context context, String file) throws FileNotFoundException {
        try {
            Object object = new JSONParser().parse(new FileReader(file));

            if (object instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) object;
            } else if (object instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) object;
            } else {
                System.out.println("Invalid JSON data");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
