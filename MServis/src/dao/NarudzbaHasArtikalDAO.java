package dao;

import dto.NarudzbaHasArtikalDTO;

import java.util.List;

public interface NarudzbaHasArtikalDAO {

	boolean insert(NarudzbaHasArtikalDTO narudzbaArtikal);
	boolean delete(NarudzbaHasArtikalDTO narudzbaArtikal);
	List<NarudzbaHasArtikalDTO> selectAll();


}