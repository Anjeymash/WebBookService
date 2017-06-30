package by.htp.library.service;

import by.htp.library.bean.User;
import by.htp.library.dao.DAOFactory;
import by.htp.library.dao.UserDAO;

public class ClientServiceImpl implements ClientService{
@Override
public User singIn(String login, String password){
	DAOFactory daoObjectFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoObjectFactory.getUserDAO();
	return userDAO.signIn(login, password);
}
@Override
public void singOut(String login){
	DAOFactory daoObjectFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoObjectFactory.getUserDAO();
}

@Override
public void registration(User user){
	DAOFactory daoObjectFactory = DAOFactory.getInstance();
	UserDAO userDAO = daoObjectFactory.getUserDAO();
}
}
