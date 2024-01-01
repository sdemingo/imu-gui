package com.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primera) throws Exception {
        primera.setTitle("Plantilla de app para JavaFX");
        primera.show();
    }


    public static void main(String[] args) {
        SerialMonitor.init();
        launch();
    }
}
