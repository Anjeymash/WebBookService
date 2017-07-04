package by.htp.library.dao;

import by.htp.library.bean.User;

public interface UserDAO {
User signIn(String login, String password) throws DAOException;

void registration(User user) throws DAOException;

}
