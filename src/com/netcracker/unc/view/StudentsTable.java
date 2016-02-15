package com.netcracker.unc.view;


import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Student;
import com.netcracker.unc.rmi.Client;

public class StudentsTable extends AbstractTableModel /*implements TableModelListener*/{
    // Список загловков для колонок в таблице
    private static final String[] headers = {"Факультет", "Группа", "Имя","Дата зачисления"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    ArrayList<Student> students;

    public StudentsTable(ArrayList<Student> students){
        this.students = students;
        //this.addTableModelListener(this);
    }

    public StudentsTable()throws Exception{
        this.students = Client.control().getStudents();
    }

    @Override
    // Получить количество строк в таблице - у нас это размер коллекции
    public int getRowCount() {
        return students.size();
    }

    @Override
    // Получить количество столбцов - их у нас стольк же, сколько полей
    public int getColumnCount() {
        return 4;
    }

    @Override
    public String getColumnName(int col){
        return headers[col];
    }

    @Override
    public Object getValueAt(int row, int col){
        Student student = students.get(row);
        switch(col){
            case 0:
                return student.getGroup().getFaculty();
            case 1:
                return student.getGroup().getNumberOfGroup();
            case 2:
                return student.getNameOfStudent();
            default:
                return student.getDateOfEnrollment();
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }



    @Override
    public void setValueAt(Object aValue, int row, int col) {
        Student student = students.get(row);
        switch(col){
            case 0:
                try {
                    String faculty = new String((aValue.toString()));
                    Client.control().modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),student.getDateOfEnrollment(),student.getGroup().getNumberOfGroup() ,faculty );

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            case 1:
                try {
                    int number = new Integer(aValue.toString()).intValue();
                    Client.control().modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),student.getDateOfEnrollment(),number  ,student.getGroup().getFaculty() );

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            case 2:
                try {
                    String name = new String((aValue.toString()));
                    Client.control().modifyStudentByName(student.getNameOfStudent(),name,student.getDateOfEnrollment(),student.getGroup().getNumberOfGroup(),student.getGroup().getFaculty() );

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
            default:
                try {
                    String date = new String((aValue.toString()));
                    Client.control().modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),date,student.getGroup().getNumberOfGroup(),student.getGroup().getFaculty() );

                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
        }

    }
}
