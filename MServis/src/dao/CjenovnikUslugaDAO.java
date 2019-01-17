package dao;

import dto.CjenovnikUslugaDTO;
import java.util.List;

public interface CjenovnikUslugaDAO {

	boolean insert(CjenovnikUslugaDTO cjenovnikUsluga);
	boolean update(CjenovnikUslugaDTO cjenovnikUsluga);
	boolean delete(CjenovnikUslugaDTO cjenovnikUsluga);
	List<CjenovnikUslugaDTO> selectAll();
	List<CjenovnikUslugaDTO> selectBy(CjenovnikUslugaDTO cjenovnikUsluga);

}