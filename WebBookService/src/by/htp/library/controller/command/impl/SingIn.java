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

public class SingIn implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;
		String page;
		User user = null;
		HttpSession session = request.getSession(true);

		login = request.getParameter("userLogin");
		password = request.getParameter("userPassword");
		System.out.println(login);

		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();

		try {
			user = clientService.singIn(login, password);
			// System.out.println("роль "+ user.getRole()+" "+ user.getName()+"
			// "+ user.getLogin()+" "+ user.getPassword());
			if (user != null) {
				request.setAttribute("userName", user.getName());
				page = "WEB-INF/jsp/main.jsp";
				session.setAttribute("id", user.getId());
				session.setAttribute("role", user.getRole());
			} else
				throw new ServiceException("wrong login or password");

		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			page = "singin.jsp";
			request.setAttribute("errorMessage", "wrong login or password");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	}
}
