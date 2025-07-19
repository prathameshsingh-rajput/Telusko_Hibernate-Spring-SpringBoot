package org.example;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Student")
public class Student {
    @Id
    private  int rollNo;
    @Column(name = "name")
    private String name;
    private double marks;
    @OneToOne(cascade = CascadeType.ALL)
    private Laptop laptop;
    @OneToMany (mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Subject> subjects;


    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
