package by.htp.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import by.htp.library.bean.User;
import by.htp.library.dao.SQLPool.ConnectionPool;
import by.htp.library.dao.SQLPoolException.ConnectionPoolException;

public class SQLUserDAO implements UserDAO {
	
	ConnectionPool conPool = ConnectionPool.getInstance();

	@Override
	public User signIn(String login, String password) throws DAOException {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		User user = null;
		try {
			con = conPool.takeConnection();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			throw new DAOException("SQL connection error", e);
		}
		try {
			st = con.createStatement();

			String query = "select * from users where login = ? and password = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, login);
			stmt.setString(2, password);
			rs = stmt.executeQuery();

			while (rs.next())
				user = new User(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
			// System.out.println(user.getName() + " found "+rs.getString(5));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new DAOException("SQL connection error", e);
		} finally {
			conPool.closeConnection(con, st, rs);

		}
		return user;
	}

	@Override
	public void registration(User user) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		int maxId = 0;
		try {
			con = conPool.takeConnection();
		} catch (ConnectionPoolException e) {
			// TODO Auto-generated catch block
			throw new DAOException("SQL connection error", e);
		}
		try {
			st = con.createStatement();
			String query = "select * from users where login = ?";
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, user.getLogin());
			rs = stmt.executeQuery();
			if (rs.next()) {
				throw new DAOException("such a user already exists");
			}
			rs.close();
			rs = st.executeQuery("select * from users where ID = (select max(ID) from users)");
			while (rs.next()) {
				maxId = rs.getInt("ID");
				maxId++;
			}
			user.setId((maxId));
			System.out.println("id" + user.getId());

			String sq = "INSERT INTO users (name, login, password, id, role) VALUES (?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sq);
			ps.setString(1, user.getName());
			ps.setString(2, user.getLogin());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getId());

			if (user.getLogin().equals("AdminAnjey"))
				ps.setString(5, "admin");
			else
				ps.setString(5, "user");
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, st, rs);
		}
	}
}
