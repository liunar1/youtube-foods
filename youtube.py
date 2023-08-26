import requests

def fetchPlaylists(url: str):
    requests.get(url, params={"part":"snippet,contentDetails"})