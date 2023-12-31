from flask import Flask, request, jsonify
import youtube
import json

app = Flask(__name__)

# categories = ["Cuisine", "YouTuber", "Course", "Type", "Festive"] 

@app.route('/fetchfood')
def fetchAllFood():
    print("Hello World!")
    youtube.get_liked_video()
    jsonString = ""
    with open("food.json", "r") as jsonFile:
        jsonString = str(json.load(jsonFile))
        print(jsonString)
    return jsonString

@app.route('/categorize')
def categorize():
    print("Categorizing")

if __name__ == '__main__':
    # ipv4 = '10.186.155.209' # 20875 room but 100.69.241.65 is in my dorm
    # ipv4 = '10.186.38.74'
    ipv4 = '100.69.247.38'

    app.run(host=ipv4, port=5000, debug=True)