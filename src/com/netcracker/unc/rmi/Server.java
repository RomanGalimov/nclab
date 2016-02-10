package com.netcracker.unc.rmi;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import com.netcracker.unc.controller.Controller;

public class Server {
    public static void main(String[] args) throws Exception {
        System.out.print("Starting registry...");
        final Registry registry = LocateRegistry.createRegistry(6666);
        System.out.println(" OK");
        final RemoteController control = Controller.getControl();
        Remote stub = UnicastRemoteObject.exportObject(control,0);
        registry.bind("control",stub);

    }
}
