package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.DAOException;
import by.htp.library.dao.DAOFactory;

public class LibraryServiceImpl implements LibraryService {

	@Override
	public void addNewBook(Book book) throws ServiceException {
		// input data check
		if ((book == null) || (book.getName()== null )|| (book.getAge()==null)
				|| (book.getAuthor()==null)|| (book.getName()== "" )|| (book.getAge()=="")
				|| (book.getAuthor()=="")) {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.addBook(book);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Book delBook(int id) throws ServiceException {
		// TODO Auto-generated method stub
		if (id == 0)  {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			return bookDAO.deleteBook(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
			
		}
		
	}

	@Override
	public ArrayList<Book> fndBook(String name) throws ServiceException {
		// TODO Auto-generated method stub
		ArrayList<Book> foundbooks = null;
		if ((name == null)||(name=="")) {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			foundbooks = bookDAO.findBook(name);

		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return foundbooks;

	}

	@Override
	public void addEditedBook(Book oldBook, Book newBook) throws ServiceException {
		// TODO Auto-generated method stub
		if (oldBook == null) {
			throw new ServiceException("Incorrect input data");
		}
		try {
			DAOFactory daoObjectFactory = DAOFactory.getInstance();
			BookDAO bookDAO = daoObjectFactory.getBookDAO();
			bookDAO.addEditedBook(oldBook, newBook);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
