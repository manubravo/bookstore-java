package model;

import java.io.Serializable;

import error.BookThrow;
import security.Constant;
import security.Order;

public class Book implements Order, Serializable{	
	private static final long serialVersionUID = 1L;
	// privates attributes
	private String title;
	private String description;
	private boolean borrowed;
	// public attribute
	public Integer type;
	// static attribute
	public static int count;
	private Integer id; // Start from 100 and increment.
	// Constructor by default.
	public Book() {	} 
	// Constructor with every parameters.
	public Book(String title, String description, Integer type) throws BookThrow {
		this.setTitle(title);
		this.setDescription(description);
		this.setBorrowed(borrowed); // False by default.
		this.setType(type);;
		this.id = 100 + count++;
	}
	// Constructor with title and description.
	public Book(String title, String description) throws BookThrow{
		this(title, description, Constant.POETRY);
	}

	private void setTitle(String title) throws BookThrow{
		if (title == null) {
			throw new BookThrow("Title can't be null");
		}
		this.title = title;
	}

	private void setDescription(String description) throws BookThrow {
		if (description == null) {
			throw new BookThrow("Description can't be null");
		}
		this.description = description;
	}

	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}

	private void setType(Integer type) throws BookThrow {
		
		if (type == null) {
			throw new BookThrow("Type of Book can't be null");
		} else {
			switch (type) {
			case Constant.NOVEL:
			case Constant.STORY:
			case Constant.POETRY:
				this.type = type;
				break;
			default:
				throw new BookThrow("You must choose a valid type of book (1-3)");
			}
		}
	}
	
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public boolean isBorrowed() {
		return borrowed;
	}
	public Integer getType() {
		return type;
	}
	public static int getCount() {
		return count;
	}
	@Override
	public Integer getOrder() {	
		return this.id;
	}
	@Override
	public int compareTo(Book parametro) { // NATURAL ORDER	
		if (this.getOrder().compareTo(parametro.getOrder()) == 0) {
			return -1;
		} else if (this.getOrder().compareTo(parametro.getOrder()) == 0) {
			return 0;
		}
		return 1;
	}
	@Override
	public String toString() {
		return getTitle() + ", " + getDescription() + ", "
				+ isBorrowed() + ", " + getType() + ", " + getOrder();
	}
}