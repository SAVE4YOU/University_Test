package DaoImpl;

import Dao.LectorsDao;
import Domain.Lector;
import Utils.HibernateSessionFactoryUtil;
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

    public void update(Lector lector) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(lector);
        tx.commit();
        session.close();
    }

    public Lector findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Lector.class, id);
    }

    public List<Lector> findAll() {
        List<Lector> lectors = (List<Lector>) HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Lector").list();
        return lectors;
    }

    public List<Lector> globalSearch(String template) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Lector WHERE lastName LIKE ?1");
        Query query2 = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Lector WHERE name LIKE ?1");
        query.setParameter(1, "%" + template + "%");
        query2.setParameter(1, "%" + template + "%");
        List<Lector> lectors = (List<Lector>) query.list();
        List<Lector> lectors2 = (List<Lector>) query2.list();
        List<Lector> result = new ArrayList<>(lectors);
        result.addAll(lectors2);
        Set<Lector> l = new HashSet<Lector>(result);
        result = new ArrayList<>(l);
        return result;
    }
}
