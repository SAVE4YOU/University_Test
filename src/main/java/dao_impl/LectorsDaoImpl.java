package dao_impl;

import dao.LectorsDao;
import domains.Lector;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LectorsDaoImpl implements LectorsDao {

    public void save(Lector lector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(lector);
        tx.commit();
        session.close();
    }

    public void delete(Lector lector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(lector);
        tx.commit();
        session.close();
    }

    public void update(Lector lector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(lector);
        tx.commit();
        session.close();
    }

    public Lector findById(Long id) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Lector lector =  session.get(Lector.class, id);
        session.close();
        return lector;
    }

    public List<Lector> findAll() {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        List<Lector> lectors = session.createQuery("From Lector").list();
        session.close();
        return lectors;
    }

    public List<Lector> globalSearch(String template) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Lector WHERE lastName LIKE ?1");
        query.setParameter(1, "%" + template + "%");
        List<Lector> lectors = (List<Lector>) query.list();
        query = session.createQuery("From Lector WHERE name LIKE ?1");
        query.setParameter(1, "%" + template + "%");
        List<Lector> lectors2 = (List<Lector>) query.list();
        List<Lector> result = new ArrayList<>(lectors);
        result.addAll(lectors2);
        Set<Lector> l = new HashSet<Lector>(result);
        result = new ArrayList<>(l);
        session.close();
        return result;
    }
}
