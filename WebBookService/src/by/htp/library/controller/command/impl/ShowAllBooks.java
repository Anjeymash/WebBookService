package by.htp.library.controller.command.impl;

import java.io.IOException;
import java.util.ArrayList;

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

public class ShowAllBooks implements Command {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Book> foundBooks = new ArrayList<>();
	//	String bookName = null;
		String page;
	
		// String response = null;
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LibraryService libraryService = serviceFactory.getLibraryService();
		try {
			foundBooks = libraryService.fndBook("findAllBooks");
		//for(int i = 0; i< foundBooks.size(); i++){
			//System.out.println(foundBooks.get(i).getName());		}
			request.setAttribute("listbook", foundBooks);
			page = "WEB-INF/jsp/showBooks.jsp";
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			page = "fndform.jsp";
			e.printStackTrace();
			request.setAttribute("errorMessage", "wrong input or the book does not exist");
		}
		
		

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}
}
