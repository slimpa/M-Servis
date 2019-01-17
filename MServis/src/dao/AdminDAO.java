package DAO;

import DTO.AdminDTO;
import java.util.List;

public interface AdminDAO {

	boolean insert(AdminDTO admin);
	boolean update(AdminDTO admin);
	boolean delete(AdminDTO admin);
	List<AdminDTO> selectAll();
	List<AdminDTO> selectBy(AdminDTO admin);

}