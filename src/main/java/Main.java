import domains.Department;
import domains.Lector;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import services.DepartmentService;
import services.LectorService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        DepartmentService departmentService = new DepartmentService();
        LectorService lectorService = new LectorService();

        Logger.getRootLogger().setLevel(Level.OFF);

        System.out.println("1 - Who is head of department {name}");
        System.out.println("2 - Show {department_name} statistic");
        System.out.println("3 - Show the average salary for department {department_name}");
        System.out.println("4 - Show count of employee for {department_name}");
        System.out.println("5 - Global search by {template}");
        System.out.println("0 - Exit");

        String command = "";
        while (!command.equals("0")) {
            String message, name;
            System.out.println("command:");
            Scanner sc = new Scanner(System.in);
                command = sc.next();
            switch (command) {
                case ("1"):
                    System.out.println("Enter a name of department:");
                    name = sc.next();
                    message = departmentService.findByName(name) == null ? "Department does not exists" :
                            "Head of " + departmentService.findByName(name).getName() +
                                    " department is " + departmentService.findByName(name).getHead().getName() +
                                    " " + departmentService.findByName(name).getHead().getLastName();
                    System.out.println(message);
                    break;
                case ("2"):
                    System.out.println("Enter a name of department:");
                    name = sc.next();
                    message = departmentService.findByName(name) == null ? "Department does not exists" :
                            departmentService.showStatisticOfDepartment(name);
                    System.out.println(message);
                    break;
                case ("3"):
                    System.out.println("Enter a name of department:");
                    name = sc.next();
                    message = departmentService.findByName(name) == null ? "Department does not exists" :
                            Integer.toString(departmentService.getAvgSalaryByDepartmentName(name));
                    System.out.println(message);
                    break;
                case ("4"):
                    System.out.println("Enter a name of department:");
                    name = sc.next();
                    message = departmentService.findByName(name) == null ? "Department does not exists" :
                            Integer.toString(departmentService.getCountOfEmployeeByDepartmentName(name));
                    System.out.println(message);
                    break;
                case ("5"):
                    System.out.println("Enter a template:");
                    name = sc.next();
                    List<Lector> lectors = lectorService.globalSearch(name);
                    for (Lector lector1 : lectors) {
                        System.out.print("(Id =" + lector1.getId() + ", name = " + lector1.getName() + ", last name = " + lector1.getLastName() + ").\n");
                    }
                    if (lectorService.globalSearch(name).isEmpty())
                        System.out.println("Not found");
                    break;
                case ("0"):
                    System.out.println("Process finished!");
                    break;
                default:
                    System.out.println("Try once more");
                    break;
            }
        }
    }
}
