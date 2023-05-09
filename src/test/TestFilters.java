package test;

import java.util.List;

import controller.Library;
import error.BookThrow;
import model.Book;

public class TestFilters {
	public static void main(String[] args) throws BookThrow {
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
		System.out.println("-------------");
		
		b.bookId(101);

		System.out.println("-------------");
		b.status(10);
		
		System.out.println("-------------");
		List<Book> listaPrestados = b.getAllBooksBorrowed();
		for (Book itera : listaPrestados) {
			System.out.println(itera);
		}
		System.out.println("-------------");
		b.creaXML("libreria");
	}
}