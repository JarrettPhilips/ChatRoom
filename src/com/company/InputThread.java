package com.company;

import java.util.Scanner;

public class InputThread {
    /////////////Variables/////////////
    private Server server;

    /////////////Constructors/////////////
    public InputThread(Server server){
        this.server = server;
    }

    /////////////Functions//////////////
    private void run(){
        try{
            //Sets up the scanner
            Scanner s = new Scanner(System.in);

            //Awaits user input
            while(true){
                String i = s.next();
                i += "//" + i;
                System.out.println("Scanner recieved");
                Command.determineCommand(i, server);
            }
        } catch(Exception ie){
            System.out.println("Error in input thread");
        }
    }
}
