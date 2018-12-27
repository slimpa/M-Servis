package mySQL;

import dao.DobavljacDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.DobavljacDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLDobavljacDAO implements DobavljacDAO {

    /**
     *
     * @param dobavljac
     */
    public static final String SQL_INSERT = "insert into Dobavljac values (?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT = "select * from Dobavljac";
    public static final String SQL_UPDATE = "update Dobavljac set";
    public static final String SQL_DELETE = "delete from Dobavljac";

    public boolean insert(DobavljacDTO dobavljac) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, dobavljac.getIdDobavljac());
            ps.setString(2, dobavljac.getNaziv());
            ps.setString(3, dobavljac.getAdresa());
            ps.setString(4, dobavljac.getTelefon());
            ps.setString(5, dobavljac.getEmail());
            ps.setString(6, dobavljac.getFax());

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
     * @param dobavljac
     */
    public boolean update(DobavljacDTO dobavljac) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Naziv = ?, Adresa = ?, Telefon = ?, Email = ?, Fax = ? where IdDobavljac = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, dobavljac.getNaziv());
            ps.setString(2, dobavljac.getAdresa());
            ps.setString(3, dobavljac.getTelefon());
            ps.setString(4, dobavljac.getEmail());
            ps.setString(5, dobavljac.getFax());
            ps.setInt(6, dobavljac.getIdDobavljac());

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
     * @param dobavljac
     */
    public boolean delete(DobavljacDTO dobavljac) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdDobavljac = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dobavljac.getIdDobavljac());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<DobavljacDTO> selectAll() {
        ArrayList<DobavljacDTO> lista = new ArrayList<DobavljacDTO>();
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
                    lista.add(new DobavljacDTO(rs.getInt("IdDobavljac"), rs.getString("Naziv"), rs.getString("Adresa"),
                            rs.getString("Telefon"), rs.getString("Email"), rs.getString("Fax")));
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
     * @param dobavljac
     */
    public DobavljacDTO selectById(int id) {
        DobavljacDTO dobavljac = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdDobavljac like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dobavljac = new DobavljacDTO(rs.getInt("IdDobavljac"), rs.getString("Naziv"), rs.getString("Adresa"),
                        rs.getString("Telefon"), rs.getString("Email"), rs.getString("Fax"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return dobavljac;
    }

    @Override
    public List<DobavljacDTO> selectBy(DobavljacDTO dobavljac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
