package com.netcracker.unc.model;

import java.io.*;
import java.util.ArrayList;

public final class Model {
    private static Model model = null;
    private ArrayList<Student> students = new ArrayList<Student>();
    private ArrayList<Group> groups = new ArrayList<Group>();
    private Group defaultGroup = new Group(0, "Отчисленные");

    private Model() {
    }

    public static synchronized Model getModel() {
        if (model == null)
            model = new Model();
        return model;
    }

    public Group getDefaultGroup() {
        return defaultGroup;
    }

    public void setDefaultGroup(Group defaultGroup) {
        this.defaultGroup = defaultGroup;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void saveAllInByteFile() throws IOException {
        FileOutputStream fos = new FileOutputStream("students");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(students);
        out.flush();
        out.close();
    }

    public void saveAllStudentsInTXTFile() throws IOException {
        StringBuilder strBuild = new StringBuilder();
        for (Student thisStudent : students) {
            strBuild.append(thisStudent.toString() + "\n");
        }
        File file = new File("students.txt");

        FileWriter fw = new FileWriter(file);
        fw.write(strBuild.toString());
        fw.close();
    }

    public String readStudentsFromTXTFile() throws IOException {

        FileReader fr = new FileReader("students.txt");
        BufferedReader br = new BufferedReader(fr);
        StringBuilder strBuild = new StringBuilder();
        while (br.ready()) {
            strBuild.append(br.readLine() + "\n");
        }
        br.close();
        return strBuild.toString();
    }

    public ArrayList getStudentsFromByteFile() throws ClassNotFoundException, IOException {
        ArrayList<Student> arrayOfStudents = null;
        {
            FileInputStream fis = new FileInputStream("studentArrayList.txt");
            ObjectInputStream in = new ObjectInputStream(fis);
            arrayOfStudents = (ArrayList<Student>) in.readObject();
            in.close();
        }
        return arrayOfStudents;
    }

    public Student getStudentByName(String name) {
        Student thisStudent = null;
        for (Student stud : students) {
            if (stud.getNameOfStudent().compareToIgnoreCase(name) == 0) {
                thisStudent = stud;
                return thisStudent;
            }
        }
        return thisStudent;
    }

    public String seeInfoAboutStudentByName(String name) {
        return getStudentByName(name).toString();
    }

    public void createStudent(Student student) {
        students.add(student);
    }

    public void createGroup(Group group) {
        groups.add(group);
    }

    public void deleteStudentByName(String name) {
        Student stud = getStudentByName(name);
        stud.setGroup(defaultGroup);
    }

    public void deleteGroup(Group group) {

        for (Group thisGroup : groups) {
            if (thisGroup.equals(group)) {
                for (Student thisStudent : students) {
                    if (thisStudent.getGroup().equals(group))
                        thisStudent.setGroup(defaultGroup);
                }
            }
            groups.remove(group);
        }
    }

    public void modifyStudentByName(String oldName, String newName, Group group, String dateOfEnrollment) {
        getStudentByName(oldName).setDateOfEnrollment(dateOfEnrollment);
        getStudentByName(oldName).setGroup(group);
        getStudentByName(oldName).setNameOfStudent(newName);
    }

    public void modifyGroup(Group group, int newNumberOfGroup, String newFaculty) {
        for (Group thisGroup : groups) {
            if (thisGroup.equals(group)) {
                thisGroup.setNumberOfGroup(newNumberOfGroup);
                thisGroup.setFaculty(newFaculty);
            }
        }
    }

    public String seeStudentsOfThisGroup(Group group) {
        StringBuilder strBuild = new StringBuilder();
        for (Student thisStudent : students) {
            if (thisStudent.getGroup().equals(group))
                strBuild.append(thisStudent.getNameOfStudent() + "\n");
        }
        return strBuild.toString();
    }
}
