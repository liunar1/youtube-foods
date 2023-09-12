package com.example.foodapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements OnApiCallCompleteListener{
    private ImageView mImageView;
    private int videoCounter = 0;
    private ParseJSON jsonData = new ParseJSON();
    private YouTubeData youTubeData = new YouTubeData();
    private String jsonString = null;

    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button myButton = findViewById(R.id.myButton);
        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.child("videos").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            mImageView = (ImageView) findViewById(R.id.imageView);
                            jsonString = snapshot.getValue().toString();
                            youTubeData.setJsonContent(jsonString);
                            try {
                                Picasso.get().load(jsonData.getImageURL(jsonString, videoCounter)).into(mImageView);
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            new GetYouTube(MainActivity.this, youTubeData, MainActivity.this).execute();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        throw error.toException();
                    }
                });
            }
        });

        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JSONObject jsonObject = jsonData.readJSONString(jsonString);
                JSONArray videoList = null;
                try {
                    videoList = jsonObject.getJSONArray("videos");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (videoList != null && videoCounter < videoList.length() - 1) {
                    videoCounter += 1;
                    mImageView = (ImageView) findViewById(R.id.imageView);
                    jsonString = youTubeData.getJsonContent();
//                    System.out.println("jsonString" + jsonString);
                    try {
                        Picasso.get().load(jsonData.getImageURL(jsonString, videoCounter)).into(mImageView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Button prevButton = findViewById(R.id.prevButton);
        prevButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (jsonString != null && videoCounter > 0) {
                    videoCounter -= 1;
                    mImageView = (ImageView) findViewById(R.id.imageView);
                    jsonString = youTubeData.getJsonContent();
//                    System.out.println("jsonString" + jsonString);
                    try {
                        Picasso.get().load(jsonData.getImageURL(jsonString, videoCounter)).into(mImageView);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    @Override
    public void onApiCallComplete(String response) {
        mImageView = (ImageView) findViewById(R.id.imageView);
        jsonString = youTubeData.getJsonContent();
        System.out.println("jsonString" + jsonString);
        databaseReference.child("videos").setValue(jsonString);
        try {
            Picasso.get().load(jsonData.getImageURL(jsonString, 0)).into(mImageView);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}