package com.netcracker.unc.view;

/**
 * Created by BIMO on 06.02.2016.
 */

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Group;

public class GroupsTable extends AbstractTableModel{
    private static final String[] headers = {"Факультет", "Группа"};

    ArrayList<Group> groups = Controller.getControl().getGroups();

    public GroupsTable (ArrayList<Group> groups){
        this.groups = groups;
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
                    Controller.getControl().modifyGroup(group.getNumberOfGroup(),group.getFaculty(),group.getNumberOfGroup(),faculty);

                } catch (IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            default:
                try {
                    int number = new Integer(aValue.toString()).intValue();
                    Controller.getControl().modifyGroup(group.getNumberOfGroup(),group.getFaculty(),number,group.getFaculty());

                } catch (IllegalArgumentException iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }

        }
    }
}
