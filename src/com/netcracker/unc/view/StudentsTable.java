package com.netcracker.unc.view;


import java.util.ArrayList;
import java.util.EventListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Student;

public class StudentsTable extends AbstractTableModel /*implements TableModelListener*/{
    // Список загловков для колонок в таблице
    private static final String[] headers = {"Факультет", "Группа", "Имя","Дата зачисления"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    ArrayList<Student> students = Controller.getControl().getStudents();

    public StudentsTable(ArrayList<Student> students){
        this.students = students;
        //this.addTableModelListener(this);
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



  /*  public void tableChanged(TableModelEvent e){
        int row = e.getFirstRow();
        int col = e.getColumn();

    }*/
    /*@Override
    public void setValueAt(Object aValue, int row, int col) {
        //super.setValueAt(aValue, rowIndex, columnIndex);
        Student student = students.get(row);
        switch(col){
            case 0:
                *//*return*//* student.getGroup().setFaculty(aValue);
            case 1:
                return student.getGroup().getNumberOfGroup();
            case 2:
                return student.getNameOfStudent();
            default:
                return student.getDateOfEnrollment();
        }

    }*/
}
