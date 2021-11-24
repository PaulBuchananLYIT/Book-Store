package ie.lyit.serialize;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import ie.lyit.book.Book;

public class BookSerializer {
	
	private ArrayList<Book> books;
	final String FILENAME = "books.ser";
	
	public BookSerializer()
	{
		 books = new ArrayList<Book>();
	}
	
	public void add()
	{
		//create book object
		Book book = new Book();
		//call its read method
		book.read();
		//adds the book object to the array list
		books.add(book);
	}
	
	public void list()
	{
		for(Book tmpBook:books)
		{
			System.out.println(tmpBook);
		}
	}
	
	public Book view()
	{
		Scanner keyboard = new Scanner(System.in);
		
		System.out.println("ENTER LIBARY NUMBER OF BOOK :");
		int bookToView = keyboard.nextInt();
		
		for(Book tmpBook : books)
		{
			if(tmpBook.getLibraryNumber() == bookToView)
			{
				System.out.println(tmpBook);
				return tmpBook;
			}
		}
		return null;
	}
	
	public void delete()
	{
		Book bookToDelete = view();
		
		if(bookToDelete != null)
		{
			books.remove(bookToDelete);
		}
		else
		{
			System.out.println("book not found");
		}
	}
	
	public void edit()
	{
		Book bookToEdit = view();
		
		if(bookToEdit != null)
		{
			//get the index
			int index = books.indexOf(bookToEdit);
			//read in new book details
			bookToEdit.read();
			//reset it in the array list
			books.set(index, bookToEdit);
		}
		else
		{
			System.out.println("book not found");
		}
	}
	
	//Serializer the books array list
	public void serializerBooks()
	{
		try
		{
			FileOutputStream fileStream = new FileOutputStream(FILENAME);
			
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			
			os.writeObject(books);
			
			os.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("cannot create file to store books.");
		}
		catch(IOException ioE)
		{
			System.out.println(ioE.getMessage());
		}
		
	}
	
	public void deserializeBooks()
	{
		try
		{
			FileInputStream fileStream = new FileInputStream(FILENAME);
			
			ObjectInputStream is = new ObjectInputStream(fileStream);
			
			books = (ArrayList<Book>)is.readObject();
			//set static here that it doesnt start as 1000 again
			
			is.close();
		}
		catch(FileNotFoundException fnfe)
		{
			System.out.println("cannot create file to store books.");
		}
		catch(IOException ioE)
		{
			System.out.println(ioE.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		
	}

}
