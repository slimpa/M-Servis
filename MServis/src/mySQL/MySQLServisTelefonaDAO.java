package mySQL;

import dao.ServisTelefonaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.ServisTelefonaDTO;
import dto.StanjeTelefonaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLServisTelefonaDAO implements ServisTelefonaDAO {

    /**
     *
     * @param servisTelefona
     */
    private static final String SQL_CALL_INSERT_SERVIS = "{call dodaj_servis(?, ?, ?, ?, ?)}";
    private static final String SQL_UPDATE = "update servistelefona set";
    private static final String SQL_SELECT_ALL = "select * from servistelefona";

    public boolean insert(ServisTelefonaDTO servisTelefona) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(SQL_CALL_INSERT_SERVIS);
            cs.setString(1, servisTelefona.getOpisKvara());
            cs.setString(2, servisTelefona.getSerijskiBrojTelefona());
            cs.setInt(3, 1);
            cs.setInt(4, servisTelefona.getIdZaposleni());
            cs.setInt(5, servisTelefona.getIdModelTelefona());
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
     * @param servisTelefona
     */
    public boolean delete(ServisTelefonaDTO servisTelefona) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;
        String query = SQL_UPDATE + " TelefonPreuzet = ? where IdServisTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, 0);
            ps.setInt(2, servisTelefona.getIdServisTelefona());

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
     * @param servisTelefona
     */
    public boolean update(ServisTelefonaDTO servisTelefona) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(SQL_UPDATE + " OpisKvara = ?, SerijskiBrojTelefona = ?,"
                    + " IdStanjeTelefona = ?, IdModelTelefona = ? where IdServisTelefona = ?");
            cs.setString(1, servisTelefona.getOpisKvara());
            cs.setString(2, servisTelefona.getSerijskiBrojTelefona());
            cs.setInt(3, servisTelefona.getIdStanjeTelefona());
            cs.setInt(4, servisTelefona.getIdModelTelefona());
            cs.setInt(5, servisTelefona.getIdServisTelefona());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(cs);
        }

        return true;
    }

    public List<ServisTelefonaDTO> selectAll() {
        List<ServisTelefonaDTO> servisi = new ArrayList<ServisTelefonaDTO>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            stat = conn.createStatement();
            rs = stat.executeQuery(SQL_SELECT_ALL + " where TelefonPreuzet = 0");

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    servisi.add(new ServisTelefonaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdKlijent"), rs.getInt("IdZaposleni"),
                            rs.getInt("IdStanjeTelefona"), rs.getString("OpisKvara"), rs.getDate("DatumPrijema"),
                            rs.getInt("IdModelTelefona"), rs.getString("SerijskiBrojTelefona"), rs.getBoolean("TelefonPreuzet")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return servisi;
    }

    /**
     *
     * @param servisTelefona
     */
    public List<ServisTelefonaDTO> selectBy(ServisTelefonaDTO servisTelefona) {
        List<ServisTelefonaDTO> servisi = new ArrayList<ServisTelefonaDTO>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
           conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_SELECT_ALL + " where IdServisTelefona = ? and IdStanjeTelefona = ?");
            ps.setInt(1, servisTelefona.getIdServisTelefona());
            ps.setInt(2, servisTelefona.getIdStanjeTelefona());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    servisi.add(new ServisTelefonaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdKlijent"), rs.getInt("IdZaposleni"),
                            rs.getInt("IdStanjeTelefona"), rs.getString("OpisKvara"), rs.getDate("DatumPrijema"),
                            rs.getInt("IdModelTelefona"), rs.getString("SerijskiBrojTelefona"), rs.getBoolean("TelefonPreuzet")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return servisi;
    }

    public boolean updateStanje(ServisTelefonaDTO servisTelefona) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            cs = conn.prepareCall(SQL_UPDATE + " IdStanjeTelefona = ? where IdServisTelefona = ?");
            cs.setInt(1, servisTelefona.getIdStanjeTelefona());
            cs.setInt(2, servisTelefona.getIdServisTelefona());
            cs.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(cs);
        }

        return true;
    }

    public List<ServisTelefonaDTO> selectById(ServisTelefonaDTO servisTelefona) {
        List<ServisTelefonaDTO> servisi = new ArrayList<ServisTelefonaDTO>();
        Connection conn = null;

        ResultSet rs = null;
        PreparedStatement ps = null;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_SELECT_ALL + " where IdServisTelefona = ?");
            ps.setInt(1, servisTelefona.getIdServisTelefona());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    servisi.add(new ServisTelefonaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdKlijent"), rs.getInt("IdZaposleni"),
                            rs.getInt("IdStanjeTelefona"), rs.getString("OpisKvara"), rs.getDate("DatumPrijema"),
                            rs.getInt("IdModelTelefona"), rs.getString("SerijskiBrojTelefona"), rs.getBoolean("TelefonPreuzet")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return servisi;
    }

    public List<ServisTelefonaDTO> selectByStanje(ServisTelefonaDTO servisTelefona) {
        List<ServisTelefonaDTO> servisi = new ArrayList<ServisTelefonaDTO>();
        Connection conn = null;
        Statement stat = null;
        ResultSet rs = null;
        PreparedStatement ps = null;
        
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_SELECT_ALL + " where IdStanjeTelefona = ?");
            ps.setInt(1, servisTelefona.getIdStanjeTelefona());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    servisi.add(new ServisTelefonaDTO(rs.getInt("IdServisTelefona"), rs.getInt("IdKlijent"), rs.getInt("IdZaposleni"),
                            rs.getInt("IdStanjeTelefona"), rs.getString("OpisKvara"), rs.getDate("DatumPrijema"),
                            rs.getInt("IdModelTelefona"), rs.getString("SerijskiBrojTelefona"), rs.getBoolean("TelefonPreuzet")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return servisi;
    }

}
