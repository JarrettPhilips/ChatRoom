package com.company;

public class Main {
    /////////////////Variables/////////////
    public static Server server;

    ////////////////Main///////////////
    public static void main(String[] args) throws Exception{
        //int port = Integer.parseInt(args[0]);

        server = new Server(25565);
    }
}
