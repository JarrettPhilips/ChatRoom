package com.company;

public class Command{
    public static void determineCommand(String command, Server server){
        try{
            int a = command.indexOf(" ");

            if(command.substring(0, a).equals("//say")){
                server.sendToAll(" [Server] " + command.substring(a));
            }
        } catch(Exception e){

        }
    }
}
