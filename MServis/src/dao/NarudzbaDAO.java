package dao;

import dto.NarudzbaDTO;
import java.util.List;

public interface NarudzbaDAO {

	boolean insert(NarudzbaDTO narudzba);
	boolean delete(NarudzbaDTO narudzba);
	List<NarudzbaDTO> selectAll();

}