package mySQL;

import dao.StanjeTelefonaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.StanjeTelefonaDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLStanjeTelefonaDAO implements StanjeTelefonaDAO {

    /**
     *
     * @param stanjeTelefona
     */
    public static final String SQL_INSERT = "insert into stanjetelefona(Stanje, Obrisano) values (?, ?);";
    public static final String SQL_SELECT = "select * from stanjetelefona where Obrisano = 0";
    public static final String SQL_UPDATE = "update stanjetelefona set";

    
     
    public boolean insert(StanjeTelefonaDTO stanjeTelefona) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setString(1, stanjeTelefona.getStanje());
            ps.setInt(2, 0);
            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param stanjeTelefona
     */
    public boolean update(StanjeTelefonaDTO stanjeTelefona) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Stanje = ? where IdStanjeTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setString(1, stanjeTelefona.getStanje());
            ps.setInt(2, stanjeTelefona.getIdStanjeTelefona());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            return false;
            //e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param stanjeTelefona
     */
    public boolean delete(StanjeTelefonaDTO stanjeTelefona) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " Obrisano = 1 where IdStanjeTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);

            ps.setInt(1, stanjeTelefona.getIdStanjeTelefona());

            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    public List<StanjeTelefonaDTO> selectAll() {
        List<StanjeTelefonaDTO> stanja = new ArrayList<StanjeTelefonaDTO>();
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
                    stanja.add(new StanjeTelefonaDTO(rs.getInt("IdStanjeTelefona"), rs.getString("Stanje")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return stanja;
    }

    @Override
    public List<StanjeTelefonaDTO> selectBy(StanjeTelefonaDTO stanjeTelefona) {
        List<StanjeTelefonaDTO> stanja = new ArrayList<StanjeTelefonaDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " and Stanje like ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setString(1, stanjeTelefona.getStanje() + "%");
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    stanja.add(new StanjeTelefonaDTO(rs.getInt("IdStanjeTelefona"), rs.getString("Stanje")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return stanja;
    }
    
    

}
