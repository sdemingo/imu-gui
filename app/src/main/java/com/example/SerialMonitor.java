package com.example;

import java.nio.charset.StandardCharsets;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;


public class SerialMonitor{

    private CommandBuffer buffer;


    public SerialMonitor(){
        buffer = new CommandBuffer();
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
                            Thread.sleep(10);
                        byte[] readBuffer = new byte[comPort.bytesAvailable()];
                        int numRead = comPort.readBytes(readBuffer, readBuffer.length);
                        String readString = new String(readBuffer, StandardCharsets.UTF_8);
                        String[] fields = readString.split(",");
                        if (fields.length == 2){
                            Command command = new Command(0,0);
                            try{
                                Integer angleX = Integer.parseInt(fields[0]);
                                //System.out.println(angleX);
                                command.setAngleX(angleX);
                            }catch(Exception e){}
                            try{
                                Integer angleY = Integer.parseInt(fields[1]);
                                //System.out.println(angleY);
                                command.setAngleY(angleY);
                            }catch(Exception e){}
                        }
                           
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
