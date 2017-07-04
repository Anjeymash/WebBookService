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

public class DelBook implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		int bookID = 0;
		int id;
		String role;
		Book book;
		HttpSession session = request.getSession();
		id = (Integer) session.getAttribute("id");
		role = (String) session.getAttribute("role");
		// System.out.println(id);
		// System.out.println(request.getParameter("bookID"));
		bookID = Integer.parseInt(request.getParameter("bookID"));
		String page;
		try {
			if (role.equals("admin")) {
				book = libraryService.delBook(bookID);
				// System.out.println(book.getName());
				request.setAttribute("book", book);
				response.sendRedirect("Controller?command=show&Messege=book is deleted&bookName=" + book.getName()
						+ "&bookAuthor=" + book.getAuthor() + "&bookYear=" + book.getAge());
			} else {
				request.setAttribute("errorMessage", "you have no rights for this operation");
				RequestDispatcher dispatcher = request.getRequestDispatcher("delform.jsp");
				dispatcher.forward(request, response);
			}

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			page = "delform.jsp";
			e.printStackTrace();
			request.setAttribute("errorMessage", "wrong input or the book does not exist");
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);
		}

	}
}
