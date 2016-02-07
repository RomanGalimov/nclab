package com.netcracker.unc.view;

/**
 * Created by BIMO on 06.02.2016.
 */

import com.netcracker.unc.controller.Controller;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
//import java.util.List;
import javax.swing.*;

import java.util.ArrayList;
import com.netcracker.unc.model.Student;
import com.netcracker.unc.model.Group;

public class MainFrame extends JFrame implements ActionListener {


    private static final String LOAD = "LOAD";
    private static final String ADD = "ADD";
    private static final String DELETE = "DELETE";
    private static final String SET = "SET";


    private final JTable studentsTable = new JTable();
    private final JTable groupsTable = new JTable();

    private MainFrame(){
        // Выставляем у таблицы свойство, которое позволяет выделить
        // ТОЛЬКО одну строку в таблице
        studentsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        groupsTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

        // Используем layout manager
        GridBagLayout gb = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();
        // Каждый элемент является последним в строке
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        // Элемент раздвигается на весь размер ячейки
        gbc.fill = GridBagConstraints.BOTH;
        // Но имеет границы - слева, сверху и справа по 5. Снизу - 0
        gbc.insets = new Insets(5, 5, 0, 5);

        // Создаем панель для кнопок
        JPanel btnPanel = new JPanel();
        // усанавливаем у него layout
        btnPanel.setLayout(gb);
        // Создаем кнопки
        btnPanel.add(createButton(gb, gbc, "Обновить", LOAD));
        btnPanel.add(createButton(gb, gbc, "Добавить", ADD));
        btnPanel.add(createButton(gb, gbc, "Исправить", SET));
        btnPanel.add(createButton(gb, gbc, "Удалить", DELETE));

        // Создаем панель для левой колокни с кнопками
        JPanel left = new JPanel();
        // Выставляем layout BorderLayout
        left.setLayout(new BorderLayout());
        // Кладем панель с кнопками в верхнюю часть
        left.add(btnPanel, BorderLayout.NORTH);
        // Кладем панель для левой колонки на форму слева - WEST
        add(left, BorderLayout.WEST);

        final JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
        tabbedPane.addTab("Студенты",new JScrollPane(studentsTable));
        tabbedPane.addTab("Группы",new JScrollPane(groupsTable));

        // выставляем координаты формы
        setBounds(100, 200, 900, 400);
        // При закрытии формы заканчиваем работу приложения
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    // Метод создает кнопку с заданными харктеристиками - заголовок и действие
    private JButton createButton(GridBagLayout gb, GridBagConstraints gbc, String title, String action) {
        // Создаем кнопку с заданным загловком
        JButton button = new JButton(title);
        // Действие будетп роверяться в обработчике и мы будем знать, какую
        // именно кнопку нажали
        button.setActionCommand(action);
        // Обработчиком события от кнопки являемся сама форма
        button.addActionListener(this);
        // Выставляем свойства для размещения для кнопки
        gb.setConstraints(button, gbc);
        return button;
    }

    @Override
    // Обработка нажатий кнопок
    public void actionPerformed(ActionEvent ae) {
        // Получаем команду - ActionCommand
        String action = ae.getActionCommand();
        // В зависимости от команды выполняем действия
        switch (action) {
            // Загрузка данных
            case LOAD:
                loadStudents();
                break;
            // Добавление записи
            case ADD:

                break;
            // Исправление записи
            case SET:

                break;
            // Удаление записи
            case DELETE:

                break;
        }
    }

    //Дальше описание кнопок

    //Загрузить список студентов
    private void loadStudents()/* throws Exception */{
       // try {
            ArrayList<Student> students = Controller.getControl().getStudents();
            StudentsTable st = new StudentsTable(students);
            studentsTable.setModel(st);
       /* }catch (NullPointerException npe){
            System.err.println(npe.getMessage());
        }*/
    }

    //Загрузить список групп
    private void loadGroups() throws Exception{
        try{
            ArrayList<Group> groups = Controller.getControl().getGroups();
            GroupsTable gt = new GroupsTable(groups);
            groupsTable.setModel(gt);
        }catch (NullPointerException npe){
            System.err.println(npe.getMessage());
        }
    }

    //Добавить студента

    //Добавить группу

    //Редактировать студента

    //Редактировать группу

    //Удалить студента

    //Удалить группу




    public static void main(String[] args) throws Exception{
        //загружаем данные
        try{
            Controller.getControl().takeModelFromByteFile();
        } catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
        MainFrame cf = new MainFrame();
        cf.setVisible(true);
        try{
            Controller.getControl().saveAllInByteFile();
        } catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }
}

