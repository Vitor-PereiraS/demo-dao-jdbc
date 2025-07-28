package application;

import endities.Department;
import endities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;

import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Program {

    public static void main(String[] args) {

        Scanner leitura = new Scanner(System.in);
        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("==== Test 1: seller findByID ====");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println();
        System.out.println("==== Test 2: seller findByDepartment ====");
        Department department = new Department(2, null);
        List<Seller> list = sellerDao.findByDepartment(department);
        list.forEach(System.out::println);

        System.out.println();
        System.out.println("==== Test 3: seller findAll ====");
        list = sellerDao.findAll();
        list.forEach(System.out::println);

        System.out.println();
        System.out.println("==== Test 4: seller Insert ====");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("inserido!, novo id = =" + newSeller.getId());

        System.out.println();
        System.out.println("==== Test 5: seller Update====");
        seller = sellerDao.findById(1);
        seller.setName("Marta Waine");
        sellerDao.update(seller);
        System.out.println("Atualizado com sucesso");

        System.out.println();
        System.out.println("==== Test 6: seller delete ====");

        System.out.print("Digite o id que deseja excluir ");
        int id = leitura.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Apagado com sucesso");

        leitura.close();
    }
}
