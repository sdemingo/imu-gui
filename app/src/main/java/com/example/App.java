package com.example;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.*;
import javafx.scene.text.*;
import java.util.Random;
import javafx.application.Platform;
import java.util.ArrayList;
import javafx.geometry.Insets;


public class App extends Application {
    
    private static int ROW_PANELS = 5;

    private static SerialMonitor serial;
    private int intValue;
    private ArrayList<FlowPane> panels;
    private Label anguloX;


    private void createPanels(HBox fila){
        
        panels=new ArrayList<FlowPane>();

        for (int i=0; i<ROW_PANELS;i++){
            panels.add(i,new FlowPane());
            panels.get(i).setStyle("-fx-border-color: black;-fx-background-color: orange");
            panels.get(i).setPrefHeight(200);
            fila.getChildren().add(panels.get(i));
        }
    }


    private void selectPanel(int index){
        for (FlowPane panel: panels){
            panel.setStyle("-fx-border-color: black;-fx-background-color: orange");
        }

        if (index>=0){
            panels.get(index).setStyle("-fx-border-color: black; -fx-background-color: red");
        }
    }


    private void updateAngle(int value){
        anguloX.setText("Angulo: "+Integer.toString(intValue));                                
        if (value > 55 && value < 90) {
            selectPanel(0);
        }else if (value > 10 && value < 55) {
            selectPanel(1);
        }else if (value > -10 && value < 10) {
            selectPanel(2);
        }else if (value > -55 && value < -10) {
            selectPanel(3);
        }else if (value > -90 && value < -55) {
            selectPanel(4);
        }else{
            selectPanel(-1);
        }
    }


    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Detección de movimiento");

        
        VBox root =new VBox(10);

        HBox fila1 = new HBox(20);
        fila1.setPadding(new Insets(10));
        anguloX = new Label("Angulo: 0");
        anguloX.setFont(new Font("Arial", 24)); 
        Region spacer = new Region();
        spacer.setPrefWidth(200);
        Label error = new Label("");
        fila1.getChildren().addAll(anguloX,spacer, error);

        HBox fila2 = new HBox();
    
        createPanels(fila2);

        root.getChildren().addAll(fila1, fila2);
        stage.setScene(new Scene(root, 800, 300));

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
                                }catch(Exception e){}
                                updateAngle(intValue);
                                error.setText(serial.getLastError());
                            }
                        });
                }
            }
        }.start();        

        //selectPanel(2);

        stage.show();
    }


    public static void main(String[] args) {
        serial = new SerialMonitor();
        serial.init();
        launch();
    }
}
