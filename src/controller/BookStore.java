package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import error.BookThrow;
import model.Book;
import security.Constant;

public class BookStore {
	/*
	 * CRUD only INSERT and UPDATE
	 * Not is necessary DELETE_ALL or DELETE_ONE
	 */
	private Connection connect;
	private Library activeLibrary;
	public BookStore() {
		this.activeLibrary = null;
	}
	public BookStore(Connection connect) {
		this.connect = connect;
	}

	public Library activeLibrary() {
		if (activeLibrary == null) {
			this.activeLibrary = new Library();
		}
		return this.activeLibrary;
	}

	public void addToDB(Book a) {
		try (PreparedStatement ps = this.connect.prepareStatement(Constant.BOOK_ADD)) {
//			5 values
			ps.setInt(1, a.getOrder());
			ps.setString(2, a.getTitle());
			ps.setString(3, a.getDescription());
			ps.setBoolean(4, a.isBorrowed());
			ps.setInt(5, a.getType());
			ps.executeUpdate();
			System.out.println("You have added a book to the database.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteBookOfDB(Integer id) {
		try (PreparedStatement ps = this.connect.prepareStatement(Constant.BOOK_DELETE)) {
			ps.setInt(1, id);
			ps.executeUpdate();
			System.out.println("You have deleted a book from the database.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Book> getAllNotBorrowed() {
		List<Book> listNotBorrowed = new ArrayList<Book>();
		Book aux = null;
		try (PreparedStatement ps = this.connect.prepareStatement(Constant.BOOK_GET_ALL_NOT_BORROWED)) {
			try (ResultSet cursor = ps.executeQuery()) {
				while (cursor.next()) {
					aux = new Book(cursor.getString(2), cursor.getString(3), cursor.getInt(5));
					listNotBorrowed.add(aux);
				}
			} catch (BookThrow e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listNotBorrowed;
	}

	public Integer totalBorrowed() {
		List<Book> listBorrowed = new ArrayList<Book>();
		Book aux = null;
		try (PreparedStatement ps = this.connect.prepareStatement(Constant.BOOK_GET_ALL_BORROWED)) {
			try (ResultSet cursor = ps.executeQuery()) {
				while (cursor.next()) {
					aux = new Book(cursor.getString(2), cursor.getString(3), cursor.getInt(5));
					listBorrowed.add(aux);
				}
			} catch (BookThrow e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listBorrowed.size();
	}
	
	public void injectSerFileToDB(String nameFile) {
		List<Book> injection = this.activeLibrary.readBooks(nameFile);
		System.out.println("Injecting serialized file to DB...");
		for (Book itera : injection) {
			this.addToDB(itera);
		}
		System.out.println("You have injected the file to the database.");
	}
}