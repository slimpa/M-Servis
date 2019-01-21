package mySQL;

import dao.ArtikalDAO;
import dbu.ConnectionPool;
import dto.ArtikalDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dbu.DBUtil;

import java.util.List;

public class MySQLArtikalDAO implements ArtikalDAO {

    
    
        public static final String SQL_INSERT = "insert into proizvodjac values (?, ?, ?)";
	public static final String SQL_SELECT = "select * from artikal";
	public static final String SQL_UPDATE = "update proizvodjac set";
	/**
	 * 
	 * @param artikal
	 */
	public boolean insert(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param artikal
	 */
	public boolean update(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param artikal
	 */
	public boolean delete(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<ArtikalDTO> selectAll() {
		List<ArtikalDTO> artikli = new ArrayList<ArtikalDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			stat = conn.createStatement();
			rs = stat.executeQuery(SQL_SELECT);
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					artikli.add(new ArtikalDTO(rs.getInt("idArtikal"),rs.getString("Naziv"),
                                                rs.getInt("Kolicina"),rs.getInt("idProizvodjac"),rs.getString("BarKod"),rs.getBoolean("Obrisano")));
                                        
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return artikli;
	}

	/**
	 * 
	 * @param artikal
	 */
	public List<ArtikalDTO> selectBy(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.selectBy
		throw new UnsupportedOperationException();
	}

}