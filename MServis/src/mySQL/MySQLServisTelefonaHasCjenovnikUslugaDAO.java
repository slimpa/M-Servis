package mySQL;

import dao.ServisTelefonaHasCjenovnikUslugaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ServisTelefonaHasCjenovnikUslugaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

public class MySQLServisTelefonaHasCjenovnikUslugaDAO implements ServisTelefonaHasCjenovnikUslugaDAO {

    /**
     *
     * @param servisCjenovnik
     */
    private static final String SQL_INSERT = "insert into servistelefona_has_cijenovnikusluga(IdServisTelefona, IdCijenovnikUsluga) values (?, ?)";
    private static final String SQL_SELECT = "select * from usluga_servis";

    public boolean insert(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, servisCjenovnik.getIdServistelefona());
            ps.setInt(2, servisCjenovnik.getIdCjenovnikUsluga());
            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param servisCjenovnik
     */
    public boolean update(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik) {
        // TODO - implement MySQLServisTelefonaHasCjenovnikUslugaDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param servisCjenovnik
     */
    public boolean delete(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik) {
        // TODO - implement MySQLServisTelefonaHasCjenovnikUslugaDAO.delete
        throw new UnsupportedOperationException();
    }

    public List<ServisTelefonaHasCjenovnikUslugaDTO> selectAll() {
        List<ServisTelefonaHasCjenovnikUslugaDTO> usluge = new ArrayList<ServisTelefonaHasCjenovnikUslugaDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    usluge.add(new ServisTelefonaHasCjenovnikUslugaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdCijenovnikUsluga"), rs.getString("Naziv")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return usluge;
    }

    /**
     *
     * @param servisCjenobnik
     */
    public List<ServisTelefonaHasCjenovnikUslugaDTO> selectBy(ServisTelefonaHasCjenovnikUslugaDTO servisCjenovnik) {
        List<ServisTelefonaHasCjenovnikUslugaDTO> usluge = new ArrayList<ServisTelefonaHasCjenovnikUslugaDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdServisTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, servisCjenovnik.getIdServistelefona());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    usluge.add(new ServisTelefonaHasCjenovnikUslugaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdCijenovnikUsluga"), rs.getString("Naziv")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return usluge;
    }

}
