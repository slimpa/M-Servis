package dao;

import dto.DobavljacDTO;
import java.util.List;

public interface DobavljacDAO {

	boolean insert(DobavljacDTO dobavljac);
	boolean update(DobavljacDTO dobavljac);
	boolean delete(DobavljacDTO dobavljac);
	List<DobavljacDTO> selectAll();
	List<DobavljacDTO> selectBy(DobavljacDTO dobavljac);

}