package mySQL;

import dao.CijenaDAO;
import dbu.ConnectionPool;
import dto.CijenaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;
import dbu.*;


public class MySQLCijenaDAO implements CijenaDAO {

    
        public static final String SQL_INSERT = "INSERT INTO `m:servis`.`cijena` (`IdArtikla`,`Cijena`, `DatumOd`, `DatumDo`,`TrenutnaCijena`) VALUES (?, ?, ?, ?, ?);";
	public static final String SQL_SELECT = "select * from artikal";
	public static final String SQL_UPDATE = "update cijena set";
        public static final String SQL_DELETE = "update cijena set DatumDo= ?, TrenutnaCijena=? where IdArtikla =? and TrenutnaCijena=1";
        
	/**
	 * 
	 * @param cijena
	 */
	public boolean insert(CijenaDTO cijena) {
            Connection conn = null;
            PreparedStatement ps = null;
            boolean returnValue = false;

            try {

               //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano

                    conn = ConnectionPool.getInstance().checkOut();
                    ps = conn.prepareStatement(SQL_INSERT);
                    
                    ps.setInt(1, cijena.getIdArtikal());
                    ps.setDouble(2,  cijena.getCijena());
                    ps.setTimestamp(3, cijena.getDatumOd());
                    ps.setTimestamp(4, null);
                    ps.setInt(5, 1);
                    
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
	 * @param cijena
	 */
	public boolean update(CijenaDTO cijena) {
		// TODO - implement MySQLCijenaDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cijena
	 */
	public boolean delete(CijenaDTO cijena) {
                Connection conn = null;
                PreparedStatement ps = null;
                boolean returnValue = false;

                try {

                   //int idArtikal, String Naziv, int Kolicina, int idProizvodjac, String BarKod, boolean Obrisano

                        conn = ConnectionPool.getInstance().checkOut();
                        ps = conn.prepareStatement(SQL_DELETE);

                        ps.setInt(3, cijena.getIdArtikal());
                        ps.setTimestamp(1,  cijena.getDatumDo());
                        ps.setInt(2, 0);


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

	public List<CijenaDTO> selectAll() {
		// TODO - implement MySQLCijenaDAO.selectAll
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param cijena
	 */
	public List<CijenaDTO> selectBy(CijenaDTO cijena) {
		// TODO - implement MySQLCijenaDAO.selectBy
		throw new UnsupportedOperationException();
	}

}