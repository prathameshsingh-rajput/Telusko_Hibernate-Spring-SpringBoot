package org.psr;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Student student1 = (Student) context.getBean("student");
        Student student2 = (Student) context.getBean("student");
        System.out.println("Are student and student2 the same? " + (student1 == student2));

        Subject subject1 = (Subject) context.getBean("subject");
        Subject subject2 = (Subject) context.getBean("subject");
        System.out.println("Are subject1 and subject2 the same? " + (subject1 == subject2));

        System.out.println("\nStudent Name: " + student1.getName());
    }
}
