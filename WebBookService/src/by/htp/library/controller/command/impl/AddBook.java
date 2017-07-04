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

public class AddBook implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();

		String bookName = null;
		String bookAuthor = null;
		String bookYear = null;
		String role = null;
		int id;

		HttpSession session = request.getSession();
		id = (Integer) session.getAttribute("id");
		role = (String) session.getAttribute("role");
		System.out.println("\n" + role);
		bookName = request.getParameter("bookName");
		bookAuthor = request.getParameter("bookAuthor");
		bookYear = request.getParameter("bookYear");
		Book book = new Book(bookName, bookAuthor, bookYear);

		try {
			if (role.equals("admin")) {
				libraryService.addNewBook(book);
				request.setAttribute("book", book);
				System.out.println(bookName + " is added");
				response.sendRedirect("Controller?command=show&Messege=book is added&bookName=" + book.getName()
						+ "&bookAuthor=" + book.getAuthor() + "&bookYear=" + book.getAge());
			} else {
				request.setAttribute("errorMessage", "you have no rights for this operation");
				RequestDispatcher dispatcher = request.getRequestDispatcher("addform.jsp");
				dispatcher.forward(request, response);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "wrong input, try once more");
			RequestDispatcher dispatcher = request.getRequestDispatcher("addform.jsp");
			dispatcher.forward(request, response);
		}
	}
}
