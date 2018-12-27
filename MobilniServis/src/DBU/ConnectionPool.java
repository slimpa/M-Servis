package DBU;

import java.sql.Connection;
import java.util.List;

public class ConnectionPool {

	private String jdbcUrl;
	private String username;
	private String password;
	private int preConnectCount;
	private int maxIdleConnection;
	private int maxConnections;
	private int connectCount;
	private List<Connection> usedConnections;
	private List<Connection> freeConnections;
	private ConnectionPool instance;

	public ConnectionPool getInstance() {
		return this.instance;
	}

	private ConnectionPool() {
		// TODO - implement ConnectionPool.ConnectionPool
		throw new UnsupportedOperationException();
	}

	private void readConfiguration() {
		// TODO - implement ConnectionPool.readConfiguration
		throw new UnsupportedOperationException();
	}

	public Connection checkOut() {
		// TODO - implement ConnectionPool.checkOut
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param connection
	 */
	public void checkIn(Connection connection) {
		// TODO - implement ConnectionPool.checkIn
		throw new UnsupportedOperationException();
	}

}