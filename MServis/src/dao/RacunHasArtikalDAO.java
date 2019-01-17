package dao;

import dto.RacunHasArtikalDTO;
import java.util.List;

public interface RacunHasArtikalDAO {

	boolean insert(RacunHasArtikalDTO racunArtikal);
	boolean update(RacunHasArtikalDTO racunArtikal);
	boolean delete(RacunHasArtikalDTO racunArtikal);
	List<RacunHasArtikalDTO> selectAll();
	List<RacunHasArtikalDTO> selectBy(RacunHasArtikalDTO racunArtikal);

}