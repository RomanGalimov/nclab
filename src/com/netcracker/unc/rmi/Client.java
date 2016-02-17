package com.netcracker.unc.rmi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

import com.netcracker.unc.model.Group;
import com.netcracker.unc.model.Student;
import com.netcracker.unc.view.*;

import javax.swing.*;

public class Client {


    public static void main(String[] args) throws Exception {
        Registry registry = LocateRegistry.getRegistry("localhost", 6666);
        RemoteController control = (RemoteController) registry.lookup("control");

        try {
            control.takeModelFromByteFile();
            //JOptionPane.showMessageDialog(null,"Файл отсутствует","Inane error",JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Файл отсутствует", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
        MainFrame frame = new MainFrame(control);
        frame.setVisible(true);
        try {
            control.saveAllInByteFile();
        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Файл отсутствует", "Inane error", JOptionPane.ERROR_MESSAGE);
        }
    }


}
