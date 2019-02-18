package dao;

import dto.TelefonDTO;

import java.util.List;

public interface TelefonDAO {

	boolean insert(TelefonDTO telefon);
	boolean update(TelefonDTO telefon);
	boolean delete(TelefonDTO telefon);
	List<TelefonDTO> selectAll();
	List<TelefonDTO> selectBy(String model);
        List<TelefonDTO> selectByModel(String model);
        List<TelefonDTO> selectBySerial(String model);

}