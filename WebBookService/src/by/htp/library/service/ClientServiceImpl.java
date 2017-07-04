package by.htp.library.service;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOException;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.UserDAO;

public class ClientServiceImpl implements ClientService {
	@Override
	public User singIn(String login, String password) throws ServiceException {
		if ((login == null) || (login == "") || (password == null) || (password == "")) {
			throw new ServiceException("Incorrect input data");
		}

		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			return userDAO.signIn(login, password);
			
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}

	@Override
	public void singOut(String login) throws ServiceException {
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
	}

	@Override
	public void registration(User user) throws ServiceException {
		if ((user == null) || (user.getName() == null) || (user.getLogin() == null) || (user.getPassword() == null)
				|| (user.getName() == "") || (user.getLogin() == "") || (user.getPassword() == "")) {
			throw new ServiceException("Incorrect input data");
		}
		DAOFactory daoObjectFactory = DAOFactory.getInstance();
		UserDAO userDAO = daoObjectFactory.getUserDAO();
		try {
			userDAO.registration(user);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			throw new ServiceException(e);
		}
	}
}
