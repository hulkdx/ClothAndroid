#!/usr/local/bin/python

# --------------------------------
# Imports
# --------------------------------

import requests
import re

# --------------------------------
# Constants
# --------------------------------

url = 'https://www.instagram.com/naku_clothing/'

# --------------------------------
# Main
# --------------------------------

def main():
  response = requests.get(url).text
  images = re.findall(r"https:\/\/[^.]+.cdninstagram.com\/[^\"]+", response)
  index = -1

  json = """
{
  "items": [
  """
  for img in images:
    index += 1
    json += """
    {
        "id": %d,
        "image": {
            "width": 500,
            "height": 500,
            "size": 1024,
            "url": "%s"
        },
        "price": 400,
        "currency": "Euro",
        "user": {
            "id": 1,
            "username": "hulk",
            "first_name": "Saba",
            "last_name": "Jafarzadeh",
            "email_address": "sabajafarzadeh@gmail.com",
            "image": {
                "width": 500,
                "height": 500,
                "size": 1024,
                "url": "https://images.pexels.com/photos/67636/rose-blue-flower-rose-blooms-67636.jpeg?cs=srgb&dl=beauty-bloom-blue-67636.jpg&fm=jpg"
            }
        }
    }
    """ % (index, img)
    print json
  json += """
  ],
  "updated_at": "2019-06-13 08:32:20"
}
  """
  print json

if __name__ == "__main__":
    main()



