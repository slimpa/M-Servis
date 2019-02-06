package mySQL;

import dao.RacunHasServisTelefonaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.RacunHasServisTelefonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.List;

public class MySQLRacunHasServisTelefonaDAO implements RacunHasServisTelefonaDAO {

    /**
     *
     * @param racunServisTelefona
     */
    private static final String SQL_INSERT = "insert into racun_has_servistelefona(IdRacun, IdServisTelefona) values (?, ?)";

    public boolean insert(RacunHasServisTelefonaDTO racunServisTelefona) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, racunServisTelefona.getIdRacun());
            ps.setInt(2, racunServisTelefona.getIdServisa());
 
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
     * @param racunServisTelefona
     */
    public boolean update(RacunHasServisTelefonaDTO racunServisTelefona) {
        // TODO - implement MySQLRacunHasServisTelefonaDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param racunServisTelefona
     */
    public boolean delete(RacunHasServisTelefonaDTO racunServisTelefona) {
        // TODO - implement MySQLRacunHasServisTelefonaDAO.delete
        throw new UnsupportedOperationException();
    }

    public List<RacunHasServisTelefonaDTO> selectAll() {
        // TODO - implement MySQLRacunHasServisTelefonaDAO.selectAll
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param racunServisTelefona
     */
    public List<RacunHasServisTelefonaDTO> selectBy(RacunHasServisTelefonaDTO racunServisTelefona) {
        // TODO - implement MySQLRacunHasServisTelefonaDAO.selectBy
        throw new UnsupportedOperationException();
    }

}
