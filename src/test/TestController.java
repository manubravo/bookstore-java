package test;

import java.sql.SQLException;

import controller.BookStore;
import model.Connect;
import security.Constant;

public class TestController {
    public static void main(String[] args) throws SQLException {
        Connect c = null;
        try {
            System.out.println("Testing controllers...");
            c = new Connect(Constant.USER, Constant.PASSWORD);
            BookStore dao = new BookStore(c.on());
            System.out.println("Total borrowed: " + dao.totalBorrowed());
            System.out.println("Total not borrowed: " + dao.getAllNotBorrowed());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            c.off();
        }
    }
}
