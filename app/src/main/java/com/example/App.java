package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.util.Random;
import javafx.application.Platform;


public class App extends Application {

    private static SerialMonitor serial;
    private int intValue;

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Detección de movimiento");

        HBox root =new HBox(10);
        Label anguloX = new Label("0");
        root.getChildren().addAll(anguloX);
        stage.setScene(new Scene(root, 300, 100));

        // separate non-FX thread
        new Thread() {
            public void run() {
                for (;;){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
        
                    Platform.runLater(new Runnable() {
                            public void run() {
                                String serialValue=serial.getLastCommand();
                                try{
                                    intValue=Integer.parseInt(serialValue.trim());
                                    //System.out.println(intValue);
                                }catch(Exception e){}
                                anguloX.setText(Integer.toString(intValue));
                            }
                        });
                }
            }
        }.start();        

        stage.show();
    }


    public static void main(String[] args) {
        serial = new SerialMonitor();
        serial.init();
        launch();
    }
}
