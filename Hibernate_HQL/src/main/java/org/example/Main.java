package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Car.class)
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        // SELECT * FROM Car; -> SQL
        // FROM CAR; -> HQL
        Query<Car> query = session.createQuery("FROM Car", Car.class);
        List<Car> carList = query.getResultList();
        for (Car car : carList) {
            System.out.println(car);
        }

        // SELECT * FROM Car WHERE price >= 55000;  -> SQL
        // FROM CAR WHERE price >= 55000; -> HQL
        Query<Car> expensiveCarsQuery = session.createQuery("FROM Car WHERE price >= 55000", Car.class);
        List<Car> expensiveCars = expensiveCarsQuery.getResultList();
        for (Car car : expensiveCars) {
            System.out.println(car);
        }

        //SELECT * FROM Car WHERE brand = 'Tata' AND model = 'nano';  -> SQL
        //FROM Car WHERE brand = 'Tata' AND model = 'nano'; -> HQL

        String brand = "Tata";
        String model = "nano";
        Query<Car> specificCarQuery = session.createQuery("FROM Car WHERE brand = :brand AND model = :model", Car.class);
        specificCarQuery.setParameter("brand", brand);
        specificCarQuery.setParameter("model", model);
        List<Car> specificCars = specificCarQuery.getResultList();
        for (Car car : specificCars) {
            System.out.println(car);
        }

        //Or you can use indexed parameters
        Query<Car> indexedCarQuery = session.createQuery("FROM Car WHERE brand = ?1 AND model = ?2", Car.class);
        indexedCarQuery.setParameter(1, "Tata");
        indexedCarQuery.setParameter(2, "Xu");
        List<Car> indexedCars = indexedCarQuery.getResultList();
        for (Car car : indexedCars) {
            System.out.println(car);
        }

        Query<String> onlyModelQuery = session.createQuery("SELECT model FROM Car WHERE brand = ?1");
        onlyModelQuery.setParameter(1, "Tata");
        List<String> onlyModels = onlyModelQuery.getResultList();
        for (String modelName : onlyModels) {
            System.out.println(modelName);
        }

        // Update a car's price
        // UPDATE Car SET price = 60000 WHERE id = 1111; -> SQL
        // UPDATE Car SET price = 60000 WHERE id = 1111; -> HQL
        Transaction transaction = session.beginTransaction();
        Query updatePriceQuery = session.createQuery("UPDATE Car SET price = 60000 WHERE id = 1111");
        updatePriceQuery.executeUpdate();
        transaction.commit();

        // Delete a car
        // DELETE FROM Car WHERE id = 1111; -> SQL
        // DELETE FROM Car WHERE id = 1111; -> HQL
        Transaction deleteTransaction = session.beginTransaction();
        Query deleteCarQuery = session.createQuery("DELETE FROM Car WHERE id = 2222");
        deleteCarQuery.executeUpdate();
        deleteTransaction.commit();


        session.close();
        sessionFactory.close();


        System.out.println("Car saved successfully!");

    }
}