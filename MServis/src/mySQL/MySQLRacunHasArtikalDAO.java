package mySQL;

import dao.RacunHasArtikalDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.RacunHasArtikalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

public class MySQLRacunHasArtikalDAO implements RacunHasArtikalDAO {

    
    public static final String SQL_INSERT = "INSERT INTO `racun_has_artikal` (`IdRacun`, `IdArtikal`, `Kolicina`) VALUES (?,?,?);";
	/**
	 * 
	 * @param racunArtikal
	 */
	public boolean insert(RacunHasArtikalDTO racunArtikal) {
               Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, racunArtikal.getIdRacun());
            ps.setInt(2, racunArtikal.getIdArtikal());
            ps.setInt(3, racunArtikal.getKolicina());
            returnValue = ps.executeUpdate() == 1;

        } catch (SQLException e) {
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
	 * @param racunArtikal
	 */
	public boolean update(RacunHasArtikalDTO racunArtikal) {
		// TODO - implement MySQLRacunHasArtikalDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param racunArtikal
	 */
	public boolean delete(RacunHasArtikalDTO racunArtikal) {
		// TODO - implement MySQLRacunHasArtikalDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<RacunHasArtikalDTO> selectAll() {
		// TODO - implement MySQLRacunHasArtikalDAO.selectAll
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param racunArtikal
	 */
	public List<RacunHasArtikalDTO> selectBy(RacunHasArtikalDTO racunArtikal) {
		// TODO - implement MySQLRacunHasArtikalDAO.selectBy
		throw new UnsupportedOperationException();
	}

}