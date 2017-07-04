package by.htp.library.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import by.htp.library.bean.Book;
import by.htp.library.dao.BookDAO;
import by.htp.library.dao.SQLPool.ConnectionPool;
import by.htp.library.dao.SQLPoolException.ConnectionPoolException;

public class SQLBookDAO1 implements BookDAO {
	private final static String SEL_MAX_ID = "select * from books where ID = (select max(ID) from books)";
	private final static String INS_BOOK = "INSERT INTO books (name, author, age, ID, status) VALUES (?, ?, ?, ?, ?)";
	private final static String SEL_ACT_BOOK =	"SELECT * FROM books WHERE status = 'active'";
	private final static String SEL_BY_ID = "SELECT * FROM books WHERE (ID=?)";
	
	ConnectionPool conPool = ConnectionPool.getInstance();

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
			rs = st.executeQuery(SEL_MAX_ID);
			while (rs.next()) {
				maxId = rs.getInt("ID");
				maxId++;
			}
			book.setId((maxId));
			System.out.println("id " + book.getId());

			ps = con.prepareStatement(INS_BOOK);
			ps.setString(1, book.getName());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getAge());
			ps.setInt(4, book.getId());
			ps.setString(5, "active");
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
			rs = st.executeQuery(SEL_ACT_BOOK);
			if (name.equals("findAllBooks")) {
				while (rs.next()) {
					foundbooks.add(
							new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
				}
			}
			while (rs.next()) {
				if (rs.getString(1).equals(name))
					foundbooks.add(
							new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error or such a book does not exist", e);

		} finally {
			conPool.closeConnection(con, st, rs);
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
			ps = con.prepareStatement(SEL_BY_ID);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				book = new Book(rs.getString(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getString(5));
				System.out
						.println(rs.getString(1) + rs.getString(1) + rs.getString(2) + rs.getString(3) + rs.getInt(4));

			} else {
				throw new DAOException("no such a book");
			}
			st = con.createStatement();
			st.executeUpdate("UPDATE books SET status = 'deleted' WHERE ID=+" + id + "");

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException("SQL error", e);
		} finally {
			conPool.closeConnection(con, ps, rs);
		}
		return book;
	}

}