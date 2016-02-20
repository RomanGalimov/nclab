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
import com.netcracker.unc.rmi.RemoteController;

public class GroupsTable extends AbstractTableModel{
    private static final String[] headers = {"Факультет", "Группа"};

    ArrayList<Group> groups;
    private RemoteController control;

    public GroupsTable (ArrayList<Group> groups){
        this.groups = groups;
    }

    public GroupsTable(RemoteController control) throws  Exception{
        this.groups= control.getGroups();
        this.control=control;
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

    public void setValueAt(Object aValue, int row, int col/*,RemoteController control*/) {
        Group group = groups.get(row);
        switch (col){
            case 0:
                try {
                    String faculty = new String((aValue.toString()));
                    control.modifyGroup(group.getNumberOfGroup(),group.getFaculty(),group.getNumberOfGroup(),faculty);
                    control.getGroups();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                try {
                    int number = new Integer(aValue.toString()).intValue();
                    control.modifyGroup(group.getNumberOfGroup(),group.getFaculty(),number,group.getFaculty());
                    control.getGroups();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;

        }
    }
}
