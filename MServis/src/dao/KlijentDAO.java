package dao;

import dto.KlijentDTO;
import java.util.List;

public interface KlijentDAO {

	boolean insert(KlijentDTO klijent);
	boolean update(KlijentDTO klijent);
	boolean delete(KlijentDTO klijent);
	List<KlijentDTO> selectAll();
	List<KlijentDTO> selectBy(KlijentDTO klijent);

}