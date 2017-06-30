package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.controller.Command;

public class WrongRequest implements Command {
	String response;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		// TODO Auto-generated method stub
		String page = "main.jsp";
		//request.setAttribute("errorMessage", "wrong input");
	

	RequestDispatcher dispatcher = request.getRequestDispatcher(page);
	dispatcher.forward(request, response);
	}

}
