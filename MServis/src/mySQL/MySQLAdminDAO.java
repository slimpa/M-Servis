package mySQL;

import dao.AdminDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.AdminDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLAdminDAO implements AdminDAO {

    public static final String SQL_SELECT = "select * from admin";
    public static final String SQL_SELECT_ALL = "select * from svi_admini";
    public static final String SQL_UPDATE = "update osoba set";
    public static final String SQL_CALL_DODAJ_ADMINA="{call dodaj_admina(?,?,?,?,?,?)}";
    /**
     *
     * @param admin
     */
    public boolean insert(AdminDTO admin) {
        Connection conn=null;
		CallableStatement cs=null;
		int flag=0;
		try {
			conn=ConnectionPool.getInstance().checkOut();
			cs=conn.prepareCall(SQL_CALL_DODAJ_ADMINA);
			cs.setString(1, admin.getIme());
                        cs.setString(2, admin.getPrezime());
                        cs.setString(3, admin.getKoriscnikoIme());
                        cs.setString(4, admin.getLozinka());
                        cs.setString(5, admin.getBrojTelefona());
                        cs.setString(6, admin.getNazivFirme());
			cs.execute();
			
		}
		catch(Exception e) {
			e.printStackTrace();
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
     * @param admin
     */
    public boolean update(AdminDTO admin) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param admin
     */
    public boolean delete(AdminDTO admin) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Obrisano = 1 where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, admin.getIdOsoba());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<AdminDTO> selectAll() {
        List<AdminDTO> admini = new ArrayList<AdminDTO>();
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
                    admini.add(new AdminDTO(rs.getInt("IdOsoba"), rs.getString("KorisnickoIme"), null,
                            rs.getString("Ime"), rs.getString("Prezime"), rs.getString("BrojTelefona"),
                            rs.getString("NazivFirme")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return admini;
    }

    /**
     *
     * @param admin
     */
    public List<AdminDTO> selectBy(AdminDTO admin) {
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param admin
     */
    public AdminDTO selectAdmin(AdminDTO admin) {
        AdminDTO retVal = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where KorisnickoIme=?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, admin.getKoriscnikoIme());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                if (rs.next()) {
                    retVal = new AdminDTO(rs.getInt("IdAdmin"), rs.getString("KorisnickoIme"), rs.getString("Lozinka"), rs.getString("NazivFirme"));
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
