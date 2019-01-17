package mySQL;

import dao.ZaposleniDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ZaposleniDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MySQLZaposleniDAO implements ZaposleniDAO {
    
    
    public static final String SQL_SELECT="select * from zaposleni";

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean insert(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean update(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposleni
	 */
	public boolean delete(ZaposleniDTO zaposleni) {
		// TODO - implement MySQLZaposleniDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<ZaposleniDTO> selectAll() {
		// TODO - implement MySQLZaposleniDAO.selectAll
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param zaposleni
	 */
	public List<ZaposleniDTO> selectBy(ZaposleniDTO zaposleni) {
                throw new UnsupportedOperationException();
	} 

        	/**
	 * 
	 * @param zaposleni
	 */
        public ZaposleniDTO selectZaposleni(ZaposleniDTO zaposleni) {
                ZaposleniDTO retVal=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=SQL_SELECT+" where KorisnickoIme=? and Lozinka=?";
		
		try {
			conn=ConnectionPool.getInstance().checkOut();
			ps=conn.prepareStatement(query);
			ps.setString(1, zaposleni.getKoriscnikoIme());
                        ps.setString(2, zaposleni.getLozinka());
			rs=ps.executeQuery();
			
			if(rs==null) {
				return null;
			}
                        else{
			if(rs.next()){
                          retVal=new ZaposleniDTO(rs.getInt("IdZaposleni"),rs.getString("KorisnickoIme"),rs.getString("Lozinka"),rs.getString("RadnoMjesto"));
			}	
                        }
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return retVal;
    }
	

}