package mySQL;

import dao.DobavljacDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.DobavljacDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLDobavljacDAO implements DobavljacDAO {

	public static final String SQL_SELECT_ALL="SELECT * from dobavljac where Obrisano=0";
        public static final String SQL_INSERT = "INSERT INTO dobavljac (Naziv, Adresa, Telefon, Email, Obrisano) VALUES (?, ?, ?, ?, ?)";
        public static final String SQL_UPDATE="UPDATE dobavljac set Naziv=?,Adresa=?,Telefon=?,Email=? where IdDobavljac=?";
        public static final String SQL_DELETE="UPDATE dobavljac set Obrisano=1 where IdDobavljac=? and Obrisano=0";
              
	public boolean insert(DobavljacDTO dobavljac) 
        {
                Connection conn = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setString(1, dobavljac.getNaziv());
                        ps.setString(2,dobavljac.getAdresa());
                        ps.setString(3,dobavljac.getTelefon());
                        ps.setString(4,dobavljac.getEmail());
                        ps.setInt(5,0);
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
	 * @param dobavljac
	 */
	public boolean update(DobavljacDTO dobavljac) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_UPDATE);
			
			ps.setString(1, dobavljac.getNaziv());
                        ps.setString(2, dobavljac.getAdresa());
                        ps.setString(3, dobavljac.getTelefon());
                        ps.setString(4, dobavljac.getEmail());
			ps.setInt(5, dobavljac.getIdDobavljac());
			
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
	 * @param dobavljac
	 */
	public boolean delete(DobavljacDTO dobavljac) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);

			ps.setInt(1, dobavljac.getIdDobavljac());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<DobavljacDTO> selectAll() {
		ArrayList<DobavljacDTO> retVal=new ArrayList<DobavljacDTO>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			conn=ConnectionPool.getInstance().checkOut();
			st=conn.createStatement();
			rs=st.executeQuery(SQL_SELECT_ALL);
			if(rs==null){
				return null;
			}
			else{
				while(rs.next()){
                                    retVal.add(new DobavljacDTO(rs.getInt("IdDobavljac"),rs.getString("Naziv"),
                                        rs.getString("Adresa"),rs.getString("Telefon"),rs.getString("Email")));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(st);
		}
		return retVal;
	
	}
	public List<DobavljacDTO> selectBy(DobavljacDTO dobavljac) {
		// TODO - implement MySQLDobavljacDAO.selectBy
		throw new UnsupportedOperationException();
	}
        
        public List<DobavljacDTO> selectByName(String dobavljac) {
		List<DobavljacDTO> dobavljaci = new ArrayList<DobavljacDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT_ALL + " and Naziv=?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, dobavljac);
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					dobavljaci.add(new DobavljacDTO(rs.getInt("IdDobavljac"),rs.getString("Naziv"),
                                        rs.getString("Adresa"),rs.getString("Telefon"),rs.getString("Email")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return dobavljaci;
	}

}