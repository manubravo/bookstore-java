package security;

import model.Book;

public interface Order extends Comparable<Book>{
	
	Integer getOrder();
	
}
