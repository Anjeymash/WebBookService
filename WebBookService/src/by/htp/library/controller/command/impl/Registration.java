package by.htp.library.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp.library.bean.User;
import by.htp.library.controller.Command;
import by.htp.library.service.ClientService;
import by.htp.library.service.ServiceException;
import by.htp.library.service.ServiceFactory;

public class Registration implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String name;
		String page;

		HttpSession session = request.getSession();

		login = request.getParameter("userLogin");
		password = request.getParameter("userPassword");
		name = request.getParameter("userName");
		System.out.println(name);

		User user = new User(name, login, password, 0, "u");
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();
		try {
			clientService.registration(user);
			request.setAttribute("userName", name);
			page = "WEB-INF/jsp/main.jsp";

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			page = "registration.jsp";
			request.setAttribute("errorMessage", "such a user is already exist");
		}
		session.setAttribute("id", user.getId());
		session.setAttribute("role", user.getRole());

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}
}
