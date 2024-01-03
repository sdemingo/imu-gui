package com.example;



public class CommandBuffer{

    private final int BUFFER_SIZE=10;

    private Command[] buffer;
    private int putpos, takepos, count;


    public CommandBuffer(){
        buffer = new Command[BUFFER_SIZE];
    }

    public synchronized void writeCommand(Command c){
        try {
            while (isFull()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doPut(c);
        notifyAll();
    }
    
    public synchronized Command readCommand(){
        try {
            while (isEmpty()) {
                wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Command c = doTake();
        notifyAll();
        return c;
    }

    public synchronized boolean isFull() {
        return count == buffer.length;
    }

    public synchronized boolean isEmpty() {
        return count == 0;
    }


    protected synchronized void doPut(Command c) {
        buffer[putpos] = c;
        if (++putpos == buffer.length) {
            putpos = 0;
        }
        ++count;
    }

    protected synchronized Command doTake() {
        Command command = buffer[takepos];
        if (++takepos == buffer.length) {
            takepos = 0;
        }
        --count;
        return command;
    }
}
