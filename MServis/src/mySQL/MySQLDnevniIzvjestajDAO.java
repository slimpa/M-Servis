/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySQL;

import dao.DnevniIzvjestajDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.DnevniIzvjestajDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class MySQLDnevniIzvjestajDAO implements DnevniIzvjestajDAO {

    public static final String SQL_SELECT_ALL = "select * from dnevni_izvjestaj order by IdRacun";


    @Override
    public List<DnevniIzvjestajDTO> selectAll() {
        List<DnevniIzvjestajDTO> artikli = new ArrayList<DnevniIzvjestajDTO>();
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
                    artikli.add(new DnevniIzvjestajDTO(rs.getInt("IdRacun"), rs.getInt("IdArtikal"), rs.getString("Naziv"), rs.getInt("Kolicina"),
                            rs.getDouble("Cijena"), rs.getString("Datum")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(stat);
        }

        return artikli;
    }
}
