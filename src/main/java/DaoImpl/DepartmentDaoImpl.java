package DaoImpl;

import Dao.DepartmentDao;
import Domain.Degree;
import Domain.Department;
import Domain.Lector;
import Utils.HibernateSessionFactoryUtil;
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

    public void update(Department department) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.merge(department);
        tx.commit();
        session.close();
    }

    public Department findById(Long id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Department.class, id);
    }

    public Department findByName(String name) {
        Query query = HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("From Department where name =:name");
        query.setParameter("name", name);
        return (Department) query.uniqueResult();
    }

    public int getAvgSalaryByDepartmentName(String name) {
        Department department = findByName(name);
        int avgSalary = department.getLectors().stream().mapToInt(Lector::getSalary).sum();
        avgSalary = avgSalary / department.getLectors().size();
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