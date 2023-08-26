import google_auth_oauthlib.flow
from googleapiclient.discovery import build
import os
from PIL import Image
from io import BytesIO
import requests
import json


# Define the OAuth 2.0 scopes required
os.environ["OAUTHLIB_INSECURE_TRANSPORT"] = "1"
scopes = ['https://www.googleapis.com/auth/youtube.readonly']
client_secrets_file = "client_secret.json"

# Create a flow instance to manage the OAuth 2.0 flow
flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(
        client_secrets_file, scopes)
# credentials = flow.run_console()
credentials = flow.run_local_server()

# Build the YouTube Data API service using OAuth 2.0 credentials
youtube = build('youtube', 'v3', credentials=credentials)

# Get liked videos
response = youtube.videos().list(
    part='snippet, contentDetails',
    myRating='like',
    maxResults=50  # Adjust as needed, the API returns results in batches
).execute()

liked_videos = []

# next_page_token = None
# while True:
response = youtube.videos().list(
    part='snippet',
    myRating='like',
    maxResults=1,
    # pageToken=next_page_token
).execute()

liked_videos.extend(response.get('items', []))
    # next_page_token = response.get('nextPageToken')
    
    # if not next_page_token:
    #     break

# Print the list of liked videos
for video in liked_videos:
    video_url = f"https://www.youtube.com/watch?v={video['id']}"
    url = video['snippet']['thumbnails']['high']['url']
    response = requests.get(url)
    thumbnail_image = Image.open(BytesIO(response.content))

    thumbnail_image.show()
    with open("testVideo.json", "w") as jsonOutput:
        json.dump(video, jsonOutput, indent=4)

    print(video_url)
    print(video['snippet']['title'])







