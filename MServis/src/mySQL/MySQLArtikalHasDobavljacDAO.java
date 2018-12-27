package mySQL;

import dao.ArtikalHasDobavljacDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.ArtikalHasDobavljacDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLArtikalHasDobavljacDAO implements ArtikalHasDobavljacDAO {

    /**
     *
     * @param artikalDobavljac
     */
    public static final String SQL_INSERT = "insert into ArtikalHasDobavljac values (?, ?)";
    public static final String SQL_SELECT = "select * from ArtikalHasDobavljac";
    public static final String SQL_UPDATE = "update ArtikalHasDobavljac set";
    public static final String SQL_DELETE = "delete from ArtikalHasDobavljac";

    public boolean insert(ArtikalHasDobavljacDTO artikalDobavljac) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, artikalDobavljac.getIdDobavljac());
            ps.setInt(2, artikalDobavljac.getIdArtikal());

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
     * @param artikalDobavljac
     */
    public boolean update(ArtikalHasDobavljacDTO artikalDobavljac) {
        // TODO - implement MySQLArtikalHasDobavljacDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param artikalDobavljac
     */
    public boolean delete(ArtikalHasDobavljacDTO artikalDobavljac) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdDobavljac = ? and IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, artikalDobavljac.getIdDobavljac());
            ps.setInt(2, artikalDobavljac.getIdArtikal());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<ArtikalHasDobavljacDTO> selectAll() {
        ArrayList<ArtikalHasDobavljacDTO> lista = new ArrayList<ArtikalHasDobavljacDTO>();
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
                    lista.add(new ArtikalHasDobavljacDTO(rs.getInt("IdDobavljac"), rs.getInt("IdArtikal")));
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
     * @param artikalDobavljac
     */
    public ArtikalHasDobavljacDTO selectByIdArtikal(int idArtikal) {
        ArtikalHasDobavljacDTO artikalDobavljac = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdArtikal like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idArtikal);
            rs = ps.executeQuery();

            if (rs.next()) {
                artikalDobavljac = new ArtikalHasDobavljacDTO(rs.getInt("IdDobavljac"), rs.getInt("IdArtikal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return artikalDobavljac;
    }

    public ArtikalHasDobavljacDTO selectByIdDobavljac(int idDobavljac) {
        ArtikalHasDobavljacDTO artikalDobavljac = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdDobavljac like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idDobavljac);
            rs = ps.executeQuery();

            if (rs.next()) {
                artikalDobavljac = new ArtikalHasDobavljacDTO(rs.getInt("IdDobavljac"), rs.getInt("IdArtikal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return artikalDobavljac;
    }

    @Override
    public List<ArtikalHasDobavljacDTO> selectBy(ArtikalHasDobavljacDTO artikalDobavljac) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
