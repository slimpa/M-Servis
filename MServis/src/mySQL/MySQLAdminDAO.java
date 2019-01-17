package mySQL;

import dao.AdminDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.AdminDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class MySQLAdminDAO implements AdminDAO {
    
        public static final String SQL_SELECT="select * from admin";
	/**
	 * 
	 * @param admin
	 */
	public boolean insert(AdminDTO admin) {
		// TODO - implement MySQLAdminDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param admin
	 */
	public boolean update(AdminDTO admin) {
		// TODO - implement MySQLAdminDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param admin
	 */
	public boolean delete(AdminDTO admin) {
		// TODO - implement MySQLAdminDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<AdminDTO> selectAll() {
		// TODO - implement MySQLAdminDAO.selectAll
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param admin
	 */
	public List<AdminDTO> selectBy(AdminDTO admin) {
            throw new UnsupportedOperationException();
	}

    
        	/**
	 * 
	 * @param admin
	 */
        public AdminDTO selectAdmin(AdminDTO admin) {
                AdminDTO retVal=null;
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		String query=SQL_SELECT+" where KorisnickoIme=? and Lozinka=?";
		
		try {
			conn=ConnectionPool.getInstance().checkOut();
			ps=conn.prepareStatement(query);
			ps.setString(1, admin.getKoriscnikoIme());
                        ps.setString(2, admin.getLozinka());
			rs=ps.executeQuery();
			
			if(rs==null) {
				return null;
			}
			else {
                        if(rs.next()){
			retVal=new AdminDTO(rs.getInt("IdAdmin"),rs.getString("KorisnickoIme"),rs.getString("Lozinka"),rs.getString("NazivFirme"));
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