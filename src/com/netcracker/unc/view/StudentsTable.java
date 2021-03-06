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
import com.netcracker.unc.rmi.RemoteController;

public class StudentsTable extends AbstractTableModel /*implements TableModelListener*/{
    // Список загловков для колонок в таблице
    private static final String[] headers = {"Факультет", "Группа", "Имя","Дата зачисления"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    ArrayList<Student> students;
    private RemoteController control;

    public StudentsTable(ArrayList<Student> students, RemoteController control){
        this.students = students;
        this.control=control;
        //this.addTableModelListener(this);
    }

    public StudentsTable(RemoteController control)throws Exception{
        this.students = control.getStudents();
        this.control=control;
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




    public void setValueAt(Object aValue, int row, int col/*, RemoteController control*/) {
        Student student = students.get(row);
        switch(col){
            case 0:
                try {
                    String faculty = new String((aValue.toString()));
                    control.modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),student.getDateOfEnrollment(),student.getGroup().getNumberOfGroup() ,faculty );
                    control.getStudents();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 1:
                try {
                    int number = new Integer(aValue.toString()).intValue();
                    control.modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),student.getDateOfEnrollment(),number  ,student.getGroup().getFaculty() );
                    control.getStudents();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 2:
                try {
                    String name = new String((aValue.toString()));
                    control.modifyStudentByName(student.getNameOfStudent(),name,student.getDateOfEnrollment(),student.getGroup().getNumberOfGroup(),student.getGroup().getFaculty() );
                    control.getStudents();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;
            default:
                try {
                    String date = new String((aValue.toString()));
                    control.modifyStudentByName(student.getNameOfStudent(),student.getNameOfStudent(),date,student.getGroup().getNumberOfGroup(),student.getGroup().getFaculty() );
                    control.getStudents();
                } catch (Exception iae){
                    JOptionPane.showMessageDialog(null, iae.getMessage(), "Inane error", JOptionPane.ERROR_MESSAGE);
                }
                break;
        }

    }
}
