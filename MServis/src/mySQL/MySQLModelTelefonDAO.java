package mySQL;

import dao.ModelTelefonaDAO;
import dto.ModelTelefonaDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import dbu.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class MySQLModelTelefonDAO implements ModelTelefonaDAO {

    
        public static final String SQL_INSERT = "insert into modeltelefona (`IdModelTelefona`,`NazivModela`,`Slika`,`Specifikacija`) values (?,?,?,?);";
	public static final String SQL_SELECT = "SELECT * FROM `m:servis`.modeltelefona";

        public static final String SQL_DELETE = "DELETE FROM `m:servis`.`modeltelefona` WHERE `IdModelTelefona`=?";
	/**
	 * 
	 * @param modelTelefona
	 */
	public boolean insert(ModelTelefonaDTO modelTelefona) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setInt(1, modelTelefona.getIdModeltelefona());
			ps.setString(2, modelTelefona.getNazivModela());
                        ps.setString(3, modelTelefona.getSlika());
                        ps.setString(4, modelTelefona.getSpecifikacija());
                        
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
	 * @param modelTelefona
	 */
	public boolean update(ModelTelefonaDTO modelTelefona) {
		// TODO - implement MySQLModelTelefonDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param modelTelefona
	 */
	public boolean delete(ModelTelefonaDTO modelTelefona) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);

			ps.setInt(1, modelTelefona.getIdModeltelefona());
			
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

			ps.setInt(1, modelTelefona.getIdModeltelefona());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<ModelTelefonaDTO> selectAll() {
		List<ModelTelefonaDTO> modeli = new ArrayList<ModelTelefonaDTO>();
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
					modeli.add(new ModelTelefonaDTO(rs.getInt("idModeltelefona"),rs.getString("Specifikacija"),rs.getString("Slika"),rs.getString("NazivModela")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return modeli;
	}

	/**
	 * 
	 * @param modelTelefona
	 */
	public List<ModelTelefonaDTO> selectBy(ModelTelefonaDTO modelTelefona) {
		List<ModelTelefonaDTO> modeli = new ArrayList<ModelTelefonaDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT + " where NazivModela = ?";
		try {
			conn = ConnectionPool.getInstance().checkOut();
                        
                        ps = conn.prepareStatement(query);
                        ps.setString(1, modelTelefona.getNazivModela());
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
                            
				while(rs.next()) {
					modeli.add(new ModelTelefonaDTO(rs.getInt("idModeltelefona"),rs.getString("Specifikacija"),rs.getString("Slika"),rs.getString("NazivModela")));
                                        for(ModelTelefonaDTO mt : modeli){
                                            System.out.println(mt.getNazivModela());
                                        }
                                }
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return modeli;
	}

}