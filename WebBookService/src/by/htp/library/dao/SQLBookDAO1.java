package by.htp.library.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.RowId;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.SQLPool.ConnectionPool;
import by.htp.library.dao.SQLPool.DBResourceManager;
import by.htp.library.dao.SQLPoolException.ConnectionPoolException;

public class SQLBookDAO1 implements BookDAO {
	ConnectionPool conPool = ConnectionPool.getInstance();

	SQLBookDAO1() {
	}

	@Override
	public void addBook(Book book) throws DAOException {
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
			rs = st.executeQuery("select * from books where ID = (select max(ID) from books)");
			while (rs.next()) {
				maxId = rs.getInt("ID");
				maxId++;
			}
			book.setId((maxId));
			System.out.println("id " + book.getId());

			String sq = "INSERT INTO books (name, author, age, ID) VALUES (?, ?, ?, ?)";
			ps = con.prepareStatement(sq);
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getAge());
			ps.setInt(4, book.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, st, rs);
		}
	}

	@Override
	public ArrayList<Book> findBook(String name) throws DAOException {
		// TODO Auto-generated method stub
		ArrayList<Book> foundbooks = new ArrayList<>();
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = conPool.takeConnection();
		} catch (ConnectionPoolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM books");
			if (name.equals("findAllBooks"))
				while (rs.next()) {
					if (rs.getInt(4) != 0)
						foundbooks.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
				}
			// for(int i = 0; i< foundbooks.size(); i++){
			// System.out.println(foundbooks.get(i).getName());
			// }

			while (rs.next()) {

				if (rs.getString(1).equals(name))
					foundbooks.add(new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error or such a book does not exist", e);

		} finally {
			conPool.closeConnection(con, st, rs);
		}
		if (foundbooks.isEmpty()) {
			throw new DAOException("such a book does not exist");
		}
		return foundbooks;
	}

	@Override
	public void addEditedBook(Book oldBook, Book newBook) throws DAOException {

	}

	@Override
	public Book deleteBook(int id) throws DAOException {
		PreparedStatement ps = null;
		Connection con = null;
		ResultSet rs = null;
		Book book;
		Statement st = null;
		try {
			con = conPool.takeConnection();
		} catch (ConnectionPoolException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			// System.out.println(id);
			ps = con.prepareStatement("SELECT * FROM books WHERE (ID=?)");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				System.out
						.println(rs.getString(1) + rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getInt(4));

			} else {
				throw new DAOException("no such a book");
			}
			st = con.createStatement();
			st.executeUpdate("UPDATE books SET ID = 0 WHERE ID=+" + id + "");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return book;
	}

}