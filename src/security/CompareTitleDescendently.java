package security;

import java.util.Comparator;

import model.Book;

public class CompareTitleDescendently implements Comparator<Book> {

	@Override
	public int compare(Book libro1, Book libro2) {
		
		return -(libro1.getTitle().compareTo(libro2.getTitle()));
	}

}
