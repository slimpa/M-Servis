package mySQL;

import dao.CjenovnikUslugaDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
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
    public static final String SQL_INSERT = "insert into CjenovnikUsluga values (?, ?)";
    public static final String SQL_SELECT = "select * from CjenovnikUsluga";
    public static final String SQL_UPDATE = "update CjenovnikUsluga set";
    public static final String SQL_DELETE = "delete from CjenovnikUsluga";

    public boolean insert(CjenovnikUslugaDTO cjenovnikUsluga) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, cjenovnikUsluga.getIdCjenovnikUsluga());
            ps.setString(2, cjenovnikUsluga.getNaziv());
            ps.setDouble(3, cjenovnikUsluga.getCijena());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
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
        String query = SQL_UPDATE + " Naziv = ?, Cijena = ? where IdCjenovnikUsluga = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, cjenovnikUsluga.getNaziv());
            ps.setDouble(2, cjenovnikUsluga.getCijena());
            ps.setInt(3, cjenovnikUsluga.getIdCjenovnikUsluga());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
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
        String query = SQL_DELETE + " where IdCjenovnikUsluga = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, cjenovnikUsluga.getIdCjenovnikUsluga());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<CjenovnikUslugaDTO> selectAll() {
        ArrayList<CjenovnikUslugaDTO> lista = new ArrayList<CjenovnikUslugaDTO>();
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
                    lista.add(new CjenovnikUslugaDTO(rs.getInt("IdCjenovnikUsluga"), rs.getString("Naziv"), rs.getDouble("Cijena")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(stat);
        }

        return lista;
    }

    /**
     *
     * @param cjenovnikUsluga
     */
    public CjenovnikUslugaDTO selectById(int id) {
        CjenovnikUslugaDTO cjenovnik = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdCjenovnikUsluga like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                cjenovnik = new CjenovnikUslugaDTO(rs.getInt("IdCjenovnikUsluga"), rs.getString("Naziv"), rs.getDouble("Cijena"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return cjenovnik;
    }

    @Override
    public List<CjenovnikUslugaDTO> selectBy(CjenovnikUslugaDTO cjenovnikUsluga) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
