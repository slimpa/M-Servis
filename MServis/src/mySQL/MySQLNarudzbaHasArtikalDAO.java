package mySQL;

import dao.NarudzbaHasArtikalDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.NarudzbaHasArtikalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class MySQLNarudzbaHasArtikalDAO implements NarudzbaHasArtikalDAO {
        
        
       
	public static final String SQL_SELECT_ALL="SELECT n.IdNarudzba,n.IdArtikal,n.Kolicina,a.Naziv from narudzba_has_artikal n inner join Artikal a where n.IdArtikal=a.IdArtikal";
        public static final String SQL_INSERT = "INSERT INTO narudzba_has_artikal (IdNarudzba,IdArtikal,Kolicina) VALUES (?, ?, ?)";
	public static final String SQL_DELETE="DELETE FROM narudzba_has_artikal WHERE IdNarudzba=? and IdArtikal=?";
        
        public boolean insert(NarudzbaHasArtikalDTO narudzbaArtikal) {
		Connection conn = null;
		PreparedStatement ps = null;
		int returnValue = 0;
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_INSERT);
                        ps.setInt(1,narudzbaArtikal.getIdNarudzba());
			ps.setInt(2, narudzbaArtikal.getIdArtikal());
                        ps.setInt(3,narudzbaArtikal.getKolicina());   
                        returnValue=ps.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue==1;
	}

	public boolean delete(NarudzbaHasArtikalDTO narudzbaArtikal) {
		boolean returnValue = false;
		Connection conn = null;
		PreparedStatement ps = null;

		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(SQL_DELETE);
			ps.setInt(1, narudzbaArtikal.getIdNarudzba());
			ps.setInt(2, narudzbaArtikal.getIdArtikal());
			returnValue = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);			
		}
		
		return returnValue;
	}

	public List<NarudzbaHasArtikalDTO> selectAll() {
		List<NarudzbaHasArtikalDTO> narudzbe = new ArrayList<NarudzbaHasArtikalDTO>();
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
					narudzbe.add(new NarudzbaHasArtikalDTO(rs.getInt("IdNarudzba"),rs.getInt("IdArtikal"),rs.getInt("Kolicina"),rs.getString("Naziv")));
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

	

}