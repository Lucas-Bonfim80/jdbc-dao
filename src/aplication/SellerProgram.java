package aplication;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class SellerProgram {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        Scanner sc = new Scanner(System.in);

        System.out.println("=== SELLER findById ===");
        model.entities.Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n === SELLER findByIdDepartment ===");

        Department department = new Department(4, null);
        List<model.entities.Seller> list = sellerDao.findByDepartment(department);
        for (model.entities.Seller obj : list) {
            System.out.println(obj);
        }

        System.out.println("\n === SELLER findAll ===");
        list = sellerDao.findAll();
        for (model.entities.Seller obj : list) {
            System.out.println(obj);
        }


        System.out.println("\n === SELLER insert ===");
        model.entities.Seller newSeller = new model.entities.Seller( null, "Martha Red","martha@gmail.com", new Date() ,3000.0, department);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! New id = " + newSeller.getId());


        System.out.println("\n === SELLER insert ===");
        seller = sellerDao.findById(10);
        seller.setName("Rodrigo Alberto");
        seller.setEmail("rodrigoalberto@gmail.com");
        sellerDao.update(seller);
        System.out.println("Update completed!");

        System.out.println("\n === SELLER insert ===");
        System.out.print("Enter Id for delete: ");
        int id = sc.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Delete completed!");
    }
}