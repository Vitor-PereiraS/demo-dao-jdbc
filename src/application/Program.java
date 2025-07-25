package application;

import endities.Seller;
import model.dao.DaoFactory;
import model.dao.SellerDao;


public class Program {

    public static void main(String[] args) {



        SellerDao sellerDao = DaoFactory.creatSellerDao();

        Seller seller = sellerDao.findById(3);

        System.out.println(seller);

    }
}
