package com.example;


public class Command{

    private int angleX;
    private int angleY;

    public Command(int x,int y){
        angleX=x;
        angleY=y;
    }

    public void setAngleX(int x){
        angleX=x;
    }

    public void setAngleY(int y){
        angleY=y;
    }

    public int getAngleX(){
        return angleX;
    }

    public int getAngleY(){
        return angleY;
    }

/*
    public String toString(){
        return System.out.Sprintf("%d,%d",angleX,angleY);
    }*/

}
