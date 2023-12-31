
import serial
import time

arduino = serial.Serial(port="/dev/ttyACM0",   baudrate=9600, timeout=.1)

while True:
    time.sleep(0.1)
    data = arduino.readline()
    try:
        numbers=data.decode('ascii').split(",")
        x=int(numbers[1])
        print(x)
    except:
        continue
