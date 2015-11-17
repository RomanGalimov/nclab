package com.netcracker.unc.controller;

import com.netcracker.unc.model.Group;
import com.netcracker.unc.model.Model;
import com.netcracker.unc.model.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class Controller {
    private static Controller control = null;

    private Controller() {
    }

    public static synchronized Controller getControl() {
        if (control == null)
            control = new Controller();
        return control;
    }

    public void saveAllInByteFile() throws Exception {
        if (Model.getModel().getStudents() == null) {
            throw new Exception("нечего сохранять");
        } else {
            Model.getModel().saveAllInByteFile();
        }
    }

    public void saveAllStudentsInTXTFile() throws Exception {
        if (Model.getModel().getStudents() == null) {
            throw new Exception("нечего сохранять");
        } else {
            Model.getModel().saveAllStudentsInTXTFile();
        }
    }

    public String getStudentsFromTXTFile() throws IOException {
        if (!new File("students.txt").exists()) {
            throw new FileNotFoundException("File not found");
        }
        return Model.getModel().readStudentsFromTXTFile();
    }

    public ArrayList getStudentsFromByteFile() throws Exception {
        if (!new File("students").exists())
            throw new FileNotFoundException("File not found");
        return Model.getModel().getStudentsFromByteFile();
    }

    public Student getStudentByName(String name) {
        return Model.getModel().getStudentByName(name);
    }//если возвращает налл - такого студента нет в системе

    public String getInfoAboutStudentByName(String name) {
        if (Model.getModel().getStudentByName(name) == null) {
            throw new NullPointerException("Student not found");
        }
        return Model.getModel().seeInfoAboutStudentByName(name);
    }

    public void createStudent(String name, int numberOfGroup, String faculty, String dateOfEnrollment) throws Exception {
        Group group = new Group(numberOfGroup, faculty);
        Student newStudent = new Student(name, group, dateOfEnrollment);
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (!thisGroup.equals(group))
                throw new Exception("Такой группы нет в системе");
        }
        for (Student thisStud : Model.getModel().getStudents()) {
            if (thisStud.equals(newStudent))
                throw new IllegalArgumentException("Такой студент уже есть в системе");
        }
        Model.getModel().createStudent(newStudent);
    }

    public void createGroup(int numberOfGroup, String faculty) {
        Group newGroup = new Group(numberOfGroup, faculty);
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(newGroup))
                throw new IllegalArgumentException("Такая группа уже есть в системе");
        }
        Model.getModel().createGroup(newGroup);
    }

    public void deleteStudentByName(String name) {
        if (Model.getModel().getStudentByName(name) == null) {
            throw new IllegalArgumentException("Такого студента нет в системе");
        }
        Model.getModel().deleteStudentByName(name);
    }

    public void deleteGroup(int numberOfGroup, String faculty) {
        Group group = new Group(numberOfGroup, faculty);
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        Model.getModel().deleteGroup(group);
    }

    public void modifyStudentByName(String oldName, String newName, String dateOfEnrollment, int numberOfGroup, String faculty) {
        if (Model.getModel().getStudentByName(oldName) == null) {
            throw new IllegalArgumentException("Такого студента нет в системе");
        }
        Group group = new Group(numberOfGroup, faculty);
        Model.getModel().modifyStudentByName(oldName, newName, group, dateOfEnrollment);
    }

    public void modifyGroup(int oldNumberOfGroup, String oldFaculty, int newNumberOfGroup, String newFaculty) {
        Group group = new Group(oldNumberOfGroup, oldFaculty);
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        Model.getModel().modifyGroup(group, newNumberOfGroup, newFaculty);
    }

    public String getStudentsOfGroup(Group group) {
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        return Model.getModel().seeStudentsOfThisGroup(group);
    }
}