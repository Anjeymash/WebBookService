package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp.library.bean.User;
import by.htp.library.controller.Command;
import by.htp.library.service.ClientService;
import by.htp.library.service.ServiceFactory;

public class Registration implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String name;

		login = request.getParameter("login");
		password = request.getParameter("password");
		name = request.getParameter("name");
	
User user = new User(name, login, password, 0);
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();
		clientService.registration(user);

		String page;
		if ((name != null)&&(login != null)&&(password != null)) {
			request.setAttribute("user", user);
			page = "WEB-INF/jsp/main.jsp";
		} else {
			page = "index.jsp";
			request.setAttribute("errorMessage", "wrong login or password");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	
	}
	}


