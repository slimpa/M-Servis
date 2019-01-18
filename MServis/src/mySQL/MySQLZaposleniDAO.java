package mySQL;

import dao.ZaposleniDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ZaposleniDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MySQLZaposleniDAO implements ZaposleniDAO {

    public static final String SQL_SELECT = "select * from zaposleni";
    public static final String SQL_SELECT_ALL = "select * from svi_zaposleni";
    public static final String SQL_UPDATE = "update osoba set";

    /**
     *
     * @param zaposleni
     */
    public boolean insert(ZaposleniDTO zaposleni) {
        // TODO - implement MySQLZaposleniDAO.insert
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param zaposleni
     */
    public boolean update(ZaposleniDTO zaposleni) {
        // TODO - implement MySQLZaposleniDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param zaposleni
     */
    public boolean delete(ZaposleniDTO zaposleni) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Obrisano = 1 where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, zaposleni.getIdOsoba());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<ZaposleniDTO> selectAll() {
        List<ZaposleniDTO> zaposleni = new ArrayList<ZaposleniDTO>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stat = conn.createStatement();
            rs = stat.executeQuery(SQL_SELECT_ALL);

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    zaposleni.add(new ZaposleniDTO(rs.getInt("IdOsoba"), rs.getString("KorisnickoIme"), null, rs.getString("RadnoMjesto"),
                            rs.getString("Ime"), rs.getString("Prezime"), rs.getString("BrojTelefona")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return zaposleni;
    }

    /**
     *
     * @param zaposleni
     */
    public List<ZaposleniDTO> selectBy(ZaposleniDTO zaposleni) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param zaposleni
     */
    public ZaposleniDTO selectZaposleni(ZaposleniDTO zaposleni) {
        ZaposleniDTO retVal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where KorisnickoIme=? and Lozinka=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, zaposleni.getKoriscnikoIme());
            ps.setString(2, zaposleni.getLozinka());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                if (rs.next()) {
                    retVal = new ZaposleniDTO(rs.getInt("IdZaposleni"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), rs.getString("RadnoMjesto"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return retVal;
    }

}
