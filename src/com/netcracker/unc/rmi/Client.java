package com.netcracker.unc.rmi;

import java.net.*;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost",6666);
        RemoteController control = (RemoteController) registry.lookup("control");
        //view

    }

}