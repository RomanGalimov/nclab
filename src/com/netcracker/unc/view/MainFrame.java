package com.netcracker.unc.view;

import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Group;
import com.netcracker.unc.model.Student;
import com.netcracker.unc.rmi.RemoteController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Created by BIMO on 06.02.2016.
 */

//import java.util.List;


public class MainFrame extends JFrame implements ActionListener {


    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String DELETE = "DELETE";
   // private static final String SET = "SET";


    private final JTable studentsTable = new JTable();
    private final JTable groupsTable = new JTable();
    private final JTabbedPane tabbedPane = new JTabbedPane();

    private RemoteController control;

    public RemoteController getControl(){
        return control;
    }

    public MainFrame(RemoteController control) throws Exception{
        this.control=control;
        studentsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 0, 5);

        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(gb);
        btnPanel.add(createButton(gb, gbc, "Сохранить", LOAD));
        btnPanel.add(createButton(gb, gbc, "Добавить", ADD));
        //btnPanel.add(createButton(gb, gbc, "Исправить", SET));
        btnPanel.add(createButton(gb, gbc, "Удалить", DELETE));

        JPanel left = new JPanel();
        left.setLayout(new BorderLayout());
        left.add(btnPanel, BorderLayout.NORTH);
        add(left, BorderLayout.WEST);


        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addTab("Студенты",new JScrollPane(studentsTable));
        tabbedPane.addTab("Группы",new JScrollPane(groupsTable));

        setBounds(100, 200, 900, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        loadStudents();
        loadGroups();

    }

    private JButton createButton(GridBagLayout gb, GridBagConstraints gbc, String title, String action) {
        JButton button = new JButton(title);
        button.setActionCommand(action);
        button.addActionListener(this);
        gb.setConstraints(button, gbc);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String action = ae.getActionCommand();
        switch (action) {
            case LOAD:
                try {
                    saveAll();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            case ADD:
                try {
                    if (tabbedPane.getSelectedIndex() == 0) addStudent();
                    else addGroup();

                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }

                break;
            //case SET:

               // break;
            case DELETE:
                try {
                    if (tabbedPane.getSelectedIndex() == 0) deleteStudent();
                    else deleteGroup();
                } catch (Exception iae) {
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                    break;
                }
        }}


    private void loadStudents() throws Exception{

            ArrayList<Student> students = control.getStudents();
            StudentsTable st = new StudentsTable(students, control);
            studentsTable.setModel(st);

    }

    private void loadGroups() throws Exception {
            ArrayList<Group> groups = control.getGroups();
            GroupsTable gt = new GroupsTable(groups, control);
            groupsTable.setModel(gt);

    }

    private void addStudent() throws Exception {
        EditStudentFrame esf = new EditStudentFrame();
        if (esf.isSave()){
            esf.createStudent(control);
        }
        loadStudents();
    }

    private void addGroup() throws Exception{
        EditGroupFrame egf = new EditGroupFrame();
        if (egf.isSave()){
            egf.createGroup(control);
        }
        loadGroups();
    }

    private void deleteStudent() throws Exception{
        int sr = studentsTable.getSelectedRow();
        if (sr!=-1){
            String name = studentsTable.getModel().getValueAt(sr,2).toString();
            control.deleteStudentByName(name);
            loadStudents();
        } else {
        JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
    }

    }

    private void deleteGroup() throws Exception{
        int sr = groupsTable.getSelectedRow();
        if (sr!=-1){
            int numberOfGroup = Integer.parseInt(groupsTable.getModel().getValueAt(sr,1).toString());
            String faculty = groupsTable.getModel().getValueAt(sr,0).toString();
            control.deleteGroup(numberOfGroup,faculty);
            loadGroups();
        } else {
        JOptionPane.showMessageDialog(this, "Вы должны выделить строку для удаления");
    }
    }

    private void saveAll() throws Exception {
        try{
            control.saveAllInByteFile();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null,"Файл отсутствует","Inane error",JOptionPane.ERROR_MESSAGE);
        }
    }




 /*   public static void main(String[] args) throws Exception{
        try{
            Controller.getControl().takeModelFromByteFile();
            //JOptionPane.showMessageDialog(null,"Файл отсутствует","Inane error",JOptionPane.ERROR_MESSAGE);
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null,"Файл отсутствует","Inane error",JOptionPane.ERROR_MESSAGE);
        }
        MainFrame cf = new MainFrame();
        cf.setVisible(true);
        try{
            Controller.getControl().saveAllInByteFile();
        } catch (FileNotFoundException fnfe){
            JOptionPane.showMessageDialog(null,"Файл отсутствует","Inane error",JOptionPane.ERROR_MESSAGE);
        }
    }*/
}

