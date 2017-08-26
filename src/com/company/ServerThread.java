package com.company;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ServerThread extends Thread{
    /////////////////Variables///////////////
    private Server server;

    private Socket socket;

    /////////////////Constructors////////////////
    public ServerThread(Server server, Socket socket){
        this.server = server;
        this.socket = socket;

        //run();
        start();
    }

    /////////////////Functions/////////////////
    public void run(){
        try{
            DataInputStream din =  new DataInputStream(socket.getInputStream());

            while(true){
                String message = din.readUTF();
                server.processInput(message);
            }
        } catch(EOFException ie){

        } catch(IOException ie){
            //ie.printStackTrace(); //Use for specifics on closing of socket
        } finally {
            server.removeConnection(socket);
        }
    }
}
