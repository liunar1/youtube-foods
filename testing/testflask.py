from flask import Flask

app = Flask(__name__)

@app.route('/')
def home():
    return "Hello world!"

@app.route('/bro')
def bro():
    return "tom is cool"

if __name__ == '__main__':
    app.run(host = '100.69.241.65', port = 5000, debug=True)