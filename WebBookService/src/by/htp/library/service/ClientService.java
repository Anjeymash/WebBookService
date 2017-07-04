package by.htp.library.service;

import by.htp.library.bean.User;

public interface ClientService {
User singIn(String login, String password) throws ServiceException;
void singOut(String login) throws ServiceException;
void registration(User user) throws ServiceException;
}
