package dao;

import dto.NarudzbaHasArtikalDTO;

import java.util.List;

public interface NarudzbaHasArtikalDAO {

	boolean insert(NarudzbaHasArtikalDTO narudzbaArtikal);
	boolean update(NarudzbaHasArtikalDTO narudzbaArtikal);
	boolean delete(NarudzbaHasArtikalDTO narudzbaArtikal);
	List<NarudzbaHasArtikalDTO> selectAll();
	List<NarudzbaHasArtikalDTO> selectBy(NarudzbaHasArtikalDTO narudzbaArtikal);

}