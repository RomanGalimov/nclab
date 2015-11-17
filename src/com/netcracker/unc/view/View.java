package com.netcracker.unc.view;

import com.netcracker.unc.controller.Controller;

import java.util.Scanner;

public final class View {
    private static View view = null;

    private View() {
    }

    public static synchronized View getView() {
        if (view == null) {
            view = new View();
        }
        return view;
    }

    public void menuMain() throws Exception {
        //Scanner in = new Scanner(System.in);
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
        View.getView().choice();
    }

    public void choice() throws Exception {
        System.out.println("Выберите номер из пунктов выше: что необходимо сделать?");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();

        switch (choice) {
            case 1:  //Посмотреть список студентов
                // View.getView().menuListsOfStudents();
                System.out.println(Controller.getControl().getStudentsFromTXTFile());
                View.getView().choice();
                break;
            case 2:  //Добавить новую группу
            {
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                Controller.getControl().createGroup(number, faculty);
                View.getView().choice();
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
                Controller.getControl().createStudent(name, number, faculty, date);
                View.getView().choice();
            }
            break;
            case 4: //Редактировать уже существующую запись студента
            {
                System.out.println("Введите ФИО студента");
                String oldName = in.next();
                System.out.println("Старая информация о студенте:");
                System.out.println(Controller.getControl().getInfoAboutStudentByName(oldName));
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                System.out.println("Введите ФИО студента:");
                String newName = in.next();
                System.out.println("Введите дату зачисления:");
                String date = in.next();
                Controller.getControl().modifyStudentByName(oldName, newName, date, number, faculty);
                System.out.println("Новая информация о студенте:");
                System.out.println(Controller.getControl().getInfoAboutStudentByName(newName));
                View.getView().choice();
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
                Controller.getControl().modifyGroup(oldNumber, oldFaculty, newNumber, newFaculty);
                View.getView().choice();
            }
            break;
            case 6: //Удалить студента
            {
                System.out.println("Введите ФИО удаляемого студента");
                String name = in.next();
                Controller.getControl().deleteStudentByName(name);
                View.getView().choice();
            }
            break;
            case 7: //Удалить группу
            {
                System.out.println("Введите факультет:");
                String faculty = in.next();
                System.out.println("Введите номер группы:");
                int number = in.nextInt();
                Controller.getControl().deleteGroup(number, faculty);
                View.getView().choice();
            }
            break;
            case 0: //Выход

                break;
            default:
                System.out.println("Неверный ввод данных");
                View.getView().choice();
                break;
        }
    }

    /*
     public void menuListsOfStudents() throws Exception{
        Scanner in = new Scanner(System.in);
        System.out.println("Хотите посмотреть всех студентов - нажмите '1'.");
        System.out.println("Посмотреть студентов выбранной группы - нажмите '2'");
        System.out.println("Главное меню - нажмите '0'");
        int choice = in.nextInt();
        switch (choice){
            case 1:
            {
                System.out.println(Controller.getControl().getStudentsFromTXTFile()) ;
                View.getView().menuListsOfStudents();
            }
            break;
            case 2:
            {
                System.out.println("Введите факультет");
                String faculty = in.next();
                System.out.println("Введите номер группы");
                int number = in.nextInt();
                Controller.getControl().getStudentsOfGroup(number, faculty);
                View.getView().menuListsOfStudents();
            }
            break;
            case 0:
            {
                View.getView().menuMain();
            }
            break;
            default :
                System.out.println("Неверный ввод данных");
                View.getView().menuListsOfStudents();
                break;
        }
     }
     */
    public static void main(String[] args) throws Exception {
        View view = new View();
        view.menuMain();
    }
}
