package com.netcracker.unc.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public final class Model implements Serializable {
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
        FileOutputStream fos = new FileOutputStream("model");
        ObjectOutputStream out = new ObjectOutputStream(fos);
        out.writeObject(this);
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

    /**
     * Shows info about all students
     * @return string with info
     */
    public String getAllStudents() {
        StringBuilder strBuild = new StringBuilder();
        for (int i = 0; i < students.size(); i++) {
            strBuild.append(students.get(i).toString() + "\n");
        }
        return strBuild.toString();
    }

    public void takeModelFromByteFile() throws ClassNotFoundException, IOException {
        FileInputStream fis = new FileInputStream("model");
        ObjectInputStream in = new ObjectInputStream(fis);
        Model model = (Model) in.readObject();
        in.close();
        this.students = model.students;
        this.defaultGroup = model.defaultGroup;
        this.groups = model.groups;

    }

    /**
     * Finds student by name in array
     * @param name
     * @return student by name
     */
    public Student getStudentByName(String name) {
        Student thisStudent = null;
        for (Student stud : getStudents()) {
            if (stud.getNameOfStudent().compareToIgnoreCase(name) == 0) {
                thisStudent = stud;
                return thisStudent;
            }
        }
        return thisStudent;
    }

    /**
     * Shows info about the student
     * @param name
     * @return string with info
     */
    public String seeInfoAboutStudentByName(String name) {
        return getStudentByName(name).toString();
    }

    /**
     * Adds new student to the student array
     * @param student
     */
    public void createStudent(Student student) {
        if (students == null)
            setStudents(new ArrayList<Student>());
        students.add(student);

    }

    /**
     * Adds new group to the group array
     * @param group
     */
    public void createGroup(Group group) {
        if (groups == null)
            setGroups(new ArrayList<Group>());
        groups.add(group);
    }

    /**
     * Moves student from his group to default group
     * @param name
     */
    public void deleteStudentByName(String name) {
        Student stud = getStudentByName(name);
        stud.setGroup(defaultGroup);
    }

    /**
     * Deletes group
     * @param group
     */
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

    /**
     * Changes name, group and date of enrollment
     * @param oldName
     * @param newName
     * @param group
     * @param dateOfEnrollment
     */
    public void modifyStudentByName(String oldName, String newName, Group group, String dateOfEnrollment) {
        getStudentByName(oldName).setDateOfEnrollment(dateOfEnrollment);
        getStudentByName(oldName).setGroup(group);
        getStudentByName(oldName).setNameOfStudent(newName);
    }

    /**
     * Changes number and faculty of group
     * @param group
     * @param newNumberOfGroup
     * @param newFaculty
     */
    public void modifyGroup(Group group, int newNumberOfGroup, String newFaculty) {
        for (Student thisStudent : students) {
            if (thisStudent.getGroup().equals(group)) {
                thisStudent.getGroup().setNumberOfGroup(newNumberOfGroup);
                thisStudent.getGroup().setFaculty(newFaculty);
            }
        }
        for (Group thisGroup : groups) {
            if (thisGroup.equals(group)) {
                thisGroup.setNumberOfGroup(newNumberOfGroup);
                thisGroup.setFaculty(newFaculty);
            }
        }

    }

    /**
     * Shows info about all students from group
     * @param group
     * @return string
     */
    public String seeStudentsOfThisGroup(Group group) {
        StringBuilder strBuild = new StringBuilder();
        for (Student thisStudent : students) {
            if (thisStudent.getGroup().equals(group))
                strBuild.append(thisStudent.getNameOfStudent() + "\n");
        }
        return strBuild.toString();
    }

    //отсортированный по именам список студентов

    /**
     * Sorts the array
     * @return sorted array of students
     */
    public ArrayList<Student> getSortedListOfStudents() {
        /*for (int j = 0; j < students.size() - 1; j++) {
            int minIndex=j;
            for (int i = j + 1; i < students.size(); i++) {
                if (students.get(i).getNameOfStudent().compareToIgnoreCase(students.get(minIndex).getNameOfStudent()) < 0 )
                    minIndex = i;
            }
            Student swapPosition = students.get(minIndex);
            students.set(minIndex,students.get(j));
            students.set(j,swapPosition);
        }*/
        students.sort(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getNameOfStudent().compareToIgnoreCase(o2.getNameOfStudent());
            }
        });
        return students;
    }
}
