package com.netcracker.unc.controller;

import com.netcracker.unc.model.Group;
import com.netcracker.unc.model.Model;
import com.netcracker.unc.model.Student;
import com.netcracker.unc.rmi.RemoteController;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public final class Controller implements RemoteController {
    private static Controller control = null;

    private Controller() {
    }

    public synchronized static Controller getControl() {
        if (control == null)
            control = new Controller();
        return control;
    }

    public synchronized void saveAllInByteFile() throws Exception {

        Model.getModel().saveAllInByteFile();
    }

    public synchronized void saveAllStudentsInTXTFile() throws Exception {
        Model.getModel().saveAllStudentsInTXTFile();
    }

    public synchronized String getStudentsFromTXTFile() throws IOException {
        if (!new File("students.txt").exists()) {
            throw new FileNotFoundException("File not found");
        }
        return Model.getModel().readStudentsFromTXTFile();
    }

    public synchronized void takeModelFromByteFile() throws Exception {
        if (!new File("model").exists())
            throw new FileNotFoundException("File not found");
        Model.getModel().takeModelFromByteFile();
    }

    public synchronized String getAllStudents(){
        if(Model.getModel().getStudents()==null){
            throw new NullPointerException();
        }
        return Model.getModel().getAllStudents();
    }

    public synchronized Student getStudentByName(String name) {
        return Model.getModel().getStudentByName(name);
    }//если возвращает налл - такого студента нет в системе

    public synchronized String getInfoAboutStudentByName(String name) {
        if (Model.getModel().getStudentByName(name) == null) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        return Model.getModel().seeInfoAboutStudentByName(name);
    }

    public synchronized void createStudent(String name, int numberOfGroup, String faculty, String dateOfEnrollment) throws Exception {
        Group group = new Group(numberOfGroup, faculty);
        Student newStudent = new Student(name, group, dateOfEnrollment);
        if(Model.getModel().getGroups()==null)
            throw new IllegalArgumentException("Такой группы нет в системе");
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (!is)
            throw new IllegalArgumentException("Такой группы нет в системе");
        for (Student thisStud : Model.getModel().getStudents()) {
            if (thisStud.equals(newStudent))
                throw new IllegalArgumentException("Такой студент уже есть в системе");
        }
        Model.getModel().createStudent(newStudent);
    }

    public synchronized void createGroup(int numberOfGroup, String faculty) {
        Group newGroup = new Group(numberOfGroup, faculty);
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(newGroup))
                is = true;
        }
        if (is == true)
            throw new IllegalArgumentException("Такая группа уже есть");
        Model.getModel().createGroup(newGroup);
    }

    public synchronized void deleteStudentByName(String name) {
        if (Model.getModel().getStudentByName(name) == null) {
            throw new IllegalArgumentException("Такого студента нет в системе");
        }
        Model.getModel().deleteStudentByName(name);
    }

    public synchronized void deleteGroup(int numberOfGroup, String faculty) {
        Group group = new Group(numberOfGroup, faculty);
        boolean is = false;
        if(Model.getModel().getGroups()==null)
            throw new IllegalArgumentException("Такой группы нет в системе");
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        Model.getModel().deleteGroup(group);
    }

    public synchronized void modifyStudentByName(String oldName, String newName, String dateOfEnrollment, int numberOfGroup, String faculty) {
        if (Model.getModel().getStudentByName(oldName) == null) {
            throw new IllegalArgumentException ("Такого студента нет в системе");
        }
        Group group = new Group(numberOfGroup,faculty);
        boolean is = false;
        if(Model.getModel().getGroups()==null)
            throw new IllegalArgumentException("Такой группы нет в системе");
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        Model.getModel().modifyStudentByName(oldName, newName, group, dateOfEnrollment);
    }

    public synchronized void modifyGroup(int oldNumberOfGroup, String oldFaculty, int newNumberOfGroup, String newFaculty) {
        Group group=new Group(oldNumberOfGroup,oldFaculty);
        if(Model.getModel().getGroups()==null)
            throw new IllegalArgumentException("Такой группы нет в системе");
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        Model.getModel().modifyGroup(group,newNumberOfGroup,newFaculty);
    }

    public synchronized String getStudentsOfGroup(int numberOfGroup,String faculty) {
        Group group=new Group(numberOfGroup,faculty);
        if(Model.getModel().getGroups()==null)
            throw new IllegalArgumentException("Такой группы нет в системе");
        boolean is = false;
        for (Group thisGroup : Model.getModel().getGroups()) {
            if (thisGroup.equals(group))
                is = true;
        }
        if (is == false)
            throw new IllegalArgumentException("Такой группы нет в системе");
        return Model.getModel().seeStudentsOfThisGroup(group);
    }

    public synchronized ArrayList<Student> getSortedListOfStudents(){
        return Model.getModel().getSortedListOfStudents();
    }

    /*~~~*/

    public synchronized ArrayList<Student> getStudents(){
        if(Model.getModel().getStudents()==null){
            throw new NullPointerException();
        }
        return Model.getModel().getStudents();
    }

    public synchronized ArrayList<Group> getGroups(){
        if(Model.getModel().getGroups()==null){
            throw new NullPointerException();
        }
        return Model.getModel().getGroups();
    }
}