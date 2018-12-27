package mySQL;

import dao.AdminDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.AdminDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLAdminDAO implements AdminDAO {

    /**
     *
     * @param admin
     */
    public static final String SQL_INSERT = "insert into Admin values (?, ?, ?, ?, ?)";
    public static final String SQL_SELECT = "select * from Admin";
    public static final String SQL_UPDATE = "update Admin set";
    public static final String SQL_DELETE = "delete from Admin";

    public boolean insert(AdminDTO admin) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, admin.getIdOsoba());
            ps.setString(2, admin.getIme());
            ps.setString(3, admin.getPrezime());
            ps.setString(4, admin.getBrojTelefona());
            ps.setString(5, admin.getNazivFirme());

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
     * @param admin
     */
    public boolean update(AdminDTO admin) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Ime = ?, Prezime = ?, BrojTelefona = ?, NazivFirme = ? where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, admin.getIme());
            ps.setString(2, admin.getPrezime());
            ps.setString(3, admin.getBrojTelefona());
            ps.setString(4, admin.getNazivFirme());
            ps.setInt(5, admin.getIdOsoba());

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
     * @param admin
     */
    public boolean delete(AdminDTO admin) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, admin.getIdOsoba());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<AdminDTO> selectAll() {
        ArrayList<AdminDTO> admini = new ArrayList<AdminDTO>();
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
                    admini.add(new AdminDTO(rs.getInt("IdOsoba"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("BrojTelefona"), rs.getString("NazivFirme")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(stat);
        }

        return admini;
    }

    /**
     *
     * @param admin
     */
    public AdminDTO selectById(int id) {
        AdminDTO admin = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdOsoba like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new AdminDTO(rs.getInt("IdOsoba"), rs.getString("Ime"), rs.getString("Prezime"), rs.getString("BrojTelefona"), rs.getString("NazivFirme"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return admin;
    }

    @Override
    public List<AdminDTO> selectBy(AdminDTO admin) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
