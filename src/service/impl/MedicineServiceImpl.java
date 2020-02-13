package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import service.MedicineService;
import tools.Constants;
import tools.Pager;
import dao.MedicineDao;
import entity.Medicine;
import entity.User;
import exception.ServiceException;

@Service
@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineDao medicineDao;
	
	public void addMedicine(Medicine medicine) throws ServiceException {
		try {
			medicineDao.insert(medicine);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage());
		}
	}
	
	public void modifyMedicine(Medicine medicine) throws ServiceException {
		medicineDao.update(medicine);
	}

	public MedicineDao getMedicineDao() {
		return medicineDao;
	}

	public void setMedicineDao(MedicineDao medicineDao) {
		this.medicineDao = medicineDao;
	}

	public Medicine findMedicineById(Integer mid) throws ServiceException {
		Medicine medicine = null;
		medicine = medicineDao.selectById(mid);
		return medicine;
	}

	@Override
	public Pager<Medicine> findMedicineByPage(Integer pageNo, Integer pageSize) throws ServiceException {
		Pager<Medicine> page = new Pager<Medicine>();
		page.setContent(medicineDao.selectByPage(pageNo, pageSize));
		page.setPageCountByTotalCountAndPageSize(medicineDao.countMedicine(),pageSize);
		page.setPageNo(pageNo);
		return page;
	}

	@Override
	public void removeMedicine(Integer mid) throws ServiceException {
		Medicine medicine = findMedicineById(mid);
		if(medicine==null){
			throw new ServiceException("暂无药品");
		}
		medicineDao.remove(medicine);
	}

	@Override
	public Medicine findMedicineByMname(String mname) throws ServiceException {
		Medicine medicine = medicineDao.selectByName(mname);
		return medicine;
	}
	

	@Override
	public List<Medicine> findMedicineByCategory(String category) throws ServiceException {
		// TODO Auto-generated method stub
			List<Medicine> medicines = medicineDao.selectByCategory(category);
			return medicines;
	}

	@Override
	public List<Medicine> findPriceByMedicine(String mname) throws ServiceException {
		// TODO Auto-generated method stub
		List<Medicine> price = medicineDao.findPriceByName(mname);
		return price;
	}

}
