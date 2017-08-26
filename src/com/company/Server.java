package com.company;

import java.io.*;
import java.net.*;
import java.util.*;
import java.time.*;
import java.text.SimpleDateFormat;

public class Server{
    //////////////////Variables//////////////////
    private ServerSocket ss;
    private Hashtable outputStreams = new Hashtable();
    private ArrayList<User> userlist = new ArrayList<User>();
    private boolean record = true;
    private String logName;

    /////////////////Constructors/////////////////
    public Server(int port) throws IOException{
        //Creates file for log
        if(record){
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HHmm");
            logName = "ServerLog_" + sdf.format(cal.getTime()) + ".txt";
            PrintWriter writer = new PrintWriter(new FileOutputStream(new File(logName), false /* append = true */));
            writer.println("Server Log");
            writer.println("==========");
            writer.close();
        }

        //Listens to server input
        Thread inputThread = new Thread(new Runnable() {
            @Override
            public void run() {

                Scanner scan = new Scanner(System.in);
                String input = "";
                while (true) {
                    input = scan.nextLine();
                    sendToAll("[Server] " + input);
                }
            }
        });
        inputThread.start();

        //Operates all other server functions
        listen(port);
    }

    /////////////////Functions//////////////////
    //Waits for new connections
    private void listen(int port) throws IOException{
        ss = new ServerSocket(port);
        System.out.println("Listening on " + ss);

        while(true){
            Socket s = ss.accept();
            System.out.println("Connections from " + s);

            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            outputStreams.put(s, dout);

            new ServerThread(this, s);
        }
    }

    //Gets an enumeration of all the output streams
    Enumeration getOutputStreams(){
        return outputStreams.elements();
    }

    //Sends a message to all clients
    void sendToAll(String message){
        //Adds a timestamp
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");

        message = sdf.format(cal.getTime()) + " " + message;

        synchronized(outputStreams){
            for(Enumeration e = getOutputStreams(); e.hasMoreElements();){
                DataOutputStream dout = (DataOutputStream)e.nextElement();

                try{
                    dout.writeUTF(message);
                } catch(IOException ie){
                    System.out.println(ie);
                }
            }
        }

        System.out.println(message);
        writeToLog(message);
    }

    //Removes a connection
    public void removeConnection(Socket s){
        synchronized(outputStreams){
            System.out.println("Removing connection to " + s);

            outputStreams.remove(s);

            try{
                s.close();
            } catch (IOException ie) {
                System.out.println("Error closing " + s);
                ie.printStackTrace();
            }
        }
    }

    //Interprets the input and takes appropriate action
    public void processInput(String input){
        //Checks for a command
        if(input.length() > 1 && input.substring(0, 2).equals("//")){
            determineCommand(input);
        } else {
            this.sendToAll(input);
        }
    }

    //Will determine a potential command and execute
    private void determineCommand(String command){
        try{
            int a = command.indexOf(" ");

            if(command.substring(0, a).equals("//say")){
                this.sendToAll("[Server]" + command.substring(a));
            }
        } catch(Exception e){

        }
    }

    private void writeToLog(String message){
        if(record){
            try {
                PrintWriter writer = new PrintWriter(new FileOutputStream(new File(logName), true /* append = true */));
                writer.println(message);
                writer.close();
            } catch(Exception e){

            }
        }
    }

    //Will add a user to the server
    private void addUser(){

    }

    //Will remove a user from the server
    private void removeUser(){

    }
}


