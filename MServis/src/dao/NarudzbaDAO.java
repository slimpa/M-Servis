package DAO;

import DTO.NarudzbaDTO;
import java.util.List;

public interface NarudzbaDAO {

	boolean insert(NarudzbaDTO narudzba);
	boolean update(NarudzbaDTO narudzba);
	boolean delete(NarudzbaDTO narudzba);
	List<NarudzbaDTO> selectAll();
	List<NarudzbaDTO> selectBy(NarudzbaDTO narudzba);

}