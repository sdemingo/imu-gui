package com.example;

import java.nio.charset.StandardCharsets;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class SerialMonitor{

    
    public static void init(){
        new Thread(() -> {
                SerialMonitor.read();
        }).start();
    }

    private static void read(){
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
                        //System.out.println("Read " + numRead + " bytes.");
                        String readString = new String(readBuffer, StandardCharsets.UTF_8);
                        System.out.println(readString);
                    }
            } catch (Exception e) { 
                //e.printStackTrace(); 
                System.out.println("Error: leyendo el puerto serie");
            }
            comPort.closePort();
        }else{
            System.out.println("No hay dispositivo conectado al puerto serie");
        }
    }


}
