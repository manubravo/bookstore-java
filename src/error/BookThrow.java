package error;

public class BookThrow extends Exception {
	private static final long serialVersionUID = 1L;

	public BookThrow() {
		super("Construction of Book failed");
	}
	public BookThrow(String message) {
		super(message);
	}
	public BookThrow(Throwable e) {
		super(e);
	}
	public BookThrow(String message, Throwable e) {
		super(message, e);
	}
}