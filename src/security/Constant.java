package security;

public interface Constant {
	
	int NOVEL = 1;
	int STORY = 2;
	int POETRY = 3;
	
	String DRIVER ="com.mysql.jdbc.Driver";
	String URL = "jdbc:mysql://localhost:3306/";
	String DATABASE = "db_bookstore_java";
	
	String USER = "root";
	String PASSWORD = "";
	
	String BOOK_TABLE = "CREATE TABLE `BOOK` ( `ID` INT NOT NULL , `TITLE` VARCHAR(60) NOT NULL , `DESCRIPTION` VARCHAR(120) NOT NULL , `BORROWED` BOOLEAN NOT NULL , `TYPE` INT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;";

	String BOOK_ADD = "INSERT INTO `BOOK`(`ID`, `TITLE`, `DESCRIPTION`, `BORROWED`, `TYPE`) "
			+ "VALUES ( ? , ? , ? , ? , ? )";
	
	String BOOK_DELETE = "DELETE FROM `BOOK` WHERE `ID`= ? ";
	
	String BOOK_GET_ALL = "SELECT * FROM BOOK";
	
	String BOOK_GET_ALL_NOT_BORROWED = "SELECT * FROM BOOK WHERE BORROWED = 0";
	
	String BOOK_GET_ALL_BORROWED = "SELECT * FROM BOOK WHERE BORROWED = 1";
}