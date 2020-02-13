package service;

import tools.Pager;

import java.util.List;

import entity.Medicine;
import exception.ServiceException;

public interface MedicineService {
	
	public void addMedicine(Medicine medicine) throws ServiceException;
	
	public void modifyMedicine(Medicine medicine) throws ServiceException;
	
	public Medicine findMedicineByMname(String mname)throws ServiceException;
	
	public Medicine findMedicineById(Integer mid) throws ServiceException;
	
	public Pager<Medicine> findMedicineByPage(Integer pageNo,Integer pageSize) throws ServiceException;
	
	public void removeMedicine(Integer mid) throws ServiceException;

	public List<Medicine> findMedicineByCategory(String category)throws ServiceException;

	public List<Medicine> findPriceByMedicine(String mname)throws ServiceException;
	
}


