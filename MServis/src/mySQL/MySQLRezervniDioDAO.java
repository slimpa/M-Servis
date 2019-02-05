package mySQL;

import dao.RezervniDioDAO;
import dbu.*;
import dbu.ConnectionPool;
import dto.RezervniDioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;


public class MySQLRezervniDioDAO implements RezervniDioDAO {
        
    
            
        public static final String SQL_INSERT = "INSERT INTO `m:servis`.`rezervniDio` (`IdRezervniDio`,`Opis`,`IdModelTelefona`) VALUES (?, ?, ?)";
	public static final String SQL_SELECT = "select * from rezervnidio";
        public static final String SQL_SELECT_DETAIL = "select * from rezervni_dijelovi";
         public static final String SQL_DELETE = "DELETE FROM `m:servis`.`rezervnidio` WHERE `IdRezervniDio`=?";
	/**
	 * 
	 * @param rezervniDio
	 */
	public boolean insert(RezervniDioDTO rezervniDio) {
                Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
			ps.setInt(1, rezervniDio.getIdRezervniDio());
			ps.setString(2, rezervniDio.getOpis());
                        ps.setInt(3, rezervniDio.getIdModelTelefona());
                        
                        
                        
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
	 * @param rezervniDio
	 */
	public boolean update(RezervniDioDTO rezervniDio) {
		// TODO - implement MySQLRezervniDioDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param rezervniDio
	 */
	public boolean delete(RezervniDioDTO rezervniDio) {
		Connection conn = null;
		PreparedStatement ps = null;
		boolean returnValue = false;
                try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);

			ps.setInt(1, rezervniDio.getIdRezervniDio());
			
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

			ps.setInt(1, rezervniDio.getIdRezervniDio());
			
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return returnValue;
	}

	public List<RezervniDioDTO> selectAll() {
		List<RezervniDioDTO> rezervniDijelovi = new ArrayList<RezervniDioDTO>();
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
					rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis"),rs.getString("Naziv"),rs.getInt("Kolicina"),rs.getDouble("Cijena")));
                                        
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return rezervniDijelovi;
	}

	/**
	 * 
	 * @param rezervniDio
	 */
	public List<RezervniDioDTO> selectBy(String naziv) {
		List<RezervniDioDTO> rezervniDijelovi = new ArrayList<RezervniDioDTO>();
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
					rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis"),rs.getString("Naziv"),rs.getInt("Kolicina"),rs.getDouble("cijena")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return rezervniDijelovi;
	}
        
        
        public List<RezervniDioDTO> selectAllDetailed() {
            List<RezervniDioDTO> rezervniDijelovi = new ArrayList<RezervniDioDTO>();
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
                                    rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis"),rs.getString("Naziv"),rs.getInt("Kolicina"),rs.getDouble("cijena")));
                                   
                            }
                    }
            } catch(SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.getInstance().checkIn(conn);
                    DBUtil.getInstance().close(stat);
            }

            return rezervniDijelovi;
        }
		
	public List<RezervniDioDTO> selectByModel(RezervniDioDTO rezervniDio) {
        List<RezervniDioDTO> rezervniDijelovi = new ArrayList<RezervniDioDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT_NAZIV + " where IdModelTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, rezervniDio.getIdModelTelefona());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    rezervniDijelovi.add(new RezervniDioDTO(rs.getString("Naziv"), rs.getInt("IdRezervniDio")));
                  
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return rezervniDijelovi;
    }

}