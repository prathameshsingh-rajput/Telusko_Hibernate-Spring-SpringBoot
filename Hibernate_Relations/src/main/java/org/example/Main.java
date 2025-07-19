package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();
        Student student = new Student();
        Laptop laptop = new Laptop();

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
                case 1: System.out.println("Enter student details (RollNo, Name and Marks): ");
                        student.setRollNo(sc.nextInt());
                        student.setName(sc.next());
                        student.setMarks(sc.nextDouble());
                        System.out.println("Enter laptop details (laptopID, brandName, model and prize): ");
                        laptop.setLaptopId(sc.nextInt());
                        laptop.setBrandName(sc.next());
                        laptop.setModel(sc.next());
                        laptop.setPrice(sc.nextDouble());
                        System.out.println("How many subjects does the student have? ");
                        int subjectCount = sc.nextInt();
                        if (subjectCount <= 0) {
                            System.out.println("No subjects to add.");
                            break;
                        }
                        List<Subject> subjects = new ArrayList<>();
                        while (subjectCount > 0){
                            System.out.println("Enter subject details (subjectId, subjectName, credits): ");
                            Subject subject = new Subject();
                            subject.setSubjectId(sc.nextInt());
                            subject.setSubjectName(sc.next());
                            subject.setCredits(sc.nextInt());
                            subject.setStudent(student); // Set the student for the subject
                            subjects.add(subject);
                            subjectCount--;
                        }

                        student.setLaptop(laptop);
                        student.setSubjects(subjects);
                        main.insertStudent(student);
                        break;

                case 2: System.out.println("Enter RollNo to update: ");
                        student.setRollNo(sc.nextInt());
                        System.out.println("Enter new Name and Marks: ");
                        student.setName(sc.next());
                        student.setMarks(sc.nextDouble());
                        main.updateStudent(student);
                        break;

                case 3: System.out.println("Enter roll number to delete student: ");
                        student.setRollNo(sc.nextInt());
                        main.deleteStudent(student);
                        break;

                case 4: System.out.println("Enter roll number to retrieve student: ");
                        student.setRollNo(sc.nextInt());
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
                .addAnnotatedClass(Laptop.class)
                .addAnnotatedClass(Subject.class)
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
                .addAnnotatedClass(Laptop.class)
                .addAnnotatedClass(Subject.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Student retrievedStudent = session.get(Student.class, student.getRollNo());

        if (retrievedStudent != null) {
            System.out.println("Retrieved Student: " + retrievedStudent);
        } else {
            System.out.println("No student found with Roll No: " + student.getRollNo());
        }

        session.close();
        sessionFactory.close();
    }

    public  void updateStudent(Student student) {
        SessionFactory sessionFactory = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class)
                .addAnnotatedClass(Subject.class)
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
                .addAnnotatedClass(Laptop.class)
                .addAnnotatedClass(Subject.class)
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Student studentToDelete = session.get(Student.class, student.getRollNo());

        if (studentToDelete != null) {
            session.remove(studentToDelete);
            System.out.println("Deleted Student: " + studentToDelete);
        } else {
            System.out.println("No student found with Roll No: " + student.getRollNo());
        }

        transaction.commit();
        session.close();
        sessionFactory.close();
    }
}