package mySQL;

import dao.UgradjeniRezervniDioDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.UgradjeniRezervniDioDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLUgradjeniRezervniDioDAO implements UgradjeniRezervniDioDAO {

    /**
     *
     * @param ugradjeniRezervniDio
     */
    private static final String SQL_INSERT = "insert into ugradjenirezervnidio(IdServisTelefona, IdRezervniDio) values (?, ?)";
    private static final String SQL_SELECT = "select * from ugradjeni_dio_servis";
    private static final String SQL_SELECT_CIJENA = "select * from ugradjeni_dio_cijena";

    public boolean insert(UgradjeniRezervniDioDTO ugradjeniRezervniDio) {
        Connection conn = null;
        PreparedStatement ps = null;
        boolean returnValue = false;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(SQL_INSERT);
            ps.setInt(1, ugradjeniRezervniDio.getIdServisa());
            ps.setInt(2, ugradjeniRezervniDio.getIdRezervniDio());
            returnValue = ps.executeUpdate() == 1;
        } catch (SQLException e) {
            //e.printStackTrace();
            return false;
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return returnValue;
    }

    /**
     *
     * @param ugradjeniRezervniDio
     */
    public boolean update(UgradjeniRezervniDioDTO ugradjeniRezervniDio) {
        // TODO - implement MySQLUgradjeniRezervniDioDAO.update
        throw new UnsupportedOperationException();
    }

    /**
     *
     * @param ugradjeniRezervniDio
     */
    public boolean delete(UgradjeniRezervniDioDTO ugradjeniRezervniDio) {
        // TODO - implement MySQLUgradjeniRezervniDioDAO.delete
        throw new UnsupportedOperationException();
    }

    public List<UgradjeniRezervniDioDTO> selectAll() {
        List<UgradjeniRezervniDioDTO> dijelovi = new ArrayList<UgradjeniRezervniDioDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT;

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    dijelovi.add(new UgradjeniRezervniDioDTO(rs.getInt("IdRezervniDio"), rs.getInt("IdServisTelefona"), rs.getString("Naziv")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return dijelovi;
    }

    /**
     *
     * @param ugradjeniRezervniDio
     */
    public List<UgradjeniRezervniDioDTO> selectBy(UgradjeniRezervniDioDTO ugradjeniRezervniDio) {
        List<UgradjeniRezervniDioDTO> dijelovi = new ArrayList<UgradjeniRezervniDioDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT + " where IdServisTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ugradjeniRezervniDio.getIdServisa());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    dijelovi.add(new UgradjeniRezervniDioDTO(rs.getInt("IdRezervniDio"), rs.getInt("IdServisTelefona"), rs.getString("Naziv")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return dijelovi;

    }
    
   public List<UgradjeniRezervniDioDTO> selectCijena(UgradjeniRezervniDioDTO ugradjeniRezervniDio){
        List<UgradjeniRezervniDioDTO> dijelovi = new ArrayList<UgradjeniRezervniDioDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT_CIJENA + " where IdServisTelefona = ?";

        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setInt(1, ugradjeniRezervniDio.getIdServisa());
            rs = ps.executeQuery();

            if (rs == null) {
                return null;
            } else {
                while (rs.next()) {
                    dijelovi.add(new UgradjeniRezervniDioDTO(rs.getInt("IdRezervniDio"), rs.getInt("IdServisTelefona"), rs.getString("Naziv"), rs.getDouble("Cijena")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }

        return dijelovi;
    }

}
