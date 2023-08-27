# import os
import openai
from flask import Flask, request, jsonify
import youtube

# openai.api_key = "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"
# openai.FineTuningJob.create(training_file="", model="gpt-3.5-turbo")

# youtubeapi_key = "AIzaSyCRwo-aM1cGNtjO-x9yJHqt_rCYE3TnqWs"

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