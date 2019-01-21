package mySQL;

import dao.TelefonDAO;
import dto.TelefonDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import dbu.*;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

public class MySQLTelefonDAO implements TelefonDAO {

    
        public static final String SQL_INSERT = "insert into proizvodjac values (?, ?, ?)";
	public static final String SQL_SELECT = "select * from rezervnidio";
	public static final String SQL_UPDATE = "update proizvodjac set";
        public static final String SQL_SELECT_DETAIL = "select * from svi_telefoni";
	/**
	 * 
	 * @param telefon
	 */
	public boolean insert(TelefonDTO telefon) {
		// TODO - implement MySQLTelefonDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param telefon
	 */
	public boolean update(TelefonDTO telefon) {
		// TODO - implement MySQLTelefonDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param telefon
	 */
	public boolean delete(TelefonDTO telefon) {
		// TODO - implement MySQLTelefonDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<TelefonDTO> selectAll() {
		List<TelefonDTO> sviTelefoni = new ArrayList<TelefonDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			stat = conn.createStatement();
			rs = stat.executeQuery(SQL_SELECT_DETAIL);
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					sviTelefoni.add(new TelefonDTO(rs.getString("SerijskiBroj"),rs.getString("Boja"),rs.getInt("IdModelTelefona"),
                                        rs.getString("Naziv"),rs.getString("NazivModela"),rs.getString("Proizvodjac"),rs.getString("Specifikacija"),rs.getInt("Cijena")));
                                       
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return sviTelefoni;
	}

	/**
	 * 
	 * @param telefon
	 */
	public List<TelefonDTO> selectBy(TelefonDTO telefon) {
		// TODO - implement MySQLTelefonDAO.selectBy
		throw new UnsupportedOperationException();
	}

}