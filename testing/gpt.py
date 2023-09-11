import requests

api_key = "open-ai-key"

api_endpoint = "https://api.openai.com/v1/chat/completions"

messages = [
    # {"role": "user", "content": "[Best Banana Ever, Millefeuille, Marzipan, Peking Duck, Souffle, Cake, Austria, Germany, Hungary, Awesome Pasta Dish, My Favorite Adventure in California]"},
    # {"role": "assistant", "content": "Return me a new list of foods that are Chinese cuisine from that list."},
    # {"role": "assistant", "content": "Return me a new list of words that are food items from that list."} 
    {"role": "user", "content": "How to get a girl to like you back"},
    {"role": "assistant", "content": "Given the title above, return True or False if the title involves food"}
]

response = requests.post(
    api_endpoint,
    headers={
        "Authorization": f"Bearer {api_key}",
    },
    json={
        "messages": messages,
        "model": "gpt-3.5-turbo-0613",  
        "max_tokens": 100,  
    }
)

if response.status_code == 200:
    result = response.json()
    print(result)
    generated_text = result["choices"][0]["message"]["content"]
    print(generated_text)
else:
    print("API request failed with status code:", response.status_code)
    print(response.text)
