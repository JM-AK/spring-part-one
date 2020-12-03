package ru.geekbrains;

import org.hibernate.cfg.Configuration;
import ru.geekbrains.entities.Category;
import ru.geekbrains.entities.Consumer;
import ru.geekbrains.entities.Order;
import ru.geekbrains.entities.Product;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.List;

public class DBService {

    private static EntityManagerFactory emFactory;

    private static final DBService instance = new DBService();

    public static DBService getInstance() {
        return instance;
    }

    public void init() {
        emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public void close() {
        emFactory.close();
    }

    public void addCategory(String...categoryTitle) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            for (int i = 0; i < categoryTitle.length; i++) {
                em.persist(new Category(null, categoryTitle[i]));
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DBServiceException("Add category fail");
        }
        finally {
            em.close();
        }
    }

    public void addProduct(String title, String description, String price, String category){
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Category categoryFound = em.createNamedQuery("findByTitle", Category.class).setParameter("title", category).getSingleResult();
            if (categoryFound != null) {
                em.persist(new Product(null, title, description, new BigDecimal(price), categoryFound));
                em.getTransaction().commit();
            } else {
                System.out.println("Category doesn't exists");
            }
        } catch (Exception e) {
            throw new DBServiceException("Add product fail");
        } finally {
            em.close();
        }
    }

    public void addConsumer(String...consumerData) {
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(new Consumer(null, consumerData[0], consumerData[1], consumerData[2], consumerData[3]));
            em.getTransaction().commit();
        } catch (Exception e) {
            throw new DBServiceException("Add consumer fail");
        }
        finally {
            em.close();
        }
    }

    public void addOrder(String consumerID, String...productTitles){
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();
            Consumer consumer = em.find(Consumer.class, Long.parseLong(consumerID));
            List<Product> products = em.createQuery("select p from Product p where p.title in (:productTitles)", Product.class).
                    setParameter("productTitles", String.join(",", productTitles)).
                    getResultList();
            Order order = new Order(null, consumer, products);
            em.persist(order);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBServiceException("Add order fail");
        }
        finally {
            em.close();
        }
    }

    public void deleteProduct(String title){
        EntityManager em = emFactory.createEntityManager();
        try {
            em.getTransaction().begin();

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DBServiceException("Add product fail");
        } finally {
            em.close();
        }
    }

    public void deleteConsumer(String consumerID){

    }

    public void findOrders(Long consumerID) {

    }

    class DBServiceException extends RuntimeException{
        public DBServiceException(String message){
            super();
        }
    }

}
