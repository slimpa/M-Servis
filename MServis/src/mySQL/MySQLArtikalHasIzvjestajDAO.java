package mySQL;

import dao.ArtikalHasIzvjestajDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.ArtikalHasIzvjestajDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLArtikalHasIzvjestajDAO implements ArtikalHasIzvjestajDAO {

    /**
     *
     * @param artikalIzvjestaj
     */
    public static final String SQL_INSERT = "insert into ArtikalHasIzvjestaj values (?, ?)";
    public static final String SQL_SELECT = "select * from ArtikalHasIzvjestaj";
    public static final String SQL_UPDATE = "update ArtikalHasIzvjestaj set";
    public static final String SQL_DELETE = "delete from ArtikalHasIzvjestaj";

    public boolean insert(ArtikalHasIzvjestajDTO artikalIzvjestaj) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, artikalIzvjestaj.getIdIzvjestaj());
            ps.setInt(2, artikalIzvjestaj.getIdArtikal());

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
     * @param artikalIzvjestaj
     */
    public boolean update(ArtikalHasIzvjestajDTO artikalIzvjestaj) {
        // TODO - implement MySQLArtikalHasIzvjestajDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param artikalIzvjestaj
     */
    public boolean delete(ArtikalHasIzvjestajDTO artikalIzvjestaj) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdIzvjestaj = ? and IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, artikalIzvjestaj.getIdIzvjestaj());
            ps.setInt(2, artikalIzvjestaj.getIdArtikal());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<ArtikalHasIzvjestajDTO> selectAll() {
        ArrayList<ArtikalHasIzvjestajDTO> lista = new ArrayList<ArtikalHasIzvjestajDTO>();
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
                    lista.add(new ArtikalHasIzvjestajDTO(rs.getInt("IdIzvjestaj"), rs.getInt("IdArtikal")));
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
     * @param artikalIzvjestaj
     */
    public ArtikalHasIzvjestajDTO selectByIdArtikal(int idArtikal) {
        ArtikalHasIzvjestajDTO artikalIzvjestaj = null;
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
                artikalIzvjestaj = new ArtikalHasIzvjestajDTO(rs.getInt("IdIzvjestaj"), rs.getInt("IdArtikal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return artikalIzvjestaj;
    }

    public ArtikalHasIzvjestajDTO selectByIdIzvjestaj(int idIzvjestaj) {
        ArtikalHasIzvjestajDTO artikalIzvjestaj = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdIzvjestaj like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, idIzvjestaj);
            rs = ps.executeQuery();

            if (rs.next()) {
                artikalIzvjestaj = new ArtikalHasIzvjestajDTO(rs.getInt("IdIzvjestaj"), rs.getInt("IdArtikal"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return artikalIzvjestaj;
    }

    @Override
    public List<ArtikalHasIzvjestajDTO> selectBy(ArtikalHasIzvjestajDTO artikalIzvjestaj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
