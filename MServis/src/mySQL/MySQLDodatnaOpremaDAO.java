package mySQL;

import dao.DodatnaOpremaDAO;
import dto.DodatnaOpremaDTO;
import dbu.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLDodatnaOpremaDAO implements DodatnaOpremaDAO {
        
    
        public static final String SQL_INSERT_DODATNA_OPREMA = "insert into dodatnaoprema values (?, ?, ?, ?)";
	public static final String SQL_SELECT = "select * from rezervnidio";
	public static final String SQL_UPDATE = "update proizvodjac set";
        public static final String SQL_SELECT_DETAIL = "select * from dodatna_oprema";
        public static final String SQL_DELETE = "DELETE FROM `m:servis`.`dodatnaoprema` WHERE `IdDodatnaOprema`=?";
	/**
	 * 
	 * @param dodatnaOprema
	 */
	public boolean insert(DodatnaOpremaDTO dodatnaOprema) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT_DODATNA_OPREMA);
			ps.setInt(1, dodatnaOprema.getIdDodatnaOprema());
			ps.setString(2, dodatnaOprema.getBoja());
                        ps.setInt(3, dodatnaOprema.getIdTipDodatneOpreme());
                        ps.setInt(4, dodatnaOprema.getIdModelTelefona());
                        
                        System.out.println(dodatnaOprema.getIdDodatnaOprema()+ " " +dodatnaOprema.getBoja()+ " " + dodatnaOprema.getIdTipDodatneOpreme()  + " " +dodatnaOprema.getIdModelTelefona());
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
	 * @param dodatnaOprema
	 */
	public boolean update(DodatnaOpremaDTO dodatnaOprema) {
		// TODO - implement MySQLDodatnaOpremaDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param dodatnaOprema
	 */
	public boolean delete(DodatnaOpremaDTO dodatnaOprema) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);

			ps.setInt(1, dodatnaOprema.getIdDodatnaOprema());
			
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

			ps.setInt(1, dodatnaOprema.getIdDodatnaOprema());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<DodatnaOpremaDTO> selectAll() {
		List<DodatnaOpremaDTO> dodatnaOprema = new ArrayList<DodatnaOpremaDTO>();
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
					dodatnaOprema.add(new DodatnaOpremaDTO(rs.getString("Boja"),rs.getInt("idDodatnaOprema"),rs.getString("Naziv"),
                                        rs.getString("NazivModela"),rs.getString("TipOpreme"),rs.getInt("Kolicina"),rs.getInt("Cijena")));
                                      
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
                return dodatnaOprema;
	}

	/**
	 * 
	 * @param dodatnaOprema
	 */
	public List<DodatnaOpremaDTO> selectBy(String naziv) {
		List<DodatnaOpremaDTO> dodatnaOprema = new ArrayList<DodatnaOpremaDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT_DETAIL + " where Naziv like ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, "%" + naziv + "%");
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					dodatnaOprema.add(new DodatnaOpremaDTO(rs.getString("Boja"),rs.getInt("idDodatnaOprema"),rs.getString("Naziv"),
                                        rs.getString("NazivModela"),rs.getString("TipOpreme"),rs.getInt("Kolicina"),rs.getInt("Cijena")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return dodatnaOprema;
	}

}