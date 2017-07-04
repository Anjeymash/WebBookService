package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceFactory;

public class Show implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// String response = null;
		// ServiceFactory serviceFactory = ServiceFactory.getInstance();
		// LibraryService libraryService = serviceFactory.getLibraryService();
		String bookName = null;
		String bookAuthor = null;
		String bookYear = null;
		String mess = null;
		mess = request.getParameter("Messege");
		bookName = request.getParameter("bookName");
		bookAuthor = request.getParameter("bookAuthor");
		bookYear = request.getParameter("bookYear");
		String page;
		Book book = new Book(bookName, bookAuthor, bookYear);
		request.setAttribute("book", book);
		request.setAttribute("Messege", mess);
		page = "show.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}
}
