package mySQL;

import dao.NarudzbaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.NarudzbaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;
import static mySQL.MySQLDobavljacDAO.SQL_INSERT;

public class MySQLNarudzbaDAO implements NarudzbaDAO {

	public static final String SQL_SELECT_ALL="SELECT * from narudzba";
        public static final String SQL_INSERT = "INSERT INTO narudzba (DatumNarudzbe, IdDobavljac) VALUES (?, ?)";
        public static final String SQL_UPDATE="UPDATE narudzba set DatumNarudzbe=?,IdDobavljac=? where IdNarudzba=?";
	public static final String SQL_DELETE="DELETE FROM narudzba WHERE IdNarudzba=?";
               
        public boolean insert(NarudzbaDTO narudzba) {
		Connection conn = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setTimestamp(1, narudzba.getDatumNarudzbe());
                        ps.setInt(2,narudzba.getIdDobavljac());                       
                        returnValue=ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue==1;
	}

	/**
	 * 
	 * @param narudzba
	 */
	public boolean update(NarudzbaDTO narudzba) {
		// TODO - implement MySQLNarudzbaDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param narudzba
	 */
	public boolean delete(NarudzbaDTO narudzba) {
		boolean returnValue = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);
			ps.setInt(1, narudzba.getIdNarudzba());
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue;
	}

	public List<NarudzbaDTO> selectAll() {
			List<NarudzbaDTO> narudzbe = new ArrayList<NarudzbaDTO>();
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			stat = conn.createStatement();
			rs = stat.executeQuery(SQL_SELECT_ALL);
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					narudzbe.add(new NarudzbaDTO(rs.getInt("IdNarudzba"),rs.getTimestamp("DatumNarudzbe"),rs.getInt("IdDobavljac")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return narudzbe;
	}

	/**
	 * 
	 * @param narudzba
	 */
	public List<NarudzbaDTO> selectBy(NarudzbaDTO narudzba) {
		// TODO - implement MySQLNarudzbaDAO.selectBy
		throw new UnsupportedOperationException();
	}

}