import base64
import cv2
import numpy as np
import zbar

scale = 260
scanner = zbar.Scanner()

def scan(img):
    img = np.bitwise_xor(img, ~img[0,0])    
    img = cv2.resize(img, (scale * 2, scale * 2), interpolation=cv2.INTER_AREA)    
    ret = scanner.scan(img)    
    return ret[0].data if ret else b''




img = cv2.imread('inception.png', cv2.IMREAD_GRAYSCALE)

w, h = img.shape # 7540x7540


msg = b''.join([scan(img[y:y+scale, x:x+scale]) for x in range(0, w, scale) for y in range(0, h, scale)])


with open('msg', 'wb') as f:
    f.write(msg)


data = base64.b64decode(msg.split()[-1])
img = cv2.imdecode(np.asarray(bytearray(data), dtype=np.uint8), cv2.IMREAD_GRAYSCALE)
print(scan(img))