package com.netcracker.unc.model;

import java.io.Serializable;

public class Group implements Serializable {

    private int numberOfGroup;
    private String faculty;

    public Group(int number, String faculty) {
        this.numberOfGroup = number;
        this.faculty = faculty;
    }


    public int getNumberOfGroup() {
        return numberOfGroup;
    }

    public void setNumberOfGroup(int numberOfGroup) {
        this.numberOfGroup = numberOfGroup;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (this.getClass() == object.getClass()) {
            Group obj = (Group) object;
            if ((this.getNumberOfGroup() == obj.getNumberOfGroup()) && (this.getFaculty().compareToIgnoreCase(obj.getFaculty()) == 0))
                return true;
        }
        return false;
    }
}