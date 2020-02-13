package dao;

import java.util.List;

import entity.Medicine;

public interface MedicineDao extends GenericDao<Medicine, Integer>{
	
	public List<Medicine> selectByPage(Integer pageNo,Integer pageSize);
	
	public Integer countMedicine();

	public Medicine selectByName(String name);

	public List<Medicine> selectByCategory(String category);

	public List<Medicine> findPriceByName(String mname);

}
