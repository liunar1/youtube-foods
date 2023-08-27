# import os
import openai
from flask import Flask, request, jsonify
import youtube
import json

openai.api_key = ""
with open("secrets.json", "r") as jsonFile:
    jsonData = json.loads(jsonFile)
    openai.api_key = jsonData["gpt_key"]
    youtubeapi_key = jsonData["youtube_key"]
openai.FineTuningJob.create(training_file="", model="gpt-3.5-turbo")

app = Flask(__name__)

# categories = ["Cuisine", "YouTuber", "Food", "Festive"] 

# @app.route('/fetchfood', methods=['READ'])
# def fetchFood():
#     print("Hello World!")
#     youtube.fetchPlaylists("https://www.googleapis.com/youtube/v3/playlistItems")

@app.route('/')
def home():
    return "Hello world!"

@app.route('/bro')
def bro():
    return "every day bro!"

if __name__ == '__main__':
    app.run(debug=True)