package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONException;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnApiCallCompleteListener{
    private ImageView mImageView;
    ParseJSON jsonData = new ParseJSON();
    YouTubeData youTubeData = new YouTubeData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new GetYouTube(MainActivity.this, youTubeData, MainActivity.this).execute();
            }
        });
    }

    @Override
    public void onApiCallComplete(String response) {
        mImageView = (ImageView) findViewById(R.id.imageView);
        String jsonString = youTubeData.getJsonContent();
        System.out.println("jsonString" + jsonString);
        try {
//                        mImageView.setImageBitmap(BitmapFactory.decodeFile(jsonData.getImageURL(jsonString)));
            Picasso.get().load(jsonData.getImageURL(jsonString)).into(mImageView);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}