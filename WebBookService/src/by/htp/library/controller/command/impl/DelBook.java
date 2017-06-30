package by.htp.library.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.bean.Book;
import by.htp.library.controller.Command;
//import by.htp.library.dao.DAOException;
import by.htp.library.service.LibraryService;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceFactory;

public class DelBook implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		int bookID = 0;
		Book book;
		
		//bookID = Integer.parseInt(request.getParameter("bookID"));
		System.out.println(request.getParameter("bookID"));
		bookID = Integer.parseInt(request.getParameter("bookID"));
		String page;
		

		try {
			book = libraryService.delBook(bookID);
			//System.out.println(book.getName());
			request.setAttribute("book", book);
			
			response.sendRedirect("Controller?command=show&Messege=book is deleted&bookName=" + book.getName()
			+ "&bookAuthor=" + book.getAuthor() + "&bookYear=" + book.getAge());

			//request.setAttribute("Messege", "book is deleted");
			
			//page = "show.jsp";

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
