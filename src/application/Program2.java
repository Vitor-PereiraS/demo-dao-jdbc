package application;

import endities.Department;
import model.dao.DaoFactory;
import model.dao.DepartmentDao;

import java.util.List;

public class Program2 {
    public static void main(String[] args) {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("==== Test 1: Department findByID ====");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println();
        System.out.println("==== Test 2: Department findAll ====");
        List<Department> list = departmentDao.findAll();
        list.forEach(System.out::println);

        System.out.println();
        System.out.println("==== Test 3: Department Insert ====");
        Department newDepartment = new Department(null, "RH");
        departmentDao.insert(newDepartment);
        System.out.println("Departamento criado com sucesso id = " + newDepartment.getId());

        System.out.println();
        System.out.println("==== Test 4: Department Delete ====");
        departmentDao.deleteById(7);
        System.out.println("DELETADO!");


        System.out.println();
        System.out.println("==== Test 5: Department UPDATE ====");
        department.setName("Esporte");
        departmentDao.update(department);
        System.out.println("departamento Atualizado! novo nome é " + department.getName());
    }
}
