package mySQL;

import dao.DodatnaOpremaDAO;
import dbu.ConnectionPool;
import dbu.DBUtilities;
import dto.DodatnaOpremaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLDodatnaOpremaDAO implements DodatnaOpremaDAO {

    /**
     *
     * @param dodatnaOprema
     */
    public static final String SQL_INSERT = "insert into DodatnaOprema values (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SQL_SELECT = "select * from DodatnaOprema";
    public static final String SQL_UPDATE = "update DodatnaOprema set";
    public static final String SQL_DELETE = "delete from DodatnaOprema";

    public boolean insert(DodatnaOpremaDTO dodatnaOprema) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);

            ps.setInt(1, dodatnaOprema.getIdArtikal());
            ps.setString(2, dodatnaOprema.getNaziv());
            ps.setDouble(3, dodatnaOprema.getCijena());
            ps.setInt(4, dodatnaOprema.getIdProizvodjac());
            ps.setInt(5, dodatnaOprema.getIdModelTelefona());
            ps.setInt(6, dodatnaOprema.getIdTipDodatneOpreme());
            ps.setInt(7, dodatnaOprema.getKolicina());
            ps.setString(8, dodatnaOprema.getBoja());

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
     * @param dodatnaOprema
     */
    public boolean update(DodatnaOpremaDTO dodatnaOprema) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Naziv = ?, Cijena = ?, IdProizvodjac = ?, IdModelTelefona = ?, IdTipDodatneOpreme = ?,"
                + " Kolicina = ?, Boja = ? where IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, dodatnaOprema.getNaziv());
            ps.setDouble(2, dodatnaOprema.getCijena());
            ps.setInt(3, dodatnaOprema.getIdProizvodjac());
            ps.setInt(4, dodatnaOprema.getIdModelTelefona());
            ps.setInt(5, dodatnaOprema.getIdTipDodatneOpreme());
            ps.setInt(6, dodatnaOprema.getKolicina());
            ps.setString(7, dodatnaOprema.getBoja());
            ps.setInt(8, dodatnaOprema.getIdArtikal());

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
     * @param dodatnaOprema
     */
    public boolean delete(DodatnaOpremaDTO dodatnaOprema) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_DELETE + " where IdArtikal = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, dodatnaOprema.getIdArtikal());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<DodatnaOpremaDTO> selectAll() {
        ArrayList<DodatnaOpremaDTO> lista = new ArrayList<DodatnaOpremaDTO>();
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
                    lista.add(new DodatnaOpremaDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"),
                            rs.getInt("IdProizvodjac"), rs.getInt("IdModelTelefona"), rs.getInt("IdTipDodatneOpreme"),
                            rs.getInt("Kolicina"), rs.getString("Boja")));
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
     * @param dodatnaOprema
     */
    public DodatnaOpremaDTO selectById(int id) {
        DodatnaOpremaDTO dodatnaOprema = null;
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
                dodatnaOprema = new DodatnaOpremaDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"),
                        rs.getInt("IdProizvodjac"), rs.getInt("IdModelTelefona"), rs.getInt("IdTipDodatneOpreme"),
                        rs.getInt("Kolicina"), rs.getString("Boja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return dodatnaOprema;
    }

    public DodatnaOpremaDTO selectByIdModelTelefona(int id) {
        DodatnaOpremaDTO dodatnaOprema = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdModelTelefona like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dodatnaOprema = new DodatnaOpremaDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"),
                        rs.getInt("IdProizvodjac"), rs.getInt("IdModelTelefona"), rs.getInt("IdTipDodatneOpreme"),
                        rs.getInt("Kolicina"), rs.getString("Boja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return dodatnaOprema;
    }

    public DodatnaOpremaDTO selectByIdTipDodatneOpreme(int id) {
        DodatnaOpremaDTO dodatnaOprema = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdTipDodatneOpreme like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                dodatnaOprema = new DodatnaOpremaDTO(rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getDouble("Cijena"),
                        rs.getInt("IdProizvodjac"), rs.getInt("IdModelTelefona"), rs.getInt("IdTipDodatneOpreme"),
                        rs.getInt("Kolicina"), rs.getString("Boja"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtilities.getInstance().close(ps);
        }

        return dodatnaOprema;
    }

    @Override
    public List<DodatnaOpremaDTO> selectBy(DodatnaOpremaDTO dodatnaOprema) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
