package mySQL;

import dao.ZaposleniDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ZaposleniDTO;
import java.sql.CallableStatement;
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
    public static final String SQL_CALL_DODAJ_ZAPOSLENOG="{call dodaj_zaposlenog(?,?,?,?,?,?)}";
    public static final String SQL_CALL_UPDATE_ZAPOSLENI = "{call izmijeni_zaposlenog(?, ?, ?, ?, ?)}";

    /**
     *
     * @param zaposleni
     */
    public boolean insert(ZaposleniDTO zaposleni) {
         Connection conn=null;
		CallableStatement cs=null;
		int flag=0;
		try {
			conn=ConnectionPool.getInstance().checkOut();
			cs=conn.prepareCall(SQL_CALL_DODAJ_ZAPOSLENOG);
			cs.setString(1, zaposleni.getIme());
                        cs.setString(2, zaposleni.getPrezime());
                        cs.setString(3, zaposleni.getKoriscnikoIme());
                        cs.setString(4, zaposleni.getLozinka());
                        cs.setString(5, zaposleni.getBrojTelefona());
                        cs.setString(6, zaposleni.getRadnoMjesto());
			cs.execute();
			
		}
		catch(Exception e) {
		//	e.printStackTrace();
                        return false;
		}
		finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(cs);
                }
                return true;
    }

    /**
     *
     * @param zaposleni
     */
    public boolean update(ZaposleniDTO zaposleni) {
         Connection conn = null;
        CallableStatement cs = null;
      
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(SQL_CALL_UPDATE_ZAPOSLENI);

            cs.setString(1, zaposleni.getIme());
            cs.setString(2, zaposleni.getPrezime());
            cs.setString(3, zaposleni.getBrojTelefona());
            cs.setString(4, zaposleni.getRadnoMjesto());
            cs.setInt(5, zaposleni.getIdOsoba());
            
            cs.execute();
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
            
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(cs);
        }

        return true;
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
        String query = SQL_SELECT + " where KorisnickoIme=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, zaposleni.getKoriscnikoIme());
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
