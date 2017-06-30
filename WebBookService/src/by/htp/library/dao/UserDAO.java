package by.htp.library.dao;

import by.htp.library.bean.User;

public interface UserDAO {
void signIn(String login, String password);
void registration(User user);

}
