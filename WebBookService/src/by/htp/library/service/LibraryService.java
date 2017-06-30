package by.htp.library.service;

import java.util.ArrayList;

import by.htp.library.bean.Book;

public interface LibraryService {
void addNewBook(Book book) throws ServiceException;
Book delBook(int bookID) throws ServiceException;
ArrayList<Book> fndBook(String name) throws ServiceException;
void addEditedBook(Book oldBook, Book newBook) throws ServiceException;

}
