/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mySQL;

import dao.PeriodicniIzvjestajDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.PeriodicniIzvjestajDTO;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nikola
 */
public class MySQLPeriodicniIzvjestajDAO implements PeriodicniIzvjestajDAO {

        public static final String SQL_SELECT_ALL = "select racun.IdRacun,date(racun.Vrijeme) as Datum, artikal.IdArtikal,artikal.Naziv, racun_has_artikal.Kolicina, cijena.Cijena\n" +
"from cijena inner join artikal  inner join racun_has_artikal inner join racun\n" +
"on cijena.IdArtikla=artikal.IdArtikal and artikal.IdArtikal=racun_has_artikal.IdArtikal and racun.IdRacun=racun_has_artikal.IdRacun\n" +
"where ((cijena.DatumDo is not null and cijena.DatumOd<racun.Vrijeme and cijena.DatumDo>racun.Vrijeme )\n" +
" or (cijena.DatumOd<racun.Vrijeme and cijena.TrenutnaCijena=1))\n" +
" and (date(racun.Vrijeme) between ? and ?) order by Datum asc;";
    
    
    @Override
    public List<PeriodicniIzvjestajDTO> selectBetween(Date odDatum, Date doDatum) {
      List<PeriodicniIzvjestajDTO> artikli = new ArrayList<PeriodicniIzvjestajDTO>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String query = SQL_SELECT_ALL;
        System.out.print(odDatum.toString());
        System.out.print(doDatum.toString());
        try {
            conn = ConnectionPool.getInstance().checkOut();
            ps = conn.prepareStatement(query);
            ps.setDate(1, odDatum);
             ps.setDate(2, doDatum);
            rs = ps.executeQuery();

            if (rs == null) {

                return null;
            } else {
                while (rs.next()) {

                    artikli.add(new PeriodicniIzvjestajDTO(rs.getInt("IdRacun"), rs.getInt("IdArtikal"), rs.getString("Naziv"),
                       rs.getString("Datum"), rs.getInt("Kolicina"), rs.getDouble("Cijena")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().checkIn(conn);
            DBUtil.getInstance().close(ps);
        }
        return artikli;
    }
    
}
