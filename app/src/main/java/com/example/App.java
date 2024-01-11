package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import java.util.Random;
import javafx.application.Platform;


public class App extends Application {

    SerialMonitor serial;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Detección de movimiento");

        HBox root =new HBox(10);
        Label anguloX = new Label("0");
        Label anguloY = new Label("0");
        root.getChildren().addAll(anguloX,anguloY);
        
        stage.setScene(new Scene(root, 300, 100));

        // separate non-FX thread
        new Thread() {
            // Can I kill the thread?
            public void run() {
                for (;;){
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    final int randValue1 = new Random().nextInt(90);
                    final int randValue2 = new Random().nextInt(90);
        
                    Platform.runLater(new Runnable() {
                            public void run() {
                                anguloX.setText(Integer.toString(randValue1));
                                anguloY.setText(Integer.toString(randValue2));
                            }
                        });
                }
            }
        }.start();        

        stage.show();
    }


    public static void main(String[] args) {
        //serial = new SerialMonitor();
        //serial.init();
        launch();
    }
}
