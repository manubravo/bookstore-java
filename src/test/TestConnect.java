package test;

import java.sql.SQLException;
import java.util.List;

import controller.DAOController;
import controller.Library;
import model.Connect;
import model.Book;
import security.Constant;

public class TestConnect {
	public static void main(String[] args) throws SQLException {
		Connect connect = null;
		try {
			connect = new Connect("db_bookstore_java", Constant.USER, Constant.PASSWORD);
			connect.on();
			
			Library b = new Library();
			b.addBook(new Book("DFG", "blablablabla", 2));
			b.addBook(new Book("XFG", "blablablabla", 1));
			b.addBook(new Book("TFG", "blablablabla", 1));
			b.addBook(new Book("ZFG", "blablablabla"));
			b.addBook(new Book("ABG", "blablablabla"));
			b.addBook(new Book("BFG", "blablablabla", 2));
			b.addBook(new Book("CFG", "blablablabla", 2));
			
			Book libroPrestado = new Book("AAA", "bsjaglñwa"); // prueba para testear dao
			libroPrestado.setBorrowed(true);
			
			Book libroPrestado2 = new Book("BBB", "bsjaglñwa"); // prueba para testear dao
			libroPrestado2.setBorrowed(true);
			
			Book libroPrestado3 = new Book("CCC", "bsjaglñwa"); // prueba para testear dao
			libroPrestado3.setBorrowed(true);
			
			b.addBook(libroPrestado);
			b.addBook(libroPrestado2);
			b.addBook(libroPrestado3);
			
			List<Book> listaVolatil = b.getAllBooks(); 
			for (Book itera : listaVolatil) {
				System.out.println(itera);
			}

			b.saveBookTxt("libros.txt");
			b.saveBooks("biblioteca.ser");
			
			Book unLibro = new Book("Test", "Aniadiendo libro");
			b.saveOneBook("biblioteca.ser", unLibro);
			
			List<Book> listaSer = b.readBooks("biblioteca.ser");
			for (Book itera : listaSer) {
				System.out.println(itera);
			}
			
			DAOController dao = new DAOController(connect.on());
//			dao.insertar(libroPrueba);
//			dao.eliminar(107);
			Integer totalSi = dao.totalBorrowed(); // FUNCIONA, TENGO SOLO 3 PRESTADOs
			System.out.println("Total Borrowed:" + totalSi);

			List<Book> noPrestado = dao.getAllNotBorrowed();
			System.out.println("No funciona " + noPrestado );
			for (Book itera : noPrestado) {
				System.out.println(itera);
				System.out.println("Qué pasa");
			}
			List<Book> inyectaTest = b.readBooks("biblioteca.ser");
			for (Book itera : inyectaTest) {
				dao.addToDB(itera);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			connect.off();
		}
	}
}