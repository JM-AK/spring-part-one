package ru.geekbrains;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // INSERT
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//        ru.geekbrains.Product product = new ru.geekbrains.Product(null, "Some product 1", "Some description 1", new BigDecimal(14));
//        ru.geekbrains.Product product1 = new ru.geekbrains.Product(null, "iPad", "Super table", new BigDecimal(1400));
//        ru.geekbrains.Product product2 = new ru.geekbrains.Product(null, "iPhone", "Super mobile phone", new BigDecimal(900));
//        em.persist(product);
//        em.persist(product1);
//        em.persist(product2);
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT
//        EntityManager em = emFactory.createEntityManager();
//
//        ru.geekbrains.Product product = em.find(ru.geekbrains.Product.class, 1L);
//        System.out.println(product);
//
//        // HQL, JPQL
//        List<ru.geekbrains.Product> products = em.createQuery("from ru.geekbrains.Product", ru.geekbrains.Product.class)
//                .getResultList();
//        products.forEach(System.out::println);
//
//        List<ru.geekbrains.Product> products1 = em.createQuery("from ru.geekbrains.Product p where p.name = :name ", ru.geekbrains.Product.class)
//                .setParameter("name", "iPad")
//                .getResultList();
//        products1.forEach(System.out::println);

        // SQL
//        em.createNativeQuery("select * from products", ru.geekbrains.Product.class)
//                .getResultList();
//        products.forEach(System.out::println);

//        em.close();

        // UPDATE
//        EntityManager em = emFactory.createEntityManager();
//
//        ru.geekbrains.Product product = em.find(ru.geekbrains.Product.class, 1L);
//        System.out.println(product);
//
//        em.getTransaction().begin();
//        product.setName("Macbook pro");
//        em.getTransaction().commit();
//
//        em.close();

        // DELETE
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//        ru.geekbrains.Product product = em.find(ru.geekbrains.Product.class, 1L);
//        em.remove(product);
//        em.getTransaction().commit();
//
//        List<ru.geekbrains.Product> products = em.createQuery("from ru.geekbrains.Product", ru.geekbrains.Product.class)
//                .getResultList();
//        products.forEach(System.out::println);
//
//        em.close();

        // INSERT one-to-many
//        EntityManager em = emFactory.createEntityManager();
//
//        em.getTransaction().begin();
//
//        em.persist(new ru.geekbrains.Category(null, "Laptop"));
//        em.persist(new ru.geekbrains.Category(null, "Phone"));
//        em.persist(new ru.geekbrains.Category(null, "Tablet"));
//
//        ru.geekbrains.Category laptop = em.createNamedQuery("findByName", ru.geekbrains.Category.class)
//                .setParameter("name", "Laptop")
//                .getSingleResult();
//        ru.geekbrains.Category phone = em.createNamedQuery("findByName", ru.geekbrains.Category.class)
//                .setParameter("name", "Phone")
//                .getSingleResult();
//        ru.geekbrains.Category tablet = em.createNamedQuery("findByName", ru.geekbrains.Category.class)
//                .setParameter("name", "Tablet")
//                .getSingleResult();
//
//        em.persist(new ru.geekbrains.Product(null, "Macbook pro", "Super laptop", new BigDecimal(3000), laptop));
//        em.persist(new ru.geekbrains.Product(null, "iPad", "Super tablet", new BigDecimal(3000), tablet));
//        em.persist(new ru.geekbrains.Product(null, "iPhone", "Super phone", new BigDecimal(3000), phone));
//
//        em.getTransaction().commit();
//
//        em.close();

        // SELECT one-to-many
        EntityManager em = emFactory.createEntityManager();

        List<Product> products = em.createQuery("select p from Product p inner join p.category c", Product.class)
                .getResultList();
        products.forEach(System.out::println);
    }
}
