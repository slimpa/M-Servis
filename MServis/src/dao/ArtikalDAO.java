package dao;

import dto.ArtikalDTO;
import java.util.List;

public interface ArtikalDAO {

	boolean insert(ArtikalDTO artikal);
	boolean update(ArtikalDTO artikal);
	boolean delete(ArtikalDTO artikal);
	List<ArtikalDTO> selectAll();
	List<ArtikalDTO> selectBy(ArtikalDTO artikal);
        Integer getLastId();

}