package com.example;

import java.nio.charset.StandardCharsets;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class SerialMonitor{

    private String lastCommand;
    private String lastError;


    public SerialMonitor(){
        lastCommand="";
        lastError="";
    }


    public String getLastCommand(){
        return lastCommand;
    }

    public String getLastError(){
        return lastError;
    }


    public void init(){
        new Thread(() -> {
                read();
        }).start();
    }
   

    private void read(){
        if (SerialPort.getCommPorts().length!=0){    
            SerialPort comPort = SerialPort.getCommPorts()[0];
            comPort.openPort();
        
            try {
                while (true)
                    {
                        while (comPort.bytesAvailable() == 0)
                            Thread.sleep(100);
                        byte[] readBuffer = new byte[comPort.bytesAvailable()];
                        int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                        String readString = new String(readBuffer, StandardCharsets.UTF_8);
                        lastCommand=readString;
                    }
            } catch (Exception e) { 
                lastError="Error: Lectura errónea del puerto serie";
                System.out.println(lastError);
            }
            comPort.closePort();
        }else{
            lastError="Error: No hay dispositivo conectado al puerto serie";
            System.out.println(lastError);
        }
    }

}
