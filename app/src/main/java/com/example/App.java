package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;

public class App extends Application {

    SerialMonitor serial;

    @Override
    public void start(Stage primera) throws Exception {
        primera.setTitle("Detección de movimiento");

        HBox root =new HBox();
        Label anguloX = new Label("0");
        Label anguloY = new Label("0");
        root.getChildren().addAll(anguloX,anguloY);


        Scene escena = new Scene(root,500,300);
        primera.setScene(escena);

        primera.show();
    }


    /*
      Tengo que meter en el stage dos labels e ir actualizandolas con los 
      valores que están en el buffer de serialmonitor

      https://stackoverflow.com/questions/29358075/running-a-thread-in-javafx
     */


    public static void main(String[] args) {
        serial = new SerialMonitor();
        serial.init();
        launch();
    }
}
