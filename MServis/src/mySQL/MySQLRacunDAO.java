package mySQL;

import dao.RacunDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.RacunDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;

public class MySQLRacunDAO implements RacunDAO {

    /**
     *
     * @param racun
     */
    public static final String SQL_INSERT = "INSERT INTO `racun` (`Vrijeme`, `UkupnaCijena`, `IdIzdavac`) VALUES (?,?,?);";
    public static final String LAST_ID = "SELECT LAST_INSERT_ID() as Id";
    
    public boolean insert(RacunDTO racun) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setTimestamp(1, racun.getVrijeme());
            ps.setDouble(2, racun.getUkupnaCijena());
            ps.setInt(3, racun.getIdZaposleni());
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
     * @param racun
     */
    public boolean update(RacunDTO racun) {
        // TODO - implement MySQLRacunDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param racun
     */
    public boolean delete(RacunDTO racun) {
        // TODO - implement MySQLRacunDAO.delete
        throw new UnsupportedOperationException();
    }

    public List<RacunDTO> selectAll() {
        // TODO - implement MySQLRacunDAO.selectAll
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param racun
     */
    public List<RacunDTO> selectBy(RacunDTO racun) {
        // TODO - implement MySQLRacunDAO.selectBy
        throw new UnsupportedOperationException();
    }

    
     public int getId() {
        Connection conn = null;
        Statement stat = null;

        long insertId = 0;
        try {
            conn = ConnectionPool.getInstance().checkOut();
            PreparedStatement getLastInsertId = conn.prepareStatement("select max(IdRacun) from racun");
            ResultSet rs = getLastInsertId.executeQuery();
            if (rs.next()) {
                insertId = rs.getLong("max(IdRacun)");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return (int) insertId;
    }
}
