package com.netcracker.unc.view;

/**
 * Created by BIMO on 06.02.2016.
 */

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Group;
import com.netcracker.unc.rmi.Client;

public class GroupsTable extends AbstractTableModel{
    private static final String[] headers = {"Факультет", "Группа"};

    ArrayList<Group> groups;

    public GroupsTable (ArrayList<Group> groups){
        this.groups = groups;
    }

    public GroupsTable() throws  Exception{
        this.groups= Client.control().getGroups();
    }

    @Override
    public int getRowCount() {
        return groups.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public String getColumnName(int col) {
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        Group group = groups.get(row);
        switch (col){
            case 0:
                return group.getFaculty();
            default:
                return group.getNumberOfGroup();
        }
    }

    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    public void setValueAt(Object aValue, int row, int col) {
        Group group = groups.get(row);
        switch (col){
            case 0:
                try {
                    String faculty = new String((aValue.toString()));
                    Client.control().modifyGroup(group.getNumberOfGroup(),group.getFaculty(),group.getNumberOfGroup(),faculty);

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            default:
                try {
                    int number = new Integer(aValue.toString()).intValue();
                    Client.control().modifyGroup(group.getNumberOfGroup(),group.getFaculty(),number,group.getFaculty());

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }

        }
    }
}
