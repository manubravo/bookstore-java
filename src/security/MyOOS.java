package security;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MyOOS extends ObjectOutputStream {
	/*
	 * Support class for serialization
	 */
	public MyOOS() throws IOException, SecurityException {
	// Constructor by default
	}
	public MyOOS(OutputStream arg0) throws IOException {
		super(arg0);
	}
	@Override
	protected void writeStreamHeader() {
	// nothing to do
	}
}