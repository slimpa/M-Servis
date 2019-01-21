package mySQL;

import dao.KlijentDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.KlijentDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLKlijentDAO implements KlijentDAO {

    private static final String SQL_CALL_INSERT_KLIJENT = "{call dodaj_klijenta(?, ?, ?)}";
    private static final String SQL_UPDATE_KLIJENT = "update osoba set";
    private static final String SQL_SELECT_ALL = "select * from svi_klijenti";

    /**
     *
     * @param klijent
     */
    public boolean insert(KlijentDTO klijent) {
        Connection conn = null;
        CallableStatement cs = null;
        int flag = 0;
        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(SQL_CALL_INSERT_KLIJENT);
            cs.setString(1, klijent.getIme());
            cs.setString(2, klijent.getPrezime());
            cs.setString(3, klijent.getBrojTelefona());
            cs.execute();

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(cs);
        }
        return true;
    }

    /**
     *
     * @param klijent
     */
    public boolean update(KlijentDTO klijent) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE_KLIJENT + " Ime = ?, Prezime = ?, BrojTelefona = ? where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, klijent.getIme());
            ps.setString(2, klijent.getPrezime());
            ps.setString(3, klijent.getBrojTelefona());
            ps.setInt(4, klijent.getIdOsoba());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param klijent
     */
    public boolean delete(KlijentDTO klijent) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = "update osoba set Obrisano = 1 where IdOsoba = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, klijent.getIdOsoba());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<KlijentDTO> selectAll() {
        List<KlijentDTO> klijenti = new ArrayList<KlijentDTO>();
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
                    klijenti.add(new KlijentDTO(rs.getInt("IdOsoba"), rs.getString("Ime"),
                            rs.getString("Prezime"), rs.getString("BrojTelefona")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return klijenti;
    }

    /**
     *
     * @param klijent
     */
    public List<KlijentDTO> selectBy(KlijentDTO klijent) {
        List<KlijentDTO> klijenti = new ArrayList<KlijentDTO>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		String query = SQL_SELECT_ALL + " where IdOsoba = ?";
		
		try {
			conn = ConnectionPool.getInstance().checkOut();
			ps = conn.prepareStatement(query);
			ps.setInt(1, klijent.getIdOsoba());
			rs = ps.executeQuery();
			
			if(rs == null) return null;
			else {
				while(rs.next()) {
					klijenti.add(new KlijentDTO(rs.getInt("IdOsoba"), rs.getString("Ime"), rs.getString("Prezime"),
                                        rs.getString("BrojTelefona")));
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(ps);
		}
		
		return klijenti;
	}

   
}
