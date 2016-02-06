package com.netcracker.unc.view;

/**
 * Created by BIMO on 06.02.2016.
 */
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import com.netcracker.unc.controller.Controller;
import com.netcracker.unc.model.Student;

public class StudentsTable extends AbstractTableModel{
    // Список загловков для колонок в таблице
    private static final String[] headers = {"Факультет", "Группа", "Имя","Дата зачисления"};

    // Здесь мы храним список контактов, которые будем отображать в таблице
    ArrayList<Student> students = Controller.getControl().getStudents();

    public StudentsTable(ArrayList<Student> students){
        this.students = students;
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



}
