package com.netcracker.unc.view;

import com.netcracker.unc.controller.Controller;

import java.io.FileNotFoundException;
import java.util.Scanner;

public final class View {
    private static View view = null;

    private View(){
    }

    public static synchronized View getView(){
        if (view == null){
            view = new View();
        }
        return view;
    }

    public void menuMain() throws Exception{
        System.out.println("Вас приветствует программа для работы со списками студентов.");
        System.out.println("Выберите номер из пунктов ниже: что необходимо сделать?");
        System.out.println("");
        System.out.println("1 - Посмотреть списки студентов");
        System.out.println("2 - Добавить новую группу");
        System.out.println("3 - Добавить нового студента");
        System.out.println("4 - Редактировать уже существующую запись студента");
        System.out.println("5 - Изменить существующую группу");
        System.out.println("6 - Удалить студента");
        System.out.println("7 - Удалить группу");
        System.out.println("0 - Выход");
        View.getView().choiceInMenuMain();
    }

    public void choiceInMenuMain()  throws Exception{
        System.out.println("Выберите номер из пунктов выше: что необходимо сделать?");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice){
            case 1:  //Посмотреть список студентов
                View.getView().menuListsOfStudents();
                break;
            case 2:  //Добавить новую группу
            {
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                try{
                    Controller.getControl().createGroup(number, faculty);
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 3: //Добавить нового студента
            {
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                System.out.println("Введите ФИО студента:");
                String name = in.next();
                System.out.println("Введите дату зачисления:");
                String date = in.next();
                try{
                    Controller.getControl().createStudent(name, number, faculty, date);
                }catch (IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 4: //Редактировать уже существующую запись студента
            {
                System.out.println("Введите ФИО студента, инфо о котором будете изменять:");
                String oldName = in.next();
                try{
                    System.out.println("Старая информация о студенте:");
                    System.out.println(Controller.getControl().getInfoAboutStudentByName(oldName));
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                    View.getView().choiceInMenuMain();
                }
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                System.out.println("Введите ФИО студента:");
                String newName = in.next();
                System.out.println("Введите дату зачисления:");
                String date = in.next();
                try{
                    Controller.getControl().modifyStudentByName(oldName, newName, date, number, faculty);
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 5: //Изменить существующую группу
            {
                System.out.println("Введите факультет");
                String oldFaculty = in.next();
                System.out.println("Введите номер группы");
                int oldNumber = in.nextInt();
                System.out.println("Введите новый факультет");
                String newFaculty = in.next();
                System.out.println("Введите новый номер группы");
                int newNumber = in.nextInt();
                try{
                    Controller.getControl().modifyGroup(oldNumber, oldFaculty, newNumber, newFaculty);
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 6: //Удалить студента
            {
                System.out.println("Введите ФИО удаляемого студента");
                String name = in.next();
                try{
                    Controller.getControl().deleteStudentByName(name);
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 7: //Удалить группу
            {
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                try{
                    Controller.getControl().deleteGroup(number, faculty);
                }catch(IllegalArgumentException iae){
                    System.err.println("Не получилось! "+iae.getMessage());
                }
                View.getView().choiceInMenuMain();
            }
            break;
            case 0: //Выход

                break;
            default :
                System.err.println("Неверный ввод данных");
                View.getView().choiceInMenuMain();
                break;
        }
    }

    public void menuListsOfStudents() throws Exception{
        System.out.println("Хотите посмотреть всех студентов - нажмите '1'.");
        System.out.println("Посмотреть студентов выбранной группы - нажмите '2'");
        System.out.println("Главное меню - нажмите '0'");
        View.getView().choiceInMenuList();
    }

    public void choiceInMenuList()throws Exception{
        System.out.println("Выберите действие:");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        switch (choice){
            case 1:
            {
                System.out.println(Controller.getControl().getAllStudents()) ;
                View.getView().menuListsOfStudents();
            }
            break;
            case 2:
            {
                int flag = 1;
                while(flag == 1){
                    System.out.println("Введите факультет");
                    String faculty = in.next();
                    System.out.println("Введите номер группы");
                    int number = in.nextInt();
                    try{
                        System.out.println(Controller.getControl().getStudentsOfGroup(number, faculty));
                        flag = 0;
                    }catch(IllegalArgumentException iae){
                        System.err.println(iae.getMessage() +". Попробуйте ввести еще раз:");
                        flag = 1;
                        System.out.println("Чтобы продолжить, нажмите 1. Для отмены нажмите 0...");
                        int exit = in.nextInt();
                        if (exit == 0){
                            View.getView().menuListsOfStudents();
                        }
                    }
                }
                View.getView().choiceInMenuList();
            }
            break;
            case 0:
            {
                View.getView().menuMain();
            }
            break;
            default :
                System.err.println("Неверный ввод данных");
                View.getView().choiceInMenuList();
                break;
        }
    }

/*    public static void main(String[] args) throws Exception {
        try{
            Controller.getControl().takeModelFromByteFile();
        } catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
        View view = new View();
        view.menuMain();
        try{
            Controller.getControl().saveAllInByteFile();
        } catch (FileNotFoundException fnfe){
            System.err.println(fnfe.getMessage());
        }
    }*/
}



