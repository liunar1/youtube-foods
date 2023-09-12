## Overview

Android app makes API call to Python Flask API whose specific endpoints make specific API calls to OpenAI API and YouTube API.
YouTube API to fetch YouTube data and OpenAI API to classify and sort data into specific categories.
Android app is all about presenting the data for the user. 

## JSON Data 

Data is stored in Firebase to prevent excessive Flask API calls and costly OpenAI GPT-3.5 usage

## Note 

Note that for security reasons, secrets are not included in this repository. The necessary keys are the OpenAI API key, and the 
YouTube API key as well as other credentials.