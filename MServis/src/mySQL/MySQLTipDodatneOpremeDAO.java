package mySQL;

import dao.TipDodatneOpremeDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.TipDodatneOpremeDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLTipDodatneOpremeDAO implements TipDodatneOpremeDAO {

    
        public static final String SQL_INSERT = "INSERT INTO `m:servis`.`tipdodatneopreme` (`TipOpreme`,`Obrisano`) VALUES (?,?);";
	public static final String SQL_SELECT = "select * from tipdodatneopreme where Obrisano = 0";
	public static final String SQL_UPDATE = "update tipdodatneopreme set";
	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	public boolean insert(TipDodatneOpremeDTO tipDodatneOpreme) {
                Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setString(1, tipDodatneOpreme.getTip());
                        ps.setInt(2, 0);
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			//e.printStackTrace();
                        return false;
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue;
	}

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	public boolean update(TipDodatneOpremeDTO tipDodatneOpreme) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		String query = SQL_UPDATE + " TipOpreme = ? where IdTipDodatneOpreme = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, tipDodatneOpreme.getTip());
			ps.setInt(2, tipDodatneOpreme.getIdTipDodatneOpreme());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
                        return false;
			//e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	public boolean delete(TipDodatneOpremeDTO tipDodatneOpreme) {
            	Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		String query = SQL_UPDATE + " Obrisano = 1 where IdTipDodatneOpreme = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);

			ps.setInt(1, tipDodatneOpreme.getIdTipDodatneOpreme());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<TipDodatneOpremeDTO> selectAll() {
		List<TipDodatneOpremeDTO> oprema = new ArrayList<TipDodatneOpremeDTO>();
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
					oprema.add(new TipDodatneOpremeDTO(rs.getInt("IdTipDodatneOpreme"),rs.getString("TipOpreme")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return oprema;
	}

	/**
	 * 
	 * @param tipDodatneOpreme
	 */
	public List<TipDodatneOpremeDTO> selectBy(TipDodatneOpremeDTO tipDodatneOpreme) {
		List<TipDodatneOpremeDTO> oprema = new ArrayList<TipDodatneOpremeDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT + " and TipOpreme = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, tipDodatneOpreme.getTip());
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					oprema.add(new TipDodatneOpremeDTO(rs.getInt("IdTipDodatneOpreme"), rs.getString("TipOpreme")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return oprema;
	}

}