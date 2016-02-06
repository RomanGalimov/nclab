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
import java.util.List;
import javax.swing.*;

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

        //загружаем данные
        /*try{
            Controller.getControl().takeModelFromByteFile();
        } catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }*/
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
            // Перегрузка данных
            case LOAD:

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



    public static void main(String[] args) {
        MainFrame cf = new MainFrame();
        cf.setVisible(true);
    }
}

