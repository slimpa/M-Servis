package mySQL;

import dao.ArtikalDAO;
import dbu.ConnectionPool;
import dto.ArtikalDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import dbu.DBUtil;
import java.sql.PreparedStatement;

import java.util.List;

public class MySQLArtikalDAO implements ArtikalDAO {

    
    
        public static final String SQL_INSERT = "insert into artikal (`Naziv`, `IdProizvodjac`, `BarKod`, `Kolicina`, `Obrisano`) values (?, ?, ?, ?, ?)";
	public static final String SQL_SELECT = "select * from artikal where Obrisano=0 order by Kolicina";
        public static final String SQL_UPDATE_ARTIKAL = "update artikal set Naziv= ?, Kolicina =? ,IdProizvodjac = ? ,BarKod = ? where IdArtikal = ?";
	public static final String SQL_UPDATE = "update artikal set kolicina=kolicina+1 where IdArtikal=?";
        //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano
	/**
	 * 
	 * @param artikal
	 */
        @Override
	public boolean insert(ArtikalDTO artikal) {
	    Connection conn = null;
            PreparedStatement ps = null;
            boolean returnValue = false;

            try {

               //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano

                    conn = ConnectionPool.getInstance().checkOut();
                    ps = conn.prepareStatement(SQL_INSERT);
                    
                    ps.setString(1, artikal.getNaziv());
                    ps.setInt(4, artikal.getKolicina());
                    ps.setInt(2, artikal.getIdProizvodjac());
                    ps.setString(3, artikal.getBarKod());
                    ps.setInt(5, 0);
                    
                    returnValue = ps.executeUpdate() == 1;
                
            } catch(SQLException e) {
                    e.printStackTrace();
                    return false;
            } finally {
                    ConnectionPool.getInstance().checkIn(conn);
                    DBUtil.getInstance().close(ps);			
            }

            return returnValue;
	}
        
        public boolean updateArtikal(ArtikalDTO artikal) {
	    Connection conn = null;
            PreparedStatement ps = null;
            boolean returnValue = false;

            try {

               //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano

                    conn = ConnectionPool.getInstance().checkOut();
                    ps = conn.prepareStatement(SQL_UPDATE_ARTIKAL);
                    
                    ps.setString(1, artikal.getNaziv());
                    ps.setInt(2, artikal.getKolicina());
                    ps.setInt(3, artikal.getIdProizvodjac());
                    ps.setString(4, artikal.getBarKod());
                    ps.setInt(5, artikal.getIdArtikal());
                    
                    returnValue = ps.executeUpdate() == 1;
                
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
	 * @param artikal
	 */
	public boolean update(ArtikalDTO artikal) {
		Connection conn = null;
            PreparedStatement ps = null;
            boolean returnValue = false;

            try {

               //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano

                    conn = ConnectionPool.getInstance().checkOut();
                    ps = conn.prepareStatement(SQL_UPDATE);
                    
                    ps.setInt(1, artikal.getIdArtikal());
                   
                    
                    returnValue = ps.executeUpdate() == 1;
                
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
	 * @param artikal
	 */
	public boolean delete(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<ArtikalDTO> selectAll() {
		List<ArtikalDTO> artikli = new ArrayList<ArtikalDTO>();
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
					artikli.add(new ArtikalDTO(rs.getInt("idArtikal"),rs.getString("Naziv"),
                                                rs.getInt("Kolicina"),rs.getInt("idProizvodjac"),rs.getString("BarKod"),rs.getBoolean("Obrisano")));
                                        
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(stat);
		}
		
		return artikli;
	}

	/**
	 * 
	 * @param artikal
	 */
	public List<ArtikalDTO> selectBy(ArtikalDTO artikal) {
		// TODO - implement MySQLArtikalDAO.selectBy
		throw new UnsupportedOperationException();
	}
        
        public Integer getLastId(){
            Connection conn = null;
            Statement stat = null;
            ResultSet rs = null;
            Integer lastId = null;

            try {
                    conn = ConnectionPool.getInstance().checkOut();
                    stat = conn.createStatement();
                    rs = stat.executeQuery("SELECT MAX(IdArtikal) FROM artikal;");
                    if (rs.next()){
                         lastId=rs.getInt(1);
                    }
                    
            } catch(SQLException e) {
                    e.printStackTrace();
            } finally {
                    ConnectionPool.getInstance().checkIn(conn);
                    DBUtil.getInstance().close(stat);
            }
            System.out.println();
            return lastId;
        }

}