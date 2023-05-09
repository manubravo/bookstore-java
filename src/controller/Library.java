package controller;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import model.Book;
import security.CompareTitleDescendently;
import security.MyOOS;

public class Library {

	private String name;
	private String location;
	private List<Book> bookList;

	public Library() {
		this.name = null;
		this.location = null;
		this.bookList = new ArrayList<Book>();
	}

	public Library(String name, String location) {
		this.name = name;
		this.location = location;
		this.bookList = new ArrayList<Book>();
	}

	public List<Book> getAllBooks() {
		return bookList;
	}

	public void addBook(Book oneBook) {
		this.bookList.add(oneBook);
	}

	public void orderByDescendently() {
		Collections.sort(this.bookList, new CompareTitleDescendently());
	}

	public void orderByAscendently() {
		Collections.sort(this.bookList); // NATURAL ORDER
	}

	public void bookId(Integer id) {
		boolean found = false;
		for (Book iterate : bookList) {
			if (iterate.getOrder().compareTo(id) == 0) {
				found = true;
				System.out.println("ID: " + id + "--> Book found: " + iterate);
				break;
			}
		}
		if (!found) {
			System.out.println("ID: " + id + "--> Book dont found.");
		}
	}

	public void status(Integer id) {
		boolean found = false;
		if (found) {
			for (Book itera : bookList) {
				bookId(id);
				System.out.println("Status: " + itera.isBorrowed());
				break;
			}
		} else if (!found) {
			System.out.println("Dont exists book with ID: " + id + ".");
		}
	}

	public List<Book> getAllBooksBorrowed() {
		List<Book> listBorrowed = new ArrayList<Book>();
		for (Book iteration : this.bookList) {
			if (iteration.isBorrowed() == true) {
				listBorrowed.add(iteration);
			}
		}
		Collections.sort(listBorrowed, new CompareTitleDescendently());
		return listBorrowed;
	}
/*	public List<Integer> cuentaLibros(Integer tipoLibro) {
		List<Integer> listaTipoLibro = new ArrayList<Integer>();
		for (Libro itera : this.listaLibros) {
			if (tipoLibro == Constantes.NOVELA) {
				if (itera.getTipoLibro().compareTo(tipoLibro) == 0) {	
					this.listaLibros.add(itera);
				}
			} else if (tipoLibro == Constantes.RELATOS) {
			} else if (tipoLibro == Constantes.POESIA) {
			}
		}
	} */
	public void saveBooks(String nameFile) {
		System.out.println("Creating seralized file...");
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nameFile))) {
			for (Book itera : bookList) {
				oos.writeObject(itera);
			}
			System.out.println("You have created a seralized file!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void saveOneBook(String nameFile, Book oneBook) {
		// Use MyOOS to avoid header problems.
		try (MyOOS oos = new MyOOS(new FileOutputStream(nameFile, true))) {
			oos.writeObject(oneBook);
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public void saveBookTxt(String nameFile) {
		System.out.println("Creating txt file...");
		try (PrintWriter pw = new PrintWriter(new File(nameFile))) {
			for (Book itera : bookList) {
				pw.print(itera + "\n");
			}
			System.out.println("You have created a txt file!");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Book> readBooks(String nameFile) {
		System.out.println("Reading seralized file:");
		List<Book> listBook = new ArrayList<Book>();
		Book aux = null;
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nameFile))) {
			while (true) {
				aux = (Book) ois.readObject();
				listBook.add(aux);
			}
		} catch (EOFException e) {
			// Nothing to do
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listBook;
	}
	
	public void creaXML(String nombreFichero) {
		DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder construye = fabrica.newDocumentBuilder();
			DOMImplementation implementa = construye.getDOMImplementation();
			Document documenta = implementa.createDocument(null, nombreFichero, null);
			documenta.setXmlVersion("1.0");
			// ROOT NODE
			Element nodoRaiz = documenta.getDocumentElement();
			for (Book itera : this.bookList) {
				Element nodoObjeto = documenta.createElement("libro");
				Element nodoPropiedad = documenta.createElement("toString");
				Text valorNodo = documenta.createTextNode(itera.toString());
				nodoPropiedad.appendChild(valorNodo);
				nodoObjeto.appendChild(nodoPropiedad);
				nodoRaiz.appendChild(nodoObjeto);
			}
			//GENERA XML
			Source fuenteOrigen = new DOMSource(documenta);
			//DONDE SE GUARDARA
			Result fuenteDestino = new StreamResult(nombreFichero + ".xml");
			Transformer tranforma = TransformerFactory.newInstance().newTransformer();
			tranforma.transform(fuenteOrigen, fuenteDestino);
			System.out.println("XML file created!");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
}
