package application;

import endities.Department;
import endities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.ArrayList;
import java.util.List;


public class Program {

    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.creatSellerDao();

        System.out.println("==== Test 1: seller findByID ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println("==== Test 2: seller findByID ====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);
    }
}
