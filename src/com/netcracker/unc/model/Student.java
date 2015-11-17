package com.netcracker.unc.model;

import java.io.Serializable;

public class Student implements Serializable {

    private String nameOfStudent;
    private Group group;
    private String dateOfEnrollment;

    public Student(String nameOfStudent, Group group, String dateOfEnrollment) {
        this.nameOfStudent = nameOfStudent;
        this.group = group;
        this.dateOfEnrollment = dateOfEnrollment;
    }

    public String getNameOfStudent() {
        return nameOfStudent;
    }

    public void setNameOfStudent(String nameOfStudent) {
        this.nameOfStudent = nameOfStudent;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getDateOfEnrollment() {
        return dateOfEnrollment;
    }

    public void setDateOfEnrollment(String dateOfEnrollment) {
        this.dateOfEnrollment = dateOfEnrollment;
    }

    @Override
    public String toString() {
        StringBuilder StrBuild = new StringBuilder();
        return StrBuild.append("Студент " + this.getNameOfStudent())
                .append(" обучается в группе под номером " + this.group.getNumberOfGroup())
                .append(" на факультете " + this.group.getFaculty())
                .append(". Зачислен на учебу " + this.dateOfEnrollment + ".")
                .toString();
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (this.getClass() == object.getClass()) {
            Student obj = (Student) object;
            if ((this.getNameOfStudent().compareToIgnoreCase(obj.getNameOfStudent()) == 0) && (this.getDateOfEnrollment() == obj.getDateOfEnrollment()))
                return true;
        }
        return false;
    }
}