package test;

import java.sql.SQLException;

import controller.BookStore;
import model.Connect;
import security.Constant;

public class TestInjection {
    public static void main(String[] args) throws SQLException {
        Connect c = null;
        try {
            System.out.println("Testing controllers...");
            c = new Connect("db_bookstore_injected",Constant.USER, Constant.PASSWORD);
            BookStore dao = new BookStore(c.on());
            dao.activeLibrary();
            dao.injectSerFileToDB("biblioteca.ser");
            System.out.println("Total borrowed: " + dao.totalBorrowed());
            System.out.println("Total not borrowed: " + dao.getAllNotBorrowed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.off();
        }
    }
}
