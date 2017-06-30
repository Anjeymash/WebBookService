package by.htp.library.service;

import by.htp.library.bean.User;

public interface ClientService {
User singIn(String login, String password);
void singOut(String login);
void registration(User user);
}
