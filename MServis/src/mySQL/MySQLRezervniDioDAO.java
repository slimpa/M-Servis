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
        
    
            
        public static final String SQL_INSERT = "insert into proizvodjac values (?, ?, ?)";
	public static final String SQL_SELECT = "select * from rezervnidio";
	public static final String SQL_UPDATE = "update proizvodjac set";
        public static final String SQL_SELECT_DETAIL = "select * from rezervni_dijelovi";
	/**
	 * 
	 * @param rezervniDio
	 */
	public boolean insert(RezervniDioDTO rezervniDio) {
		// TODO - implement MySQLRezervniDioDAO.insert
		throw new UnsupportedOperationException();
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
		// TODO - implement MySQLRezervniDioDAO.delete
		throw new UnsupportedOperationException();
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
					rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis")));
                                        
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
		String query = SQL_SELECT_DETAIL + " and artikal.Naziv = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setString(1, naziv);
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis"),rs.getString("Naziv"),rs.getInt("Kolicina"),rs.getInt("cijena")));
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
                                    rezervniDijelovi.add(new RezervniDioDTO(rs.getInt("IdRezervniDio"),rs.getInt("IdModelTelefona"),rs.getString("Opis"),rs.getString("Naziv"),rs.getInt("Kolicina"),rs.getInt("cijena")));
                                   
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

}