/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DnevniIzvjestajDTO;
import java.util.List;

/**
 *
 * @author Nikola
 */
public interface DnevniIzvjestajDAO {
    	boolean insert(DnevniIzvjestajDTO admin);
	boolean update(DnevniIzvjestajDTO admin);
	boolean delete(DnevniIzvjestajDTO admin);
	List<DnevniIzvjestajDTO> selectAll();
	List<DnevniIzvjestajDTO> selectBy(DnevniIzvjestajDTO admin);
        DnevniIzvjestajDTO selectAdmin(DnevniIzvjestajDTO admin);
}
