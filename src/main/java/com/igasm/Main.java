package com.igasm;

import com.igasm.pojo.Message;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class Main {

  public static void main(String[] args) {
//    create(1, "Witaj świecie");
//    create(2, "Witaj JPA");
//    create(3, "Witaj Hiberze");

    create("Witaj świecie");
    create("Witaj JPA");
    create("Witaj Hiberze");
    readAll();
  }

  private static void readAll() {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("utrwalacz");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
//    Query query = em.createNativeQuery("SELECT id, text FROM content;", Message.class);
//    List<Message> messages = query.getResultList();
//    messages.forEach(message -> System.out.println(message.toString()));

    em.createNativeQuery("SELECT id, text FROM content;", Message.class).getResultList().forEach(System.out::println);
//    em.createQuery("SELECT id, text FROM content", Message.class).getResultList().forEach(System.out::println);

    transaction.commit();
  }

  static void create(int id, String content){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("utrwalacz");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    Query query = em.createNativeQuery("INSERT INTO content (id, text) VALUES (?, ?)");
    query.setParameter(1, id);
    query.setParameter(2, content);
    query.executeUpdate();
    transaction.commit();

  }

  static void create(String content){
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("utrwalacz");
    EntityManager em = emf.createEntityManager();
    EntityTransaction transaction = em.getTransaction();
    transaction.begin();
    em.persist(new Message(content));
    transaction.commit();
  }

}
