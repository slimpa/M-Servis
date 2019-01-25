package mySQL;

import dao.ProizvodjacDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ProizvodjacDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLProizvodjacDAO implements ProizvodjacDAO {

	/**
	 * 
	 * @param proizvodjac
	 */
    
        public static final String SQL_INSERT = "insert into proizvodjac (`Naziv`,`Obrisano`) values (?,?);";
	public static final String SQL_SELECT = "select * from proizvodjac where Obrisano = 0";
	public static final String SQL_UPDATE = "update proizvodjac set";
        
	public boolean insert(ProizvodjacDTO proizvodjac) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
                    
                    if(this.selectBy(proizvodjac).isEmpty() && this.selectIfRemoved(proizvodjac.getNaziv()).isEmpty()){
                       
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setString(1, proizvodjac.getNaziv());
                        ps.setInt(2, 0);
			returnValue = ps.executeUpdate() == 1;
                    } else if(!this.selectIfRemoved(proizvodjac.getNaziv()).isEmpty()){
                        conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_UPDATE + " Obrisano = 0 where Naziv = ?");
                        ps.setString(1, proizvodjac.getNaziv());
			returnValue = ps.executeUpdate() == 1;
                    }
		} catch(SQLException e) {
			e.printStackTrace();
                        return false;
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue;
	}

	/**
	 * 
	 * @param proizvodjac
	 */
	public boolean update(ProizvodjacDTO proizvodjac) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		String query = SQL_UPDATE + " Naziv = ? where IdProizvodjac = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			
			ps.setString(1, proizvodjac.getNaziv());
			ps.setInt(2, proizvodjac.getIdProizvodjac());
			
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
	 * @param proizvodjac
	 */
	public boolean delete(ProizvodjacDTO proizvodjac) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		String query = SQL_UPDATE + " Obrisano = 1 where IdProizvodjac = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);

			ps.setInt(1, proizvodjac.getIdProizvodjac());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<ProizvodjacDTO> selectAll() {
		List<ProizvodjacDTO> proizvodjaci = new ArrayList<ProizvodjacDTO>();
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
					proizvodjaci.add(new ProizvodjacDTO(rs.getInt("IdProizvodjac"),rs.getString("Naziv")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return proizvodjaci;
	}

	/**
	 * 
	 * @param proizvodjac
	 */
	public List<ProizvodjacDTO> selectBy(ProizvodjacDTO proizvodjac) {
		List<ProizvodjacDTO> proizvodjaci = new ArrayList<ProizvodjacDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT + " and Naziv like ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, proizvodjac.getNaziv() + "%");
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					proizvodjaci.add(new ProizvodjacDTO(rs.getInt("IdProizvodjac"), rs.getString("Naziv")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return proizvodjaci;
	}

   
    public List<ProizvodjacDTO> selectIfRemoved(String naziv) {
        List<ProizvodjacDTO> proizvodjaci = new ArrayList<ProizvodjacDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = "select * from proizvodjac where Obrisano = 1 and Naziv = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					proizvodjaci.add(new ProizvodjacDTO(rs.getInt("IdProizvodjac"), rs.getString("Naziv")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return proizvodjaci;
    }
}