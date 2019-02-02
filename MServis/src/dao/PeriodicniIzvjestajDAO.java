/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PeriodicniIzvjestajDTO;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author Nikola
 */
public interface PeriodicniIzvjestajDAO {
    List<PeriodicniIzvjestajDTO> selectBetween(Date odDatum,Date doDatum);
}
