import google_auth_oauthlib.flow
from googleapiclient.discovery import build
import os
from PIL import Image
from io import BytesIO
import requests
import json
import gpt


os.environ["OAUTHLIB_INSECURE_TRANSPORT"] = "1"
scopes = ['https://www.googleapis.com/auth/youtube.readonly']
client_secrets_file = "client_secret.json"

flow = google_auth_oauthlib.flow.InstalledAppFlow.from_client_secrets_file(
    client_secrets_file, scopes)
credentials = flow.run_local_server()

youtube = build('youtube', 'v3', credentials=credentials)

def get_food():
    pass

def get_liked_video():

    liked_videos = []

    next_page_token = None
    # while True:
    response = youtube.videos().list(
        part='snippet',
        myRating='like',
        maxResults=1,
        pageToken=next_page_token
    ).execute()
    # print(response)
    # print(type(response))
    video_title = response["items"][0]["snippet"]["title"]
    print(video_title)
    if "True" in gpt.is_food(video_title):
        liked_videos.extend(response.get('items', []))
        print("that was a food item")
    # ['snippet']['title']
        # next_page_token = response.get('nextPageToken')
    
        # if not next_page_token:
        #     break

    # Print the list of liked videos
    # for video in liked_videos:
        # video_url = f"https://www.youtube.com/watch?v={video['id']}"
        # url = video['snippet']['thumbnails']['high']['url']
        # response = requests.get(url)
        # thumbnail_image = Image.open(BytesIO(response.content))

        # thumbnail_image.show()
        # if (os.path.isfile("file")):
        #     with open("testVideo.json", "w") as clearFile:
        #         pass
        # with open("testVideo.json", "a") as jsonOutput:
        #     json.dump(video, jsonOutput, indent=4)

        # print(video_url)
        # print(video['snippet']['title'])
    print(liked_videos)
    return liked_videos







