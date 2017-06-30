package by.htp.library.dao.SQLPool;

public class DBResourceManager {
	private final static DBResourceManager instance = new DBResourceManager();
	public static final String DB_DRIVER = "org.gjt.mm.mysql.Driver";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/books";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Qwert1234";
	public static final String DB_POLL_SIZE = "10";
	public static DBResourceManager getInstance() {
		return instance;
		}
	DBResourceManager(){}
}
