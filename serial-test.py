
import serial
import time

arduino = serial.Serial(port="/dev/ttyACM0",   baudrate=9600, timeout=.1)

while True:
    time.sleep(0.1)
    data = arduino.readline()
    try:
        numbers=data.decode('ascii').split(",")
        x=int(numbers[1])
        y=int(numbers[0])
        print("X:"+str(x)+" Y:"+str(y))
    except:
        continue
