package DAO;

import DTO.TelefonDTO;

import java.util.List;

public interface TelefonDAO {

	boolean insert(TelefonDTO telefon);
	boolean update(TelefonDTO telefon);
	boolean delete(TelefonDTO telefon);
	List<TelefonDTO> selectAll();
	List<TelefonDTO> selectBy(TelefonDTO telefon);

}