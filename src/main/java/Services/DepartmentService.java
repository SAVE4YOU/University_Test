package Services;

import DaoImpl.DepartmentDaoImpl;
import Domain.Department;

public class DepartmentService {
    private DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();

    public void save(Department department) {
        departmentDao.save(department);
    }

    public void update(Department department) {
        departmentDao.update(department);
    }

    public Department findById(Long id) {
        return departmentDao.findById(id);
    }

    public Department findByName(String name) {
        return departmentDao.findByName(name);
    }

    public int getAvgSalaryByDepartmentName(String name) {
        return departmentDao.getAvgSalaryByDepartmentName(name);
    }

    public int getCountOfEmployeeByDepartmentName(String name) {
        return departmentDao.getCountOfEmployeeByDepartmentName(name);
    }

    public String showStatisticOfDepartment(String name) {
        return departmentDao.showStatisticOfDepartment(name);
    }
}
