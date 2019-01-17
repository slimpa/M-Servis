package mySQL;

import dao.OsobaDAO;
import dbu.ConnectionPool;
import dbu.DBUtil;
import dto.OsobaDTO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

public class MySQLOsobaDAO implements OsobaDAO {
    public static final String SQL_SELECT="select * from osoba";
	/**
	 * 
	 * @param osoba
	 */
	public boolean insert(OsobaDTO osoba) {
		// TODO - implement MySQLOsobaDAO.insert
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param osoba
	 */
	public boolean update(OsobaDTO osoba) {
		// TODO - implement MySQLOsobaDAO.update
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param osoba
	 */
	public boolean delete(OsobaDTO osoba) {
		// TODO - implement MySQLOsobaDAO.delete
		throw new UnsupportedOperationException();
	}

	public List<OsobaDTO> selectAll() {
		ArrayList<OsobaDTO> retVal=new ArrayList<OsobaDTO>();
		Connection conn=null;
		Statement st=null;
		ResultSet rs=null;
		
		try{
			conn=ConnectionPool.getInstance().checkOut();
			st=conn.createStatement();
			rs=st.executeQuery(SQL_SELECT);
			if(rs==null){
				return null;
			}
			else{
				while(rs.next()){
					retVal.add(new OsobaDTO(rs.getInt("IdOsoba"),rs.getString("Ime")));
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			ConnectionPool.getInstance().checkIn(conn);
			DBUtil.getInstance().close(st);
		}
		return retVal;
	
	}

	/**
	 * 
	 * @param osoba
	 */
	public List<OsobaDTO> selectBy(OsobaDTO osoba) {
		// TODO - implement MySQLOsobaDAO.selectBy
		throw new UnsupportedOperationException();
	}

}