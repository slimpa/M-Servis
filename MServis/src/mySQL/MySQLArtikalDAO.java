package mySQL;

import dao.ArtikalDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.ArtikalDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLArtikalDAO implements ArtikalDAO {

    /**
     *
     * @param artikal
     */
    public static final String SQL_INSERT = "insert into Artikal values (?, ?, ?, ?)";
    public static final String SQL_SELECT = "select * from Artikal";
    public static final String SQL_UPDATE = "update Artikal set";
    public static final String SQL_DELETE = "delete from Artikal";

    public boolean insert(ArtikalDTO artikal) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, artikal.getIdArtikal());
            ps.setString(2, artikal.getNaziv());
            ps.setDouble(3, artikal.getCijena());
            ps.setInt(4, artikal.getIdProizvodjac());

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
     * @param artikal
     */
    public boolean update(ArtikalDTO artikal) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Naziv = ?, Cijena = ?, IdProizvodjac = ? where IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, artikal.getNaziv());
            ps.setDouble(2, artikal.getCijena());
            ps.setInt(3, artikal.getIdProizvodjac());
            ps.setInt(4, artikal.getIdArtikal());

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
     * @param artikal
     */
    public boolean delete(ArtikalDTO artikal) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, artikal.getIdArtikal());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<ArtikalDTO> selectAll() {
        ArrayList<ArtikalDTO> artikli = new ArrayList<ArtikalDTO>();
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
                    artikli.add(new ArtikalDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"), rs.getInt("IdProizvodjac")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(stat);
        }

        return artikli;
    }

    /**
     *
     * @param artikal
     */
    public ArtikalDTO selectById(int id) {
        ArtikalDTO artikal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdArtikal like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                artikal = new ArtikalDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"), rs.getInt("IdProizvodjac"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return artikal;
    }

    @Override
    public List<ArtikalDTO> selectBy(ArtikalDTO artikal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
