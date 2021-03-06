package dao;

import domains.Department;

public interface DepartmentDao {
    void save(Department department);

    void update(Department department);

    Department findById(Long id);

    Department findByName(String name);

    int getAvgSalaryByDepartmentName(String name);

    int getCountOfEmployeeByDepartmentName(String name);

    String showStatisticOfDepartment(String name);

    void delete(Department department);
}
