package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        Student student = new Student();
        Address address = new Address();

        System.out.println("-----------Menu-----------");
        System.out.println("1. Insert Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. Get Student");
        System.out.println("5. Exit");
        int choice = 0;

        do{
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: System.out.println("Enter RollNo, Name and Marks: ");
                        student.setRollNumber(sc.nextInt());
                        student.setName(sc.next());
                        student.setMarks(sc.nextDouble());
                        System.out.println("Enter Address details (home_name, street, area, city, pincode): ");
                        address.setHomeName(sc.next());
                        address.setStreet(sc.next());
                        address.setArea(sc.next());
                        address.setCity(sc.next());
                        address.setPinCode(sc.nextInt());

                        student.setAddress(address);

                        main.insertStudent(student);
                        break;

                case 2: System.out.println("Enter RollNo to update: ");
                        student.setRollNumber(sc.nextInt());
                        System.out.println("Enter new Name and Marks: ");
                        student.setName(sc.next());
                        student.setMarks(sc.nextDouble());
                        main.updateStudent(student);
                        break;

                case 3: System.out.println("Enter roll number to delete student: ");
                        student.setRollNumber(sc.nextInt());
                        main.deleteStudent(student);
                        break;

                case 4: System.out.println("Enter roll number to retrieve student: ");
                        student.setRollNumber(sc.nextInt());
                        main.getStudent(student);
                        break;

                case 5: System.out.println("Exiting...");
                        break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }while(choice != 5);

    }

    public void insertStudent(Student student){
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(student);
        transaction.commit();
        session.close();
        sessionFactory.close();

    }

    public void getStudent(Student student) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Student retrievedStudent = session.get(Student.class, student.getRollNumber());

        if (retrievedStudent != null) {
            System.out.println("Retrieved Student: " + retrievedStudent);
        } else {
            System.out.println("No student found with Roll No: " + student.getRollNumber());
        }

        session.close();
        sessionFactory.close();
    }

    public  void updateStudent(Student student) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(student);
        transaction.commit();

        session.close();
        sessionFactory.close();
    }

    public void deleteStudent(Student student) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student studentToDelete = session.get(Student.class, student.getRollNumber());

        if (studentToDelete != null) {
            session.remove(studentToDelete);
            System.out.println("Deleted Student: " + studentToDelete);
        } else {
            System.out.println("No student found with Roll No: " + student.getRollNumber());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}