from io import BytesIO
from PIL import Image
import json

import requests

with open("testVideo.json", "r") as jsonInput:
    videoJson = json.load(jsonInput)
    url = videoJson["snippet"]["thumbnails"]["standard"]["url"]
    response = requests.get(url)
    thumbnail_image = Image.open(BytesIO(response.content))
    thumbnail_image.show()