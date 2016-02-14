package com.netcracker.unc.rmi;

import com.netcracker.unc.model.Student;

import java.rmi.*;
import java.util.ArrayList;



public interface RemoteController extends Remote {

    void saveAllInByteFile() throws Exception;

    void takeModelFromByteFile() throws Exception;

    String getAllStudents() throws  Exception;

    Student getStudentByName(String name) throws Exception;

    String getInfoAboutStudentByName(String name) throws Exception;

    void createStudent(String name, int numberOfGroup, String faculty, String dateOfEnrollment) throws Exception;

    void createGroup(int numberOfGroup, String faculty) throws Exception;

    void deleteStudentByName(String name) throws Exception;

    void deleteGroup(int numberOfGroup, String faculty) throws Exception;

    void modifyStudentByName(String oldName, String newName, String dateOfEnrollment, int numberOfGroup, String faculty) throws Exception;

    void modifyGroup(int oldNumberOfGroup, String oldFaculty, int newNumberOfGroup, String newFaculty) throws Exception;

    String getStudentsOfGroup(int numberOfGroup,String faculty) throws Exception;

    ArrayList<Student> getSortedListOfStudents() throws Exception;
}
