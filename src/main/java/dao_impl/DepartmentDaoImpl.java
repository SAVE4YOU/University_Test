package dao_impl;

import dao.DepartmentDao;
import domains.Degree;
import domains.Department;
import domains.Lector;
import utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;

public class DepartmentDaoImpl implements DepartmentDao {

    public void save(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(department);
        tx.commit();
        session.close();
    }

    public void delete(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(department);
        tx.commit();
        session.close();
    }

    public void update(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(department);
        tx.commit();
        session.close();
    }

    public Department findById(Long id) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Department department =  session.get(Department.class, id);
        session.close();
        return department;
    }

    public Department findByName(String name) {
        Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From Department where name =:name");
        query.setParameter("name", name);
        Department department = (Department) query.uniqueResult();
        session.close();
        return department;
    }

    public int getAvgSalaryByDepartmentName(String name) {
        Department department = findByName(name);
        int allSalary = department.getLectors().stream().mapToInt(Lector::getSalary).sum();
        int sum = department.getLectors().size() == 0?1:department.getLectors().size();
        int avgSalary = allSalary/sum;
        return avgSalary;
    }

    public int getCountOfEmployeeByDepartmentName(String name) {
        Department department = findByName(name);
        return department.getLectors().size();
    }

    public String showStatisticOfDepartment(String name) {
        Department department = findByName(name);
        long assistant = department.getLectors().stream().filter(lector -> lector.getDegree().equals(Collections.singleton(Degree.assistant))).count();
        long associateProfessor = department.getLectors().stream().filter(lector -> lector.getDegree().equals(Collections.singleton(Degree.associate_proffesor))).count();
        long professors = department.getLectors().stream().filter(lector -> lector.getDegree().equals(Collections.singleton(Degree.proffesor))).count();
        return "Assistants - " + assistant + "\nAssociateProfessors - " + associateProfessor + "\nProfessors - " + professors;
    }
}