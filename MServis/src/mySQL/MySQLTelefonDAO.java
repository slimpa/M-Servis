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

    
        public static final String SQL_INSERT = "insert into telefon (`IdModeTelefona`,`SerijskiBroj`,`Boja`,`Obrisano`) values (?, ?, ?, ?)";
	public static final String SQL_SELECT = "select * from rezervnidio";
	
        public static final String SQL_SELECT_DETAIL = "select * from svi_telefoni";
        public static final String SQL_DELETE = "update telefon set Obrisano = 1 WHERE `IdModeTelefona`=? and SerijskiBroj = ?";
	/**
	 * 
	 * @param telefon
	 */
	public boolean insert(TelefonDTO telefon) {
                Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setInt(1, telefon.getIdModelTelefona());
			ps.setString(2, telefon.getSerijskiBroj());
                        ps.setString(3, telefon.getBoja());
                        ps.setInt(4, 0);
                        
                        
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue;
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
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);

			ps.setInt(1, telefon.getIdModelTelefona());
                        ps.setString(2, telefon.getSerijskiBroj());
                        
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
                
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement("update artikal set Obrisano=1 where IdArtikal = ?");

			ps.setInt(1, telefon.getIdModelTelefona());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
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
                                        rs.getString("Naziv"),rs.getString("NazivModela"),rs.getString("Proizvodjac"),rs.getString("Specifikacija"),rs.getDouble("Cijena")));
                                       
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
	public List<TelefonDTO> selectBy(String model) {
		List<TelefonDTO> telefoni = new ArrayList<TelefonDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT_DETAIL + " where NazivModela like ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + model + "%");
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					telefoni.add(new TelefonDTO(rs.getString("SerijskiBroj"),rs.getString("Boja"),rs.getInt("IdModelTelefona"),
                                        rs.getString("Naziv"),rs.getString("NazivModela"),rs.getString("Proizvodjac"),rs.getString("Specifikacija"),rs.getDouble("Cijena")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return telefoni;
	}

}