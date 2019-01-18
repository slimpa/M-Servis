package mySQL;

import dao.CjenovnikUslugaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.CjenovnikUslugaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLCjenovnikUslugaDAO implements CjenovnikUslugaDAO {

    /**
     *
     * @param cjenovnikUsluga
     */
    public static final String SQL_INSERT = "insert into cijenovnikusluga(Naziv, Cijena, Obrisano) values (?, ?, ?);";
    public static final String SQL_SELECT = "select * from cijenovnikusluga where Obrisano = 0";
    public static final String SQL_UPDATE = "update cijenovnikusluga set";

    public boolean insert(CjenovnikUslugaDTO cjenovnikUsluga) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, cjenovnikUsluga.getNaziv());
            ps.setDouble(2, cjenovnikUsluga.getCijena());
            ps.setInt(3, 0);
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
     * @param cjenovnikUsluga
     */
    public boolean update(CjenovnikUslugaDTO cjenovnikUsluga) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Cijena = ?, Naziv = ? where IdCijenovnikUsluga = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setDouble(1, cjenovnikUsluga.getCijena());
            ps.setString(2, cjenovnikUsluga.getNaziv());
            ps.setInt(3, cjenovnikUsluga.getIdCjenovnikUsluga());
            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
            //e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param cjenovnikUsluga
     */
    public boolean delete(CjenovnikUslugaDTO cjenovnikUsluga) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Obrisano = 1 where IdCijenovnikUsluga = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, cjenovnikUsluga.getIdCjenovnikUsluga());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<CjenovnikUslugaDTO> selectAll() {
        List<CjenovnikUslugaDTO> cjenovnik = new ArrayList<CjenovnikUslugaDTO>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stat = conn.createStatement();
            rs = stat.executeQuery(SQL_SELECT);

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    cjenovnik.add(new CjenovnikUslugaDTO(rs.getInt("IdCijenovnikUsluga"), rs.getString("Naziv"), rs.getDouble("Cijena")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return cjenovnik;
    }

    /**
     *
     * @param cjenovnikUsluga
     */
    public List<CjenovnikUslugaDTO> selectBy(CjenovnikUslugaDTO cjenovnikUsluga) {
        List<CjenovnikUslugaDTO> cjenovnik = new ArrayList<CjenovnikUslugaDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " and Naziv=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, cjenovnikUsluga.getNaziv());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    cjenovnik.add(new CjenovnikUslugaDTO(rs.getInt("IdCijenovnikUsluga"), rs.getString("Naziv"), rs.getDouble("Cijena")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return cjenovnik;
    }

}
