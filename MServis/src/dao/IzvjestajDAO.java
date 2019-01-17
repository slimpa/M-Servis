package DAO;

import DTO.IzvjestajDTO;

import java.util.List;

public interface IzvjestajDAO {

	boolean insert(IzvjestajDTO izvjestaj);
	boolean update(IzvjestajDTO izvjestaj);
	boolean delete(IzvjestajDTO izvjestaj);
	List<IzvjestajDTO> selectAll();
	List<IzvjestajDTO> selectBy(IzvjestajDTO izvjestaj);

}